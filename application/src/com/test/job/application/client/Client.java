package com.test.job.application.client;

import java.util.ArrayList;
import java.util.List;

import com.test.job.application.invoice.Invoice;

public class Client {

	private int clientId;
	private List<Invoice> invoices = new ArrayList<Invoice>();
	
	
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public List<Invoice> getInvoices() {
		return invoices;
	}
	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", invoices=" + invoices + "]";
	}
}
