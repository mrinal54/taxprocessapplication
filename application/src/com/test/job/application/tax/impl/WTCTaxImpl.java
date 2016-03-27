package com.test.job.application.tax.impl;

import com.test.job.application.tax.Tax;

public class WTCTaxImpl implements Tax {
	
	@Override
	public Long calculateTax(Long invoiceAmount) {
		Long wtcTax = 100L;
		return wtcTax;
	}

}
