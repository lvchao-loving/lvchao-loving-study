package com.lvchao.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.lvchao.easyexcel.entity.ExcelTotalVo;
import com.lvchao.easyexcel.entity.User;
import com.lvchao.easyexcel.listener.ExcelListener;
import com.lvchao.easyexcel.listener.ExcelTotalDataListener;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2021/1/5
 */
@RestController
public class EasyExcelController {

    /**
     * 模拟数据
     * @param start 数据开始下标
     * @param end 数据结束下标
     * @return
     */
    private List<User> data(int start,int end){
        List<User> users = new ArrayList<>();
        for (int i = start; i < end; i++) {
            users.add(User.builder().id(i).age(i + 10).name("张三" + i).build());
        }
        return users;
    }

    /**
     * 单个文件上传
     * @param file
     * @return
     */
    @PostMapping("upload")
    public String upload(MultipartFile file){
        try{
            InputStream in = file.getInputStream();
            // 统计总数量
            ExcelTotalVo excelTotalVo = new ExcelTotalVo();
            EasyExcel.read(in, User.class,new ExcelTotalDataListener(excelTotalVo)).sheet().doRead();
            System.out.println("总数量为：" + excelTotalVo.getTotal());
            //调用方法进行读取
            InputStream in1 = file.getInputStream();
            EasyExcel.read(in1, User.class,new ExcelListener()).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
        //文件输入流
        System.out.println("成功");
        return "upload sucess...";
    }

    /**
     * 批量下载，只压缩未加密
     * @param response
     * @throws Exception
     */
    @GetMapping(value = "/downloadBatch")
    public void downloadBatch(HttpServletResponse response) throws Exception {

        String fileName = "测试zip包.zip";
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
        List<User> list1 = data(0,1000);
        List<User> list2 = data(1000,2000);

        //按某个条件分组
        Map<String, List<User>> map = new HashMap();
        map.put("test1.xls", list1);
        map.put("test2.xls", list2);
        exportExcel(response, map);
    }

    /**
     * 说明：说明这里这样写容易出现OOM,建议在后续优化面中把map中的数据放到是实时查询中，边查询边通过流的形式输出
     * @param response
     * @param map 文件数据
     * @throws IOException
     */
    private void exportExcel(HttpServletResponse response, Map<String, List<User>> map) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        try {
            for (Map.Entry<String, List<User>> entry : map.entrySet()) {
                // 文件的名称
                String k = entry.getKey();
                // 文件的数据
                List<User> value = entry.getValue();
                // TODO 设置需要导出的列
                Set<String> includeColumnFiledNames = new HashSet<String>();
                includeColumnFiledNames.add("name");
                includeColumnFiledNames.add("age");

                //构建一个excel对象,这里注意type要是xls不能是xlsx,否则下面的写入后流会关闭,导致报错
                ExcelWriter excelWriter = EasyExcel.write().excelType(ExcelTypeEnum.XLS).includeColumnFiledNames(includeColumnFiledNames).build();
                //构建一个sheet页
                WriteSheet writeSheet = EasyExcel.writerSheet("sheet名称").build();
                //构建excel表头信息
                WriteTable writeTable0 = EasyExcel.writerTable(0).head(User.class).needHead(Boolean.TRUE).build();
                //将表头和数据写入表格
                excelWriter.write(value, writeSheet, writeTable0);

                //创建压缩文件
                ZipEntry zipEntry = new ZipEntry(k);
                zipOutputStream.putNextEntry(zipEntry);
                Workbook workbook = excelWriter.writeContext().writeWorkbookHolder().getWorkbook();
                //将excel对象以流的形式写入压缩流
                workbook.write(zipOutputStream);
            }
            zipOutputStream.flush();
        } catch (Exception e) {
            //抛出异常结束程序
            throw new RuntimeException("数据导出接口异常");
        } finally {
            //关闭数据流，注意关闭的顺序
            zipOutputStream.close();
            outputStream.close();
        }
    }

    /**
     * 加密压缩
     *
     * 说明：说明这里这样写容易出现OOM,建议在后续优化面中把map中的数据放到是实时查询中，边查询边通过流的形式输出
     * @param response
     * @param map 文件数据
     * @throws IOException
     */
    private void exportExcelEncrypt (HttpServletResponse response, Map<String, List<User>> map,String password) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        try {
            for (Map.Entry<String, List<User>> entry : map.entrySet()) {
                // 文件的名称
                String k = entry.getKey();
                // 文件的数据
                List<User> value = entry.getValue();
                //构建一个excel对象,这里注意type要是xls不能是xlsx,否则下面的写入后流会关闭,导致报错
                ExcelWriter excelWriter = EasyExcel.write().excelType(ExcelTypeEnum.XLS).build();
                //构建一个sheet页
                WriteSheet writeSheet = EasyExcel.writerSheet("薪资单").build();
                //构建excel表头信息
                WriteTable writeTable0 = EasyExcel.writerTable(0).head(User.class).needHead(Boolean.TRUE).build();
                //将表头和数据写入表格
                excelWriter.write(value, writeSheet, writeTable0);

                //创建压缩文件
                ZipEntry zipEntry = new ZipEntry(k);
                zipOutputStream.putNextEntry(zipEntry);
                Workbook workbook = excelWriter.writeContext().writeWorkbookHolder().getWorkbook();
                //将excel对象以流的形式写入压缩流
                workbook.write(zipOutputStream);
            }
            zipOutputStream.flush();
        } catch (Exception e) {
            //抛出异常结束程序
            throw new RuntimeException("数据导出接口异常");
        } finally {
            //关闭数据流，注意关闭的顺序
            zipOutputStream.close();
            outputStream.close();
        }
    }

    /**
     * 单个文件下载
     * @param response
     * @throws IOException
     */
    @GetMapping("downloadSimpleton")
    public void downloadSimpleton(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        Set<String> set = new HashSet<String>();
        set.add("name");
        set.add("age");
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
   //     EasyExcel.write(fileName, DemoData.class).includeColumnFiledNames(includeColumnFiledNames).sheet("模板").doWrite(data());
        EasyExcel.write(response.getOutputStream(), User.class).includeColumnFiledNames(set).sheet("模板").doWrite(data(0,100));
    //    EasyExcel.write(response.getOutputStream(), User.class).sheet("模板").doWrite(data(0,100));
    }
}
