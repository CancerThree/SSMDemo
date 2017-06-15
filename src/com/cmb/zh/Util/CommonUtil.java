package com.cmb.zh.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
	
	private CommonUtil(){}
	
	public static boolean isFullyMatchRegex (String str, String pattern) {
		boolean res = false;
		
		if (str != null && pattern != null) {
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(str);
			res = m.matches();
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		String p = "\\w+";
		String s = "123@com";
		System.out.println(isFullyMatchRegex(s, p));
		System.out.println(isFullyMatchRegex(s, ""));
		System.out.println("true".equals(new Boolean(true).toString()));
	}
}
