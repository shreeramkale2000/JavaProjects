package com.test.comparable2;

public class TestComparable2 {

	public static void main(String[] args) {
		Accountant acc = new Accountant();
		acc.addInvoice(new Invoice(10));
		acc.addInvoice(new Invoice(9));
		
		System.out.println(acc.findMinimumInvoice().getAmount());
	}

}
