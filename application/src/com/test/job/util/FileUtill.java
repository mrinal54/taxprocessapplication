package com.test.job.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.test.job.application.invoice.Invoice;
import com.test.job.application.tax.Tax;
import com.test.job.application.tax.factory.TaxFactory;
import com.test.job.constant.Constant;
import com.test.job.exception.InvalidInvoiceException;

public class FileUtill {

	public static FileReadReturnWithError getInvoiceFromFile(String filePath){
		BufferedReader br = null;
		boolean isHeaderLineProcessed = false;
		String line = "";
		String splitDelimeter = ",";
		int lineNumber = 0;
		FileReadReturnWithError fileReadReturnWithError = new FileReadReturnWithError();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Map<Integer,StringBuffer> errorMap = new HashMap<Integer,StringBuffer>();
		List<Invoice> invoices = new ArrayList<Invoice>();
		try {

			br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null) {
				StringBuffer errorBuffer = new StringBuffer("");
				String[] invoiceLine = line.split(splitDelimeter);
				if (isHeaderLineProcessed) {
					lineNumber++;
					Invoice invoice = new Invoice();
					String invoiceNumber = invoiceLine[0];
					invoiceNumber=invoiceNumber.replaceAll(Constant.WHITESPCE_REGEX,"");
					invoice.setInvoiceNumber(Integer.parseInt(invoiceNumber));
					String invoiceClient = invoiceLine[1];
					invoiceClient=invoiceClient.replaceAll(Constant.WHITESPCE_REGEX,"");
					invoice.setClientId(invoiceLine[1]);
					String invoiceAmount = invoiceLine[3];
					invoiceAmount=invoiceAmount.replaceAll(Constant.WHITESPCE_REGEX,"");
					invoice.setInvoiceAmount(Long.parseLong(invoiceAmount));
					String invoiceDate = invoiceLine[2];
					invoiceDate=invoiceDate.replaceAll(Constant.WHITESPCE_REGEX,"");
					invoice.setDate(invoiceDate);
					errorBuffer = invoice.isValidInvoice();
					if(Validation.isValidInvoiceDate(invoiceDate)){
						Date date = formatter.parse(invoiceDate);
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						int month = cal.get(Calendar.MONTH) + 1;
						int year = cal.get(Calendar.YEAR);
						invoice.setDateMonth(month);
						invoice.setDateYear(year);
					}
                    Invoice invoiceUpdated = updateTax(invoice);
					invoices.add(invoiceUpdated);
					if(errorBuffer.length()!=0){
						errorMap.put(lineNumber, errorBuffer);
					}
					fileReadReturnWithError.setErrorMap(errorMap);
				} else {
					isHeaderLineProcessed = true;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		fileReadReturnWithError.setInvoiceList(invoices);		
		return  fileReadReturnWithError;
	}

	private static Invoice updateTax(Invoice invoice) {
		Long invoiceAmount = invoice.getInvoiceAmount();
		
		if(isDomestic(invoice)){
			Tax serviceTax = TaxFactory.getTax(Constant.ST);
			invoice.setTaxST(serviceTax.calculateTax(invoiceAmount));
			
			Tax ecTax = TaxFactory.getTax(Constant.EC);
			invoice.setTaxEC(ecTax.calculateTax(serviceTax.calculateTax(invoiceAmount)));
			
			invoice.setTaxFRT(0L);
			invoice.setTaxWTC(0L);
		}else{
			invoice.setTaxST(0L);
			invoice.setTaxEC(0L);
			
			Tax frTax = TaxFactory.getTax(Constant.FRT);
			invoice.setTaxFRT(frTax.calculateTax(invoiceAmount));
			
			Tax wtcTax = TaxFactory.getTax(Constant.WTC);
			invoice.setTaxWTC(wtcTax.calculateTax(invoiceAmount));
		}
		return invoice;
	}
	
	//Test case
	private static boolean isDomestic(Invoice invoice) {
		Pattern pattern = Pattern.compile(Constant.DOMESTIC_CLIENT_REGEX);
		Matcher matcher = pattern.matcher(invoice.getClientId());
		if(matcher.find())
			return true;
		return false;
	}
}
