package com.util;

public class StringUtil {

		public String getString(String str){
			if(str==null){
				return "";
			}else if(!"".equals(str)){
				str=str.trim();
			}
			return str;
		}
		public static String getString(Object obj){
			String newString="";
			if(obj==null){
				return "";
			}else if(!"".equals(obj)){
				newString=obj.toString().trim();
			}
			return newString;
		}
}
