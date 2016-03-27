package com.test.job.application.tax.impl;

import com.test.job.application.tax.Tax;

public class STaxImpl implements Tax {
	
	@Override
	public Long calculateTax(Long invoiceAmount) {
		Long serviceTax =(long) Math.ceil( (invoiceAmount*10)/100.0);
		return serviceTax;
	}

}
