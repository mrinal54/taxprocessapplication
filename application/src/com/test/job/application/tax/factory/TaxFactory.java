package com.test.job.application.tax.factory;

import com.test.job.application.tax.Tax;
import com.test.job.application.tax.impl.ECTaxImpl;
import com.test.job.application.tax.impl.FRTaxImpl;
import com.test.job.application.tax.impl.STaxImpl;
import com.test.job.application.tax.impl.WTCTaxImpl;
import com.test.job.constant.Constant;

public class TaxFactory {
	
	public static Tax getTax(String criteria){
		if (Constant.FRT.equals(criteria))
		      return new FRTaxImpl();
		    else if (Constant.ST.equals(criteria))
		      return new STaxImpl();
		    else if (Constant.EC.equals(criteria))
		      return new ECTaxImpl();
		    else if (Constant.WTC.equals(criteria))
			      return new WTCTaxImpl();
		 
		    return null;
	}

}
