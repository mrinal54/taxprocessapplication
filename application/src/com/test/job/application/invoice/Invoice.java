package com.test.job.application.invoice;

import java.util.HashMap;
import java.util.Map;

import com.test.job.util.Validation;

public class Invoice {

	private int invoiceNumber;
	private String clientId;
	private Long invoiceAmount;
	private int dateMonth;
	private int dateYear;
	private Long taxFRT;
	private Long taxST;
	private Long taxEC;
	private Long taxWTC;
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	/*
	 * private Map<Integer,String> errorMap = new HashMap<Integer,String>();
	 * 
	 * public Map<Integer, String> getErrorMap() { return errorMap; } public
	 * void setErrorMap(Map<Integer, String> errorMap) { this.errorMap =
	 * errorMap; }
	 */
	public Long getTaxFRT() {
		return taxFRT;
	}

	public void setTaxFRT(Long taxFRT) {
		this.taxFRT = taxFRT;
	}

	public Long getTaxST() {
		return taxST;
	}

	public void setTaxST(Long taxST) {
		this.taxST = taxST;
	}

	public Long getTaxEC() {
		return taxEC;
	}

	public void setTaxEC(Long taxEC) {
		this.taxEC = taxEC;
	}

	public Long getTaxWTC() {
		return taxWTC;
	}

	public void setTaxWTC(Long taxWTC) {
		this.taxWTC = taxWTC;
	}

	public int getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Long getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(Long invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public int getDateMonth() {
		return dateMonth;
	}

	public void setDateMonth(int dateMonth) {
		this.dateMonth = dateMonth;
	}

	public int getDateYear() {
		return dateYear;
	}

	public void setDateYear(int dateYear) {
		this.dateYear = dateYear;
	}

	@Override
	public String toString() {
		return "Invoice [invoiceNumber=" + invoiceNumber + ", clientId=" + clientId + ", invoiceAmount=" + invoiceAmount
				+ ", dateMonth=" + dateMonth + ", dateYear=" + dateYear + "]";
	}

	public StringBuffer isValidInvoice() {
		StringBuffer sb = new StringBuffer("");
		if (!Validation.isValidClientId(this.clientId)) {
			if (sb.length() == 0) {
				sb.append("Client number is invalid");
			} else {
				sb.append(",Client number is invalid");
			}
		}
		if (!Validation.isValidInvoiceAmount(this.invoiceAmount)) {
			if (sb.length() == 0) {
				sb.append("Invoice amount is invalid");
			} else {
				sb.append(",Invoice amount is invalid");
			}
		}
		if (!Validation.isDuplicateInvoiceNumber(this.invoiceNumber)) {
			if (sb.length() == 0) {
				sb.append("Duplicate invoice number");
			} else {
				sb.append(",Duplicate invoice number");
			}
		}
		if (!Validation.isValidInvoiceDate(this.date)) {
			if (sb.length() == 0) {
				sb.append("Invalid date");
			} else {
				sb.append(",Invalid date");
			}
		}
		return sb;
	}

}
