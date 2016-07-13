package com.whiteblog.service;

import java.util.HashMap;
import java.util.Map;


public class EncryptServiceImpl { 
	public static Map<Integer, String> encrypteMap = new HashMap<Integer, String>();
	public static Map<String, Integer> decrypteMap = new HashMap<String, Integer>();
	public EncryptServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */ 
	public static String convert(String inStr){

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}

	
	//判断是否是数字
	public final static boolean isNumeric(String s) {  
        if (s != null && !"".equals(s.trim()))  
            return s.matches("^[0-9]*$");  
        else  
            return false;  
    }  

}
