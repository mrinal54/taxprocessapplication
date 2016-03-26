package com.test.job.application;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.test.job.application.invoice.Invoice;
import com.test.job.util.FileUtill;

public class Application {

	public static void main(String[] args) throws ParseException {
		List<Invoice> invoices = FileUtill.getInvoiceFromFile("src/resources/invoice_details.csv");
		Map<String, Invoice> dateInvoiceMap = convertInvoiceListIntoMap(invoices, "date");
		Map<String, Invoice> clilentInvoiceMap = convertInvoiceListIntoMap(invoices, "client");

		for (Entry<String, Invoice> entry : dateInvoiceMap.entrySet()) {
			System.out.println(entry.getKey() + "|" + entry.getValue().getInvoiceAmount());
		}
	}

	private static Map<String, Invoice> convertInvoiceListIntoMap(List<Invoice> invoices, String key) {
		Map<String, Invoice> invoiceMap = null ;
		if ("date".equals(key)) {
			invoiceMap = new HashMap<String, Invoice>();
			for (Invoice invoice : invoices) {
				String keydateFormat = String.valueOf(invoice.getDateMonth()) + "-"	+ String.valueOf(invoice.getDateYear());
				populateMap(invoiceMap, invoice, keydateFormat);
			}
		} else if ("client".equals(key)) {
			invoiceMap = new HashMap<String, Invoice>();
			for (Invoice invoice : invoices) {
				String keyClientId = invoice.getClientId();
				populateMap(invoiceMap, invoice, keyClientId);
			}
		}
		return invoiceMap;
	}

	private static void populateMap(Map<String, Invoice> invoiceMap, Invoice invoice, String keydateFormat) {
		if (invoiceMap.containsKey(keydateFormat)) {
			Invoice existingInvoice = getUpdatedInvoice(invoiceMap, invoice, keydateFormat);
			invoiceMap.put(keydateFormat, existingInvoice);
		} else {
			invoiceMap.put(keydateFormat, invoice);
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
