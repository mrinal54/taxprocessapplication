package com.test.job.application;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.test.job.application.invoice.Invoice;
import com.test.job.constant.Constant;
import com.test.job.util.FileReadReturnWithError;
import com.test.job.util.FileUtill;
import com.test.job.util.Validation;

public class Application {
	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		while(true){
			if(Constant.MY_PROGRAM_INVOICE_DETAILS_CSV.equals(sc.nextLine())){
				String fileName = Constant.MY_PROGRAM_INVOICE_DETAILS_CSV;
				run(sc,fileName);
			}else{
				System.out.println("Enter Valid Filename");
			}
		}
	}

	private static void run(Scanner sc, String fileName) {
		Long totalInvoiceAmount = 0L;
		Long totalST = 0L;
		Long totalEC = 0L;
		Long totalFRT = 0L;
		Long totalWTC = 0L;
		FileReadReturnWithError returnFileWithError = FileUtill.getInvoiceFromFile(Constant.FILEPATH+fileName);
		if (!returnFileWithError.isErrorInFile()) {
			Map<String, Invoice> dateInvoiceMap = convertInvoiceListIntoMap(returnFileWithError.getInvoiceList(),
					Constant.DATE, null);
			printReport(totalInvoiceAmount, totalST, totalEC, totalFRT, totalWTC, dateInvoiceMap, Constant.MONTHLY);
			while (true) {
				String userInput = sc.nextLine();
				if (Constant.AGGREGATED.equals(userInput)) {
					printReport(totalInvoiceAmount, totalST, totalEC, totalFRT, totalWTC, dateInvoiceMap,
							Constant.MONTHLY);
				} else if (Constant.EXIT.equals(userInput)) {
					System.exit(0);
				} else if (Validation.isValidUserInputMonth(userInput)) {
					Map<String, Invoice> clilentInvoiceMap = convertInvoiceListIntoMap(returnFileWithError.getInvoiceList(), Constant.CLIENT, userInput);
					printReport(totalInvoiceAmount, totalST, totalEC, totalFRT, totalWTC, clilentInvoiceMap,
							Constant.CLIENT);
				} else {
					System.out.println("Invalid Command:(month in mm-yyyy format, aggregated and exit)");
				}

			}
			// String aggregate = sc.nextLine();
			//
		} else {
			System.out.println("The input file has invalid data please fix following errors and try again:");
			for (Entry<Integer,StringBuffer> entry : returnFileWithError.getErrorMap().entrySet()) {
				System.out.println("Line " + entry.getKey() + " - " + entry.getValue().toString());
			}
		}
	}

	private static void printReport(Long totalInvoiceAmount, Long totalST, Long totalEC, Long totalFRT,
			Long totalWTC, Map<String, Invoice> dateInvoiceMap, String monthlyOrClient) {
		if (Constant.MONTHLY.equals(monthlyOrClient)) {
			System.out.println("Month | Total Invoice Amount | ST | EC | FRT | WTC");
		} else if (Constant.CLIENT.equals(monthlyOrClient)) {
			System.out.println("Client Number | Total Invoice Amount | ST | EC | FRT | WTC");
		}
		for (Entry<String, Invoice> entry : dateInvoiceMap.entrySet()) {
			System.out.println(entry.getKey() + " | " + entry.getValue().getInvoiceAmount() + " | "
					+ entry.getValue().getTaxST() + " | " + entry.getValue().getTaxEC() + " | "
					+ entry.getValue().getTaxFRT() + " | " + entry.getValue().getTaxWTC());
			totalInvoiceAmount = totalInvoiceAmount + entry.getValue().getInvoiceAmount();
			totalEC = totalEC + entry.getValue().getTaxEC();
			totalST = totalST + entry.getValue().getTaxST();
			totalFRT = totalFRT + entry.getValue().getTaxFRT();
			totalWTC = totalWTC + entry.getValue().getTaxWTC();
		}
		if (Constant.MONTHLY.equals(monthlyOrClient)) {
			System.out.println("Total | | " + totalST + " | " + totalEC + " | " + totalFRT + " | " + totalWTC);
		}
		System.out.println("view month? (mm-yyyy / aggregated)");
	}

	public static Map<String, Invoice> convertInvoiceListIntoMap(List<Invoice> invoices, String key,
			String userInputDate) {
		Map<String, Invoice> invoiceMap = null;
		if (Constant.DATE.equals(key)) {
			invoiceMap = new HashMap<String, Invoice>();
			for (Invoice invoice : invoices) {
				String keydateFormat = new DecimalFormat("00").format(invoice.getDateMonth()) + "-"
						+ String.valueOf(invoice.getDateYear());
				populateMap(invoiceMap, invoice, keydateFormat);
			}
		} else if (Constant.CLIENT.equals(key)) {
			invoiceMap = new HashMap<String, Invoice>();
			for (Invoice invoice : invoices) {
				String keydateFormat = new DecimalFormat("00").format(invoice.getDateMonth()) + "-"
						+ String.valueOf(invoice.getDateYear());
				if (userInputDate != null) {
					if (userInputDate.equals(keydateFormat)) {
						String keyClientId = invoice.getClientId();
						populateMap(invoiceMap, invoice, keyClientId);
					}
				}
			}
		}
		return invoiceMap;
	}

	private static void populateMap(Map<String, Invoice> invoiceMap, Invoice invoice, String keyMap) {
		if (invoiceMap.containsKey(keyMap)) {
			Invoice existingInvoice = getUpdatedInvoice(invoiceMap, invoice, keyMap);
			invoiceMap.put(keyMap, existingInvoice);
		} else {
			invoiceMap.put(keyMap, invoice);
		}
	}

	private static Invoice getUpdatedInvoice(Map<String, Invoice> invoiceMap, Invoice invoice, String keydateFormat) {
		Invoice existingInvoice = invoiceMap.get(keydateFormat);
		existingInvoice.setInvoiceAmount(existingInvoice.getInvoiceAmount() + invoice.getInvoiceAmount());
		existingInvoice.setTaxFRT(existingInvoice.getTaxFRT() + invoice.getTaxFRT());
		existingInvoice.setTaxEC(existingInvoice.getTaxEC() + invoice.getTaxEC());
		existingInvoice.setTaxST(existingInvoice.getTaxST() + invoice.getTaxST());
		existingInvoice.setTaxWTC(existingInvoice.getTaxWTC() + invoice.getTaxWTC());
		return existingInvoice;
	}
}
