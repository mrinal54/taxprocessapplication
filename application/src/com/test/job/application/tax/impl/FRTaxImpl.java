package com.test.job.application.tax.impl;

import com.test.job.application.tax.Tax;

public class FRTaxImpl implements Tax {
	
	@Override
	public Long calculateTax(Long invoiceAmount) {
		Long frTax = (long) Math.ceil((invoiceAmount*5)/100.0);
		return frTax;
	}

}
