package com.bh25034.apps;

import java.util.Vector;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bh25034.config.ThreadedImplementation;
import com.bh25034.logic.AlgorithmManagerJosephus;
import com.bh25034.beans.Person;

public class Main {
	
	public static void main(String args []) {

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		AlgorithmManagerJosephus algorithmManager = (AlgorithmManagerJosephus) context.getBean("algorithmManager");
		
		Vector<Person> people = new Vector<Person>();
		
		for (int a = 0; a < 39; a ++) {
			
			Person person = new Person(a + 1, false, 0);
			people.add(person);
			
		}
		
		algorithmManager.setPeople(people);
		
		ThreadedImplementation threadedImplementation = new ThreadedImplementation(algorithmManager);
		threadedImplementation.start();
		
	}
	
}
