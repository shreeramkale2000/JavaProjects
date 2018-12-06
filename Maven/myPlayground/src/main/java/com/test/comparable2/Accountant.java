package com.test.comparable2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Accountant {
	private List<Invoice> invoices = new ArrayList<Invoice>();

	public void addInvoices(List<Invoice> invoices) {
		this.invoices.addAll(invoices);
	}

	public void addInvoice(Invoice invoice1) {
		invoices.add(invoice1);
	}

	public Invoice findMinimumInvoice() {
		Invoice invoice = null;
		invoice = Collections.min(invoices);
		return invoice;
	}

}