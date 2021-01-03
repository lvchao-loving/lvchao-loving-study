package com.lvchao.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lvchao.common.constant.PageConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * FEBS工具类
 *
 * @author Heisai
 */
@Slf4j
public class FormatUtil {

    /**
     * 驼峰转下划线
     *
     * @param value 待转换值
     * @return 结果
     */
    public static String camelToUnderscore(String value) {
        if (StringUtils.isBlank(value))
            return value;
        String[] arr = StringUtils.splitByCharacterTypeCamelCase(value);
        if (arr.length == 0)
            return value;
        StringBuilder result = new StringBuilder();
        IntStream.range(0, arr.length).forEach(i -> {
            if (i != arr.length - 1)
                result.append(arr[i]).append("_");
            else
                result.append(arr[i]);
        });
        return StringUtils.lowerCase(result.toString());
    }

    /**
     * 下划线转驼峰
     *
     * @param value 待转换值
     * @return 结果
     */
    public static String underscoreToCamel(String value) {
        StringBuilder result = new StringBuilder();
        String[] arr = value.split("_");
        for (String s : arr) {
            result.append((String.valueOf(s.charAt(0))).toUpperCase()).append(s.substring(1));
        }
        return result.toString();
    }

    /**
     * 判断是否为 ajax请求
     *
     * @param request HttpServletRequest
     * @return boolean
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }

    /**
     * 正则校验
     *
     * @param regex 正则表达式字符串
     * @param value 要匹配的字符串
     * @return 正则校验结果
     */
    public static boolean match(String regex, String value) {
    	if (value ==null) {
    		return false;
		}
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     * 设置响应
     *
     * @param response    HttpServletResponse
     * @param contentType content-type
     * @param status      http状态码
     * @param value       响应内容
     * @throws IOException IOException
     */
    public static void makeResponse(HttpServletResponse response, String contentType,
                                    int status, Object value) throws IOException {
        response.setContentType(contentType);
        response.setStatus(status);
        response.getOutputStream().write(JSONObject.toJSONString(value).getBytes());
    }

    /**
     * 封装前端分页表格所需数据
     *
     * @param pageInfo pageInfo
     * @return Map<String, Object>
     */
    public static Map<String, Object> getDataTable(IPage<?> pageInfo) {
        Map<String, Object> data = new HashMap<>();
        if (pageInfo == null) {
			data.put(PageConstant.DATA, new String[]{});
			data.put(PageConstant.TOTAL, 0);
			data.put(PageConstant.PAGE_COUNT, 0);
		} else {
			data.put(PageConstant.DATA, pageInfo.getRecords());
			data.put(PageConstant.TOTAL, pageInfo.getTotal());
			data.put(PageConstant.PAGE_COUNT, pageInfo.getPages());
			data.put(PageConstant.PAGE_SIZE, pageInfo.getSize());
			data.put(PageConstant.CURRENT_PAGE, pageInfo.getCurrent());
		}
		return data;
    }

    /**
     * 判断是否包含中文
     *
     * @param value 内容
     * @return 结果
     */
    public static boolean containChinese(String value) {
    	if (value == null) {
    		return false;
		}
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(value);
        return m.find();
    }

	public static boolean isPhoneNumber(String value) {
		return match("^1(3|4|5|6|7|8|9)\\d{9}$", value);
	}


	/**
	 * 微信昵称过滤表情符号
	 * @param str
	 * @return
	 */
	public static String filterEmoji(String str){
			if(str == null || str.length() == 0){
				return "";
			}
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<str.length();i++){
				int ch = str.charAt(i);
				int min = Integer.parseInt("E001", 16);
				int max = Integer.parseInt("E537", 16);
				if(ch >= min && ch <= max){
					sb.append("");
				}else{
					sb.append((char)ch);
				}
			}
			return sb.toString();
		}

		/**
		 * 过滤昵称特殊表情
		 */
		public static String filterName(String name) {
			if(name==null){
				return null;
			}
			if("".equals(name.trim())){
				return "";
			}

			Pattern patter = Pattern.compile("[a-zA-Z0-9\u4e00-\u9fa5]");
			Matcher match = patter.matcher(name);

			StringBuffer buffer = new StringBuffer();

			while (match.find()) {
				buffer.append(match.group());
			}
			return buffer.toString();
		}



	}
