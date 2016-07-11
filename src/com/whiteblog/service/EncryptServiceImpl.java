package com.whiteblog.service;

import java.security.MessageDigest;
<<<<<<< HEAD

public class EncryptServiceImpl {
	
=======
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


public class EncryptServiceImpl {
	private final static String[] hexDigits = { "0", "1", "2", "3", "4",
		   "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" }; 
	public static Map<Integer, String> encrypteMap = new HashMap<Integer, String>();
	public static Map<String, Integer> decrypteMap = new HashMap<String, Integer>();
	public EncryptServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
>>>>>>> 8f46b5bd902881449fdf9af027304db0247f9f5b
	/**
	 * 参数加密，对于int类型
	 * @param parm
	 * @return
	 */
<<<<<<< HEAD
	static int parmEnrypt(int parm){
		return 1;
=======
	public static int parmIntEnrypt(int parm){
		String t = Integer.toString(parm);
		System.out.println(t);
		int i = Integer.parseInt(convertInt(t));
		System.out.println(i);
		t = Integer.toString(i);
		i = Integer.parseInt(convertInt(t));
		System.out.println(i);
		return i;
>>>>>>> 8f46b5bd902881449fdf9af027304db0247f9f5b
	}
	/**
	 * 参数加密，对于String 类型
	 * @param parm
	 * @return
	 */
<<<<<<< HEAD
	static String parmEncypt(String parm){
		String res = "";
		return res;
=======
	public static String parmStringEncypt(String parm){
		return convert(parm);
>>>>>>> 8f46b5bd902881449fdf9af027304db0247f9f5b
	}
	/**
	 * 参数解密对于int类型
	 * @param parm
	 * @return
	 */
<<<<<<< HEAD
	static int parmDecrypt(int parm){
		
		return 1;
=======
	public static int parmIntDecrypt(String parm){
		String tmp = convert(parm);
		int res = Integer.parseInt(tmp);
		return res;
>>>>>>> 8f46b5bd902881449fdf9af027304db0247f9f5b
	}
	/**
	 * 参数解密，对于String类型
	 * @param parm
	 * @return
	 */
<<<<<<< HEAD
	static String parmDecrypt(String parm){
		String res = "";
		return res;
=======
	public static String parmStringDecrypt(String parm){
		return convert(parm);
>>>>>>> 8f46b5bd902881449fdf9af027304db0247f9f5b
	}
	
	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */ 
<<<<<<< HEAD
	public static String convert(String inStr){
=======
	private static String convert(String inStr){
>>>>>>> 8f46b5bd902881449fdf9af027304db0247f9f5b

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}
<<<<<<< HEAD

	// 测试主函数
	public static void main(String[] args) {
		String s = new String("123");
		System.out.println("原始：" + s);
		System.out.println("加密的：" + convert(s));
		System.out.println("解密的：" + convert(convert(s)));

	}
=======
	
	private static String convertInt(String inStr){
		char [] a = inStr.toCharArray();
		for(int i = 0; i < a.length; i++)
			/*a[i] = (char) ('0' + (a[i] ^ '1') % 10);*/
			a[i] = (char) (a[i] ^ '0');
		String res = new String (a);
		return res;
	}
	
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
		    resultSb.append(byteToHexString(b[i]));
		}
	    return resultSb.toString();
	}
	/** 将一个字节转化成十六进制形式的字符串 */
	private static String byteToHexString(byte b) {
	   int n = b;
	   if (n < 0)
	    n = 256 + n;
	   int d1 = n / 16;
	   int d2 = n % 16;
	   return hexDigits[d1] + hexDigits[d2];
	}
	//判断是否是数字
	public final static boolean isNumeric(String s) {  
        if (s != null && !"".equals(s.trim()))  
            return s.matches("^[0-9]*$");  
        else  
            return false;  
    }  

>>>>>>> 8f46b5bd902881449fdf9af027304db0247f9f5b
}
