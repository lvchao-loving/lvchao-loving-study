package com.lvchao.common.utils;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author edgardeng
 * @datetime 2019/12/04
 * @description
 */
public class HttpServletRequestUtil {
	/**
	 * 获取请求中的head中的clientId　clientSecrete
	 * @param request
	 * @return
	 */
	public static String[] basicAuthClient(HttpServletRequest request) {
		String[] client = null;
		try {
				String header = request.getHeader(HttpHeaders.AUTHORIZATION);
				if (header == null) {
					return null;
				}
				byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
				byte[] decoded;
				decoded = Base64.getDecoder().decode(base64Token);
				String token = new String(decoded, StandardCharsets.UTF_8);
				int delim = token.indexOf(":");
				if (delim != -1) {
					client = new String[]{token.substring(0, delim), token.substring(delim + 1)};
				}
		} catch (Exception ignore) {
		}
		return client;
	}
}
