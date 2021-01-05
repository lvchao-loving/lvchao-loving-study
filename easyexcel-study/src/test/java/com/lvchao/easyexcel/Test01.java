package com.lvchao.easyexcel;

import cn.hutool.core.util.ZipUtil;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2021/1/5
 */
public class Test01 {
    @Test
    public void test002(){
//    /    ZipUtil.unzip("D:/test/");
        ZipUtil.zip("d:/test");
    }

    @Test
    public void test001(){
        encrypt_zip("C:\\Users\\lvchao\\Desktop\\123.xlsx","C:\\Users\\lvchao\\Desktop\\1234.xlsx","123456");
    }

    public static void encrypt_zip(String src_file, String dst_file, String password) {
        File file = new File(src_file);

        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);//压缩方式
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); // 压缩级别

        parameters.setEncryptFiles(true);
        parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);//加密方式
        parameters.setPassword(password.toCharArray());//设置密码

        try {
            ZipFile zipFile = new ZipFile(dst_file);
            zipFile.setFileNameCharset("gbk");
            zipFile.addFile(file, parameters);

        } catch (net.lingala.zip4j.exception.ZipException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {

        String source = "C:\\Users\\lvchao\\Desktop\\1234.zip";
        String dest = "C:\\Users\\lvchao\\Desktop";
        String password = "155255";
        unZip(source,dest,password);
    }

    public static void unZip(String source,String dest,String password){
        try {
   //         File zipFile = new File(source);
            ZipFile zFile = new ZipFile(source); // 首先创建ZipFile指向磁盘上的.zip文件
            zFile.setFileNameCharset("GBK");
            File destDir = new File(dest); // 解压目录
            if (!destDir.exists()) {// 目标目录不存在时，创建该文件夹
                destDir.mkdirs();
            }
            if (zFile.isEncrypted()) {
                zFile.setPassword(password.toCharArray()); // 设置密码
            }
            zFile.extractAll(dest); // 将文件抽出到解压目录(解压)
            List<FileHeader> headerList = zFile.getFileHeaders();
            List<File> extractedFileList = new ArrayList<File>();
            for (FileHeader fileHeader : headerList) {
                if (!fileHeader.isDirectory()) {
                    extractedFileList.add(new File(destDir, fileHeader.getFileName()));
                }
            }
            File[] extractedFiles = new File[extractedFileList.size()];
            extractedFileList.toArray(extractedFiles);
            for (File f : extractedFileList) {
                System.out.println(f.getAbsolutePath() + "文件解压成功!");
            }
        } catch (ZipException e) {
            e.printStackTrace();
        }


    }
}
