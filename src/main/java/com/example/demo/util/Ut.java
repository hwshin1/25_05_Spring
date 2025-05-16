package com.example.demo.util;

public class Ut {
	public static boolean isEmptyOrNull(String str) {
		return str == null || str.trim().length() == 0;
	}
	
	public static Object f(String str, Object... args) {
		return String.format(str, args);
	}
}
