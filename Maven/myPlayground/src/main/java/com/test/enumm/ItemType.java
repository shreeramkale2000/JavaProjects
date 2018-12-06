package com.test.enumm;

public enum ItemType {
	COMPANY_WIDE("Company"), DEPARTMENTAL("Departmental"), PROJECT_SPECIFIC("Project");
	
	private String itemCode;

	private ItemType(String dbcode) {
		this.itemCode = dbcode;
	}

	public String getItemCode() {
		return itemCode;
	}
}