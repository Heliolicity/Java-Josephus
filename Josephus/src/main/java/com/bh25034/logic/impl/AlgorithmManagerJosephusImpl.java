package com.bh25034.logic.impl;

import java.util.Vector;

import com.bh25034.logic.AlgorithmManagerJosephus;
import com.bh25034.beans.Person;

public class AlgorithmManagerJosephusImpl implements AlgorithmManagerJosephus {
	
	private Vector<Person> people;
	private int numberOfPeople;
	private int killInterval;
	private int count;
	private int orderKilled;
	private String resultText;
	
	public AlgorithmManagerJosephusImpl(
			int numberOfPeople, 
			int killInterval) {
		
		this.numberOfPeople = numberOfPeople;
		this.killInterval = killInterval;
		this.count = 0;
		this.orderKilled = 0;
		
	}
	
	public void run() {
		
		this.resultText = "";
		
		if (! (this.people == null)) {
			
			for (int a = 0; a < this.people.size(); a ++) {
				
				this.count ++;
				
				if (this.count % this.killInterval == 0) {
					
					this.resultText += "FLAGGING " + this.people.get(a).getPosition() + " FOR DEATH\n";
					//this.pl(this.resultText);
					this.people.get(a).setKilled(true);
					this.orderKilled ++;
					this.people.get(a).setOrderKilled(this.orderKilled);
					
				}
				
			}
			
		}
		
		for (int b = 0; b < this.people.size(); b ++) 
			
			if (this.people.get(b).isKilled()) 
				
				this.people.remove(b);
		
	}
	
	public Vector<Person> getPeople() {
		return people;
	}

	public void setPeople(Vector<Person> people) {
		this.people = people;
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public int getKillInterval() {
		return killInterval;
	}

	public void setKillInterval(int killInterval) {
		this.killInterval = killInterval;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getResultText() {
		return resultText;
	}

	public void setResultText(String resultText) {
		this.resultText = resultText;
	}

	private void pl(String s) { System.out.println(s); }
	
}
