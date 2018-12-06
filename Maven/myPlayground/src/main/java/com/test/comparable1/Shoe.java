package com.test.comparable1;

public class Shoe implements Comparable<Shoe> {
	private int size;
	private String description;
	
	public Shoe(int size,String description){
		this.size = size;
		this.description = description;
	}

	@Override
	public int compareTo(Shoe shoe) {
		int comp = 0;
		if (this.size == shoe.size) {
			comp = this.description.compareTo(shoe.description);
		} else {
			comp = this.size - shoe.size;
		}
		return comp;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}