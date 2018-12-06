package com.test.comparable2;

public class Invoice implements Comparable<Invoice>{

	private int amount;
	
	public Invoice (int amount) {
		this.amount = amount;
	}
	
	@Override
	public int compareTo(Invoice invoice) {
		return this.amount - invoice.amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
