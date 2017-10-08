package cn.itcast.utils;

import java.io.File;
import java.util.UUID;

public class FileUploadUtils {
	
	/**
	 * 工具类:得到上传文件的真实文件名
	 * 		C:\a.txt    ------>>   a.txt
	 * 例如:有可能上传文件的时候,得到的是一个URL完整路径,这个时候通过这个方法,截取得到真实的文件名
	 * @param fileName
	 * @return
	 */
	public static String getRealFileName(String fileName){
		int index = fileName.lastIndexOf("\\")+1;
		
		return fileName.substring(index);
	}
	
	/**
	 * 得到上传的文件的随机存储名:由于可能有很多人上传文件,可能存在文件名相同的情况,在服务器存储的内部,使用随机名存储,
	 * @param fileName
	 * @return
	 */
	public static String getUUIDFileName(String fileName){
		int index = fileName.lastIndexOf(".");
		//由于可能上传的文件没有后缀名,所以判断是否为 -1
		if(index != -1){
			return UUID.randomUUID() + fileName.substring(index);
		}else{
			return UUID.randomUUID().toString();
		}
	}
	
	/**
	 * 文件目录分离算法:通过产生一个hashcode随机值,根据hashcode随机值,创建相应的文件夹(路径)
	 * @param fileName
	 * @return
	 */
	public static String getRandomDirectory(String fileName){
		
		/*
		int hashcode = fileName.hashCode();   //得到文件名的hashcode值
		//int类型数据在内存中占据32位,转换成16进制数,就得到8位16进制数
		String hex = Integer.toHexString(hashcode);   //将int类型转换成16进制数
		
		return "/"+hex.charAt(0)+"/"+hex.charAt(1);	
		*/
		
		int hashcode = fileName.hashCode();
		//  0101 0110 1101 1001 0011 0110 0011
		System.out.println(Integer.toBinaryString(hashcode));   //将10进制转换为二进制 0 , 1
		//进行二进制转换运算
		int a = hashcode & 0xf;  //进行与运算,得到
		// 3
		System.out.println(a);
		
		hashcode = hashcode >>> 4;   // 二进制右移4为
		
		int b = hashcode & 0xf;
		// 6
		System.out.println(b);
		
		return "/"+a+"/"+b;
	}
	
	//测试
//	public static void main(String[] args){
//		String path = getRandomDirectory("a.txt");
//		
//		File file = new File("/home/wcq/day05_1");
//		File directory = new File(file,path);
//	
//		//判断文件夹是否存在,如果不存在,就创建这个文件夹
//		if(!directory.exists()){   
//			directory.mkdirs();
//		}
//	}
}







