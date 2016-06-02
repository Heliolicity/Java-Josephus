package com.bh25034.logic;

import java.util.Vector;

import com.bh25034.beans.Person;

public interface AlgorithmManagerJosephus {
	
	public void run();
	
	public Vector<Person> getPeople();
	
	public int getNumberOfPeople();
	
	public int getKillInterval();
	
	public int getCount();
	
	public String getResultText();
	
	public void setPeople(Vector<Person> people);
	
	public void setNumberOfPeople(int numberOfPeople);
	
	public void setKillInterval(int killInterval);
	
	public void setCount(int count);
	
	public void setResultText(String resultText);
	
}
