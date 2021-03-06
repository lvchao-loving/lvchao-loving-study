package com.lvchao.easyexcel.utils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.ZipOutputStream;

/**
 * 对文件进行加密压缩
 */
public class ZipFileUtil {

    /**
     * 对文件加密
     * @param filePath 需要加密的文件夹或文件路径
     * @param zipFileName 加密后的文件路径
     * @param password 加密密码，必填
     */
    public static void zipFileAndEncrypt(String filePath, String zipFileName, String password) throws ZipException, IOException {
        if (StringUtils.isBlank(password)){
            throw new RuntimeException("加密密码不为空");
        }
        ZipParameters parameters = setParam(password);
        ArrayList<File> filesToAdd = new ArrayList<File>();
        File file=new File(filePath);
        File[] files = new File[0];
        if(file.isDirectory()) {
            files = file.listFiles();
            for(int i=0;i<files.length;i++){
                filesToAdd.add(files[i]);
                System.out.println("文件名称："+files[i].getName());
            }
        }
        else {
            filesToAdd.add(new File(filePath));
        }
        ZipFile zipFile = new ZipFile(zipFileName);
        zipFile.addFiles(filesToAdd, parameters);//this line does works
    }
    /**
     * 加密1，winrar，好压2345可直接输入密码解压
     */
    public static void zipFileStream(InputStream is, String zipFileName, String password) {
        try {
            ZipParameters parameters = setParam(password);
            //addStream,多设置两个参数，缺一不可
            parameters.setFileNameInZip("yourfilename.xlsx");
            parameters.setSourceExternalStream(true);

            ZipFile zipFile = new ZipFile(zipFileName);
            zipFile.addStream(is, parameters);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
    private static ZipParameters setParam(String password){
        //设置压缩文件参数
        ZipParameters parameters = new ZipParameters();
        //设置压缩方法
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        //设置压缩级别
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        //设置压缩文件是否加密
        parameters.setEncryptFiles(true);
        //设置aes加密强度
        parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
        //设置加密方法
        parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
        //设置密码
        parameters.setPassword(password.toCharArray());
        return parameters;
    }
    public static void main(String[] args) throws Exception {
        try{
            //加密,addFile
            zipFileAndEncrypt("D:\\test","D:\\test.zip","123");
            // zipFileAndEncrypt("D:\\test\\123.xlsx","D:\\test.zip","123");

            // InputStream in = new FileInputStream("D:\\test");
            // zipFileStream(in,"D:\\test.zip","123");
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

}