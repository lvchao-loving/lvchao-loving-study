package com.lvchao.easyexcel.controller;

import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.lvchao.easyexcel.entity.User;
import com.lvchao.easyexcel.utils.ZipFileUtil;
import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.exception.ZipException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2021/1/5
 */

// @RestController
// @Slf4j
public class EasyExcelDownloadController {

    @PostMapping(value = "/test001")
    public void execute1(MultipartFile file) {
        String prefix = FileUtil.getSuffix(file.getOriginalFilename());
        System.out.println(prefix);
        // ZipFileUtil.zipFileAndEncrypt("D:\\test","demo.zip","123456");
    }

    // 获取当前系统的临时目录
    private static final String FilePath = System.getProperty("java.io.tmpdir") + File.separator;

    @GetMapping(value = "/execute")
    public void execute(HttpServletResponse response) {
        // 用于存放文件路径
        List<String> filePaths = new ArrayList<>();
        //生成的ZIP文件名为Demo.zip
        String tmpFileName = "Demo.zip";
        // zip文件路径
        String strZipPath = FilePath + tmpFileName;
        filePaths.add(strZipPath);
        try {
            //创建zip输出流
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipPath));
            //声明文件集合用于存放excel文件
            List<File> fileList = new ArrayList<File>();
            //生成excel文件集合
            for (int i = 0; i < 10; i++) {
                // 生成随机文件名
                String filename = FilePath + generateRandomFilename() + ".xlsx";
                // 将文件路径保存
                fileList.add(creatFile(filename));
                filePaths.add(filename);
                List<User> list = data(0,100);
                // 使用easyexcel生成excel文件
               // writeExcel(newFile, User.class, list, ExcelTypeEnum.XLS);
            }
            byte[] buffer = new byte[1024];
            //将excel文件放入zip压缩包
            for (int i = 0; i < fileList.size(); i++) {
                File file = fileList.get(i);
                FileInputStream fis = new FileInputStream(file);
                out.putNextEntry(new ZipEntry(file.getName()));
                //设置压缩文件内的字符编码，不然会变成乱码
            //    out.setEncoding("GBK");
                int len;
                // 读入需要下载的文件的内容，打包到zip文件
                while ((len = fis.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                out.closeEntry();
                fis.close();
            }
            out.close();
            //下载zip文件
            this.downFile(response, tmpFileName,filePaths);
        } catch (Exception e) {
            // 下载失败删除生成的文件
            deleteFile(filePaths);
          //  log.error("文件下载出错", e);
        }
    }

    /**
     * 模拟数据
     * @param start 数据开始下标
     * @param end 数据结束下标
     * @return
     */
    private static List<User> data(int start,int end){
        List<User> users = new ArrayList<>();
        for (int i = start; i < end; i++) {
            users.add(User.builder().id(i).age(i + 10).name("张三" + i).build());
        }
        return users;
    }

    /**
     * 文件下载
     *  @param response
     * @param str
     * @param filePaths
     */
    private void downFile(HttpServletResponse response, String str, List<String> filePaths) {
        try {
            String path = FilePath + str;
            File file = new File(path);
            if (file.exists()) {
                InputStream ins = new FileInputStream(path);
                BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面
                OutputStream outs = response.getOutputStream();// 获取文件输出IO流
                BufferedOutputStream bouts = new BufferedOutputStream(outs);
                response.setContentType("application/x-download");// 设置response内容的类型
                response.setHeader(
                        "Content-disposition",
                        "attachment;filename="
                                + URLEncoder.encode(str, "UTF-8"));// 设置头部信息
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                // 开始向网络传输文件流
                while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
                    bouts.write(buffer, 0, bytesRead);
                }
                bouts.flush();// 这里一定要调用flush()方法
                ins.close();
                bins.close();
                outs.close();
                bouts.close();
                deleteFile(filePaths);
            }
        } catch (IOException e) {
            deleteFile(filePaths);
            // log.error("文件下载出错", e);
        }
    }
    //创建文件File对象
    private static File creatFile(String filePath) {
        File file = new File(filePath);
        return file;
    }
    //生成随机文件名
    public String generateRandomFilename() {
        String RandomFilename = "";
        Random rand = new Random();//生成随机数
        int random = rand.nextInt();
        Calendar calCurrent = Calendar.getInstance();
        int intDay = calCurrent.get(Calendar.DATE);
        int intMonth = calCurrent.get(Calendar.MONTH) + 1;
        int intYear = calCurrent.get(Calendar.YEAR);
        String now = String.valueOf(intYear) + "_" + String.valueOf(intMonth) + "_" +
                String.valueOf(intDay) + "_";

        RandomFilename = now + String.valueOf(random > 0 ? random : (-1) * random);
        return RandomFilename;
    }
    //删除文件
    public static boolean deleteFile(List<String> filePath){
        boolean result = false;
        for (String pathname:filePath){
            File file = new File(pathname);
            if (file.exists()) {
                file.delete();
                result = true;
            }
        }
        return result;
    }
    /**
     * @Title: writeExcel
     * @Description: 写入excel文件到输出流web端
     *
     */
    private void writeExcel(OutputStream outputStream, Class<?> clazz, List<?> datalist, ExcelTypeEnum excelType, String sheetName) throws IOException {
        EasyExcel.write(outputStream, clazz).excelType(excelType).sheet(sheetName==null ? "sheet1":sheetName).doWrite(datalist);
        outputStream.flush();
    }

    /**
     * @Title: writeExcel
     * @Description: 写入excel到本地路径
     */
    private void writeExcel(File newFile, Class<?> clazz, List<?> datalist,ExcelTypeEnum excelType) {
        EasyExcel.write(newFile, clazz).excelType(excelType).sheet("sheet1").doWrite(datalist);
    }

    /**
     * @Title: readExcel
     * @Description: 读取excel内容（从输入流）
     */
    private List<?> readExcel(InputStream inputStream, Class<?> clazz, ReadListener<?> listener) {
        List<?> list = null;
        list = EasyExcel.read(inputStream, clazz, listener).sheet().doReadSync();
        return list;
    };

    public static void main(String[] args) {
      //  writeExcel("",User,data())
        File file = creatFile("D:\\test\\1.xlsx");
        EasyExcel.write(file, User.class).excelType(ExcelTypeEnum.XLSX).sheet("sheet1").doWrite(data(0,100));
    }
}
