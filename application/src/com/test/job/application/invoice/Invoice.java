package com.test.job.application.invoice;

public class Invoice {
	
	private int invoiceNumber;
	private String clientId;
	private Long invoiceAmount;
	private int dateMonth;
	private int dateYear;
	private int taxFRT;
	private int taxST;
	private int taxEC;
	private int taxWTC;
	
	public int getTaxFRT() {
		return taxFRT;
	}
	public void setTaxFRT(int taxFRT) {
		this.taxFRT = taxFRT;
	}
	public int getTaxST() {
		return taxST;
	}
	public void setTaxST(int taxST) {
		this.taxST = taxST;
	}
	public int getTaxEC() {
		return taxEC;
	}
	public void setTaxEC(int taxEC) {
		this.taxEC = taxEC;
	}
	public int getTaxWTC() {
		return taxWTC;
	}
	public void setTaxWTC(int taxWTC) {
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
	

}
