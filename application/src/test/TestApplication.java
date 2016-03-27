package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.test.job.application.Application;
import com.test.job.application.invoice.Invoice;
import com.test.job.constant.Constant;
import com.test.job.util.FileReadReturnWithError;
import com.test.job.util.FileUtill;

public class TestApplication {

	@Test
	public void testValidInvoice() {
		 Invoice invoice = new Invoice();
		 invoice.setClientId("G123");
		 invoice.setDate("12-03-2013");
		 invoice.setInvoiceAmount(3000L);
		 invoice.setInvoiceNumber(1);
		 assertEquals(false,invoice.isValidInvoice().length()==0);
	}
	
	@Test
	public void testConvertListIntoMapByDate() {
		 List<Invoice> invoices = new ArrayList<Invoice>();
		 Invoice invoice1 = new Invoice();
		 invoice1.setClientId("D124");
		 invoice1.setDate("12-03-2013");
		 invoice1.setDateMonth(03);
		 invoice1.setDateYear(2013);
		 invoice1.setInvoiceAmount(3000L);
		 invoice1.setInvoiceNumber(1);
		 invoice1.setTaxEC(100L);
		 invoice1.setTaxFRT(100L);
		 invoice1.setTaxST(100L);
		 invoice1.setTaxWTC(100L);
		 invoices.add(invoice1);
		 
		 Invoice invoice2 = new Invoice();
		 invoice2.setClientId("I123");
		 invoice2.setDate("12-03-2013");
		 invoice2.setDateMonth(03);
		 invoice2.setDateYear(2013);
		 invoice2.setInvoiceAmount(3000L);
		 invoice2.setInvoiceNumber(1);
		 invoice2.setTaxEC(100L);
		 invoice2.setTaxFRT(100L);
		 invoice2.setTaxST(100L);
		 invoice2.setTaxWTC(100L);
		 invoices.add(invoice2);
		 Map<String,Invoice> map = Application.convertInvoiceListIntoMap(invoices, "date", null);
		 Assert.assertNotNull(map);
		 assertEquals(1,map.size());
	}
	
	@Test
	public void testConvertListIntoMapByClient() {
		 List<Invoice> invoices = new ArrayList<Invoice>();
		 Invoice invoice1 = new Invoice();
		 invoice1.setClientId("D124");
		 invoice1.setDate("12-03-2013");
		 invoice1.setDateMonth(03);
		 invoice1.setDateYear(2013);
		 invoice1.setInvoiceAmount(3000L);
		 invoice1.setInvoiceNumber(1);
		 invoice1.setTaxEC(100L);
		 invoice1.setTaxFRT(100L);
		 invoice1.setTaxST(100L);
		 invoice1.setTaxWTC(100L);
		 invoices.add(invoice1);
		 
		 Invoice invoice2 = new Invoice();
		 invoice2.setClientId("I123");
		 invoice2.setDate("12-03-2013");
		 invoice2.setDateMonth(03);
		 invoice2.setDateYear(2013);
		 invoice2.setInvoiceAmount(3000L);
		 invoice2.setInvoiceNumber(1);
		 invoice2.setTaxEC(100L);
		 invoice2.setTaxFRT(100L);
		 invoice2.setTaxST(100L);
		 invoice2.setTaxWTC(100L);

		 invoices.add(invoice2);
		 
		 Map<String,Invoice> map = Application.convertInvoiceListIntoMap(invoices, "client", "03-2013");
		 Assert.assertNotNull(map);
		 assertEquals(2,map.size());
	}
	
	@Test
	public void testListFromFile() {
		FileReadReturnWithError returnFileWithError = FileUtill.getInvoiceFromFile(Constant.FILEPATH+"my_program invoice_details.csv");
		 assertEquals(7,returnFileWithError.getInvoiceList().size());
	}
	
}
