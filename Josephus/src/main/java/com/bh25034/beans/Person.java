package com.bh25034.beans;

public class Person {

	private int position;
	private boolean killed;
	private int orderKilled;
	
	public Person(int position, boolean killed, int orderKilled) {
		super();
		this.position = position;
		this.killed = killed;
		this.orderKilled = orderKilled;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isKilled() {
		return killed;
	}

	public void setKilled(boolean killed) {
		this.killed = killed;
	}

	public int getOrderKilled() {
		return orderKilled;
	}

	public void setOrderKilled(int orderKilled) {
		this.orderKilled = orderKilled;
	}
	
}
