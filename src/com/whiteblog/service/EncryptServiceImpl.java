package com.whiteblog.service;

import java.security.MessageDigest;

public class EncryptServiceImpl {
	
	/**
	 * 参数加密，对于int类型
	 * @param parm
	 * @return
	 */
	static int parmEnrypt(int parm){
		return 1;
	}
	/**
	 * 参数加密，对于String 类型
	 * @param parm
	 * @return
	 */
	static String parmEncypt(String parm){
		String res = "";
		return res;
	}
	/**
	 * 参数解密对于int类型
	 * @param parm
	 * @return
	 */
	static int parmDecrypt(int parm){
		
		return 1;
	}
	/**
	 * 参数解密，对于String类型
	 * @param parm
	 * @return
	 */
	static String parmDecrypt(String parm){
		String res = "";
		return res;
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

	// 测试主函数
	public static void main(String[] args) {
		String s = new String("123");
		System.out.println("原始：" + s);
		System.out.println("加密的：" + convert(s));
		System.out.println("解密的：" + convert(convert(s)));

	}
}
