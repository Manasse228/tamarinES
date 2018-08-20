package com.TamarinES.Utility;

import java.util.Date;


public  final class Convertion {
	
	public static boolean checkStringNotNullNotEmpty(String val) {
		return (val != null && !val.trim().isEmpty() && (val.trim() != "") ) ? true : false;
	}
	
	public static boolean checkFloatGreatThanZero(Float val) {
		return (val > 0.0f) ? true : false;
	}
	
	public static boolean checkDateNotNull(Date val) {
		return (val != null) ? true : false;
	}	
	

}
