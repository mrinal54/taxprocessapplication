package com.test.job.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import com.test.job.application.invoice.Invoice;

public class FileUtill {

	public static List<Invoice> getInvoiceFromFile(String filePath) {
		BufferedReader br = null;
		boolean isHeaderLineProcessed = false;
		String line = "";
		String splitDelimeter = ",";
		int lineNumber = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		List<Invoice> invoices = new ArrayList<Invoice>();
		try {

			br = new BufferedReader(new FileReader("src/resources/invoice_details.csv"));
			while ((line = br.readLine()) != null) {
				String[] invoiceLine = line.split(splitDelimeter);
				if (isHeaderLineProcessed) {
					lineNumber++;
					Invoice invoice = new Invoice();
					invoice.setInvoiceNumber(Integer.parseInt(invoiceLine[0]));
					invoice.setClientId(invoiceLine[1]);
					invoice.setInvoiceAmount(Long.parseLong(invoiceLine[3]));
					
					Date date = formatter.parse(invoiceLine[2]);
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					int month = cal.get(Calendar.MONTH) + 1;
					int year = cal.get(Calendar.YEAR);
					invoice.setDateMonth(month);
					invoice.setDateYear(year);

					invoices.add(invoice);
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
		return invoices;
	}
}
