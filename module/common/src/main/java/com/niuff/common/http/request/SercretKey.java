package com.niuff.common.http.request;

public class SercretKey
{
	//注意: 这里的password(秘钥必须是16位的)
	//私钥   AES固定格式为128/192/256 bits.即：16/24/32bytes。DES固定格式为128bits，即8bytes。
	private  static final String KEY="App.baoDuitong@2016.Com";
	
	public static String getKey()
	{
		return KEY.substring(0,16);
	}
	
	public static void main(String[]args)
	{
		System.out.println(getKey());
	}
	
	
}
