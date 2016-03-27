package com.test.job.application.tax.impl;

import com.test.job.application.tax.Tax;

public class ECTaxImpl implements Tax {
	
	@Override
	public Long calculateTax(Long invoiceAmount) {
		Long ecTax = (long) Math.ceil((invoiceAmount*3)/100.0);
		return ecTax;
	}

	
}
