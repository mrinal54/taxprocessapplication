package com.test.job.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.job.application.invoice.Invoice;

public class FileReadReturnWithError {
	
	private List<Invoice> invoiceList = new ArrayList<Invoice>();
	private Map<Integer,StringBuffer> errorMap = new HashMap<Integer,StringBuffer>();
	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}
	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}
	public Map<Integer, StringBuffer> getErrorMap() {
		return errorMap;
	}
	public void setErrorMap(Map<Integer, StringBuffer> errorMap) {
		this.errorMap = errorMap;
	}
	
	public boolean isErrorInFile(){
		if(this.errorMap.size()!=0){
			return true;
		}else{
			return false;
		}
	}

}
