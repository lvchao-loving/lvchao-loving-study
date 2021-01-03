package com.lvchao.common.utils;

import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * https://www.cnblogs.com/xinzhisoft/p/10025901.html
 *
 */
//@SuppressWarnings("restriction")
public class Base64Util {

	/**
	 * 测试
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		//图片--->base64
		//本地图片
//		String url = "C:/Users/Administrator/Desktop/1.png";
		String url = "/upload/aa.jpg";
		String str = Base64Util.ImageToBase64ByLocal(url);
		System.out.println(str);

//		//在线图片地址
//		String string = "http://bpic.588ku.com//element_origin_min_pic/17/03/03/7bf4480888f35addcf2ce942701c728a.jpg";
//		String ste = Base64Utils.ImageToBase64ByOnline(string);
//		System.out.println(ste);
//
//		//base64--->图片
//		Base64Utils.Base64ToImage(str,"C:/Users/Administrator/Desktop/test1.jpg");
//		Base64Utils.Base64ToImage(ste, "C:/Users/Administrator/Desktop/test2.jpg");
	}

	/**
	 * 本地图片转换成base64字符串
	 * @param imgFile    图片本地路径
	 * @return
	 *
	 * @author ZHANGJL
	 * @dateTime 2018-02-23 14:40:46
	 */
	public static String ImageToBase64ByLocal(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;

		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);

			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
//			e.printStackTrace();
			return "";
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		String imgFileExtension = imgFile.substring(imgFile.lastIndexOf(".") + 1);
		String base64Prefix = "";
		base64Prefix += "data:image/"+ imgFileExtension +";base64,";
		return base64Prefix + encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}



	/**
	 * 在线图片转换成base64字符串
	 *
	 * @param imgURL    图片线上路径
	 * @return
	 *
	 * @author ZHANGJL
	 * @dateTime 2018-02-23 14:43:18
	 */
	public static String ImageToBase64ByOnline(String imgURL) {
		ByteArrayOutputStream data = new ByteArrayOutputStream();
		try {
			// 创建URL
			URL url = new URL(imgURL);
			byte[] by = new byte[1024];
			// 创建链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			InputStream is = conn.getInputStream();
			// 将内容读取内存中
			int len = -1;
			while ((len = is.read(by)) != -1) {
				data.write(by, 0, len);
			}
			// 关闭流
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data.toByteArray());
	}


	/**
	 * base64字符串转换成图片
	 * @param imgStr        base64字符串
	 * @param imgFilePath    图片存放路径
	 * @return
	 *
	 * @author ZHANGJL
	 * @dateTime 2018-02-23 14:42:17
	 */
	public static boolean Base64ToImage(String imgStr,String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片

		if (isEmpty(imgStr)) // 图像数据为空
			return false;

		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			// 北京要求图片base64内容前增加"data:image/jpeg;base64,"，解码时需要去掉
			imgStr = imgStr.substring(imgStr.indexOf(",")+1);
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}

			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// https://blog.csdn.net/Yang_Hui_Liang/article/details/86609673
	public static String multipartFileToBase64(MultipartFile file) {
		BASE64Encoder base64Encoder =new BASE64Encoder();
		String base64EncoderImg = null;
		try {
//			base64EncoderImg = file.getOriginalFilename()+","+ base64Encoder.encode(file.getBytes());
			// "data:image/jpeg;base64," +
//			String originalFilename = file.getOriginalFilename();
//			String originalFilenameExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			String dataURISchemePrefix = "data:";
			dataURISchemePrefix += file.getContentType();
			dataURISchemePrefix += ";base64,";
			base64EncoderImg = dataURISchemePrefix + base64Encoder.encode(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return base64EncoderImg;
	}

	/**
	 * 验证字符串是否为空
	 *
	 * @param input
	 * @return
	 */
	private static boolean isEmpty(String input) {
		return input == null || input.equals("");
	}
}
