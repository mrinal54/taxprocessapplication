package com.test.job.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.test.job.constant.Constant;

public class Validation {
	
	public static  Set<Integer> invoiceNumberSet = new HashSet<Integer>(); 
	
	public  static boolean isValidClientId(String clientId){
		Pattern pattern = Pattern.compile(Constant.CLIENT_REGEX);
		Matcher matcher = pattern.matcher(clientId);
		if(matcher.find())
			return true;
		return false;
	} 
	
	public  static boolean isValidInvoiceAmount(Long invoiceAmount){
		return (invoiceAmount>0);
	} 
	public  static boolean isValidInvoiceDate(String  date){
		Pattern pattern = Pattern.compile(Constant.DATE_REGEX);
		Matcher matcher = pattern.matcher(date);
		if(matcher.find()){
			String[] parts = date.split("-");
			String days = parts[0];
			days = days.replaceAll("\\s+","");
			String months = parts[1];
			months = months.replaceAll("\\s+","");
			if(Integer.parseInt(months)==2&&(Integer.parseInt(days)>29)){
				return false;
			}
			if(Integer.parseInt(months)>12){
				return false;
			}
			return true;
		}
		return false;
	} 
	public  static boolean isDuplicateInvoiceNumber(int invoiceNuber){
		return invoiceNumberSet.add(invoiceNuber);
	} 
	
	public static boolean isValidUserInputMonth(String month){
		Pattern pattern = Pattern.compile(Constant.USER_INPUT_MONTH_REGEX);
		Matcher matcher = pattern.matcher(month);
		if(matcher.find())
			return true;
		return false;
	}

}
