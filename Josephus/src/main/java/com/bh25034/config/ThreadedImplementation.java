package com.bh25034.config;

import java.util.Vector;

import com.bh25034.logic.AlgorithmManagerJosephus;
import com.bh25034.beans.Person;

public class ThreadedImplementation extends Thread {

	private AlgorithmManagerJosephus manager;
	private Vector<Person> people;
	
	public ThreadedImplementation(AlgorithmManagerJosephus manager) { this.manager = manager; }
	
	public void run() {
		
		try {
        
			this.people = this.manager.getPeople();
			
			while (this.people.size() > 1) {
			
				this.manager.run();
				this.people = this.manager.getPeople();
				
				Thread.sleep(1000);
				
			}
			
			this.pl("SURVIVOR: " + this.people.get(this.people.size() - 1).getPosition());
            
        } 
		
		catch (InterruptedException iex) {
            
        	this.pl("Exception in thread: " + iex.getMessage());
        	iex.printStackTrace();
        	
        }
		
	}
	
	private void pl(String s) { System.out.println(s); }
	
}
