package com.bh25034.gui.fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

import java.util.Vector;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bh25034.config.ThreadedImplementation;
import com.bh25034.logic.AlgorithmManagerJosephus;
import com.bh25034.beans.Person;

public class GUIJosephus extends Application implements Runnable {

	private ApplicationContext context;
	private AlgorithmManagerJosephus algorithmManager;
	private ThreadedImplementation threadedImplementation;
	private Vector<Person> people;
	private int numberOfPeople;
	private int killInterval;
	private GraphicsContext gc;
	private TextArea display;
	private String resultText;
	
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		this.context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		this.algorithmManager = (AlgorithmManagerJosephus) this.context.getBean("algorithmManager");
		this.numberOfPeople = this.algorithmManager.getNumberOfPeople();
		this.killInterval = this.algorithmManager.getKillInterval();
		
		this.people = new Vector<Person>();
		
		for (int a = 0; a < this.numberOfPeople; a ++) {
			
			Person person = new Person(a + 1, false, 0);
			this.people.add(person);
			
		}
		
		this.algorithmManager.setPeople(this.people);		
		this.threadedImplementation = new ThreadedImplementation(this.algorithmManager);
		
		primaryStage.setTitle("Josephus Algorithm");
		
        FlowPane root = new FlowPane();
        root.setStyle("-fx-background-color: black;");
        
        Canvas canvas = new Canvas(600, 600);
        this.gc = canvas.getGraphicsContext2D();
        
        //this.drawShapes(gc);
        this.initialiseCircles(gc);
        
        root.getChildren().add(canvas);
        
        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: white;");
        hbox.setMinSize(800, 100);
        
        this.display = new TextArea();
        this.display.setMinSize(800, 100);
        this.display.setMaxSize(800, 100);
        this.display.setText("Results will appear here.");
        
        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: grey;");
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setMinSize(200, 600);
        
        Label lblText = new Label();
        lblText.setWrapText(true);
        lblText.setMaxWidth(100);
        lblText.setMinWidth(100);
        lblText.setMinHeight(400);
        lblText.setText("This is an implementation of the Josephus Problem.  Given a group of n men arranged in a circle under the edict that every mth man will be executed going around the circle until only one remains, find the position L(n,m) in which you should stand in order to be the last survivor (Ball and Coxeter 1987).");
        
        Button btnStart = new Button("Start");
        btnStart.setMaxHeight(50);
        btnStart.setMinHeight(50);
        btnStart.setMaxWidth(100);
        btnStart.setMinWidth(100);
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
		      //@Override 
		      public void handle(ActionEvent event) {
		    	   run();
		      }
		    });
        
        Button btnReset = new Button("Reset");
        btnReset.setMaxHeight(50);
        btnReset.setMinHeight(50);
        btnReset.setMaxWidth(100);
        btnReset.setMinWidth(100);
        btnReset.setOnAction(new EventHandler<ActionEvent>() {
		      //@Override 
		      public void handle(ActionEvent event) {
		    	   initialiseCircles(gc);
		      }
		    });
        
        vbox.getChildren().add(lblText);
        vbox.getChildren().add(btnStart);
        vbox.getChildren().add(btnReset);
        
        root.getChildren().add(vbox);
        
        hbox.getChildren().add(this.display);
        
        root.getChildren().add(hbox);
        
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        
        primaryStage.setScene(scene);
        primaryStage.setWidth(820);
        primaryStage.setHeight(750);
        primaryStage.resizableProperty().set(false);
        primaryStage.show();
        
	}
	
	private void initialiseCircles(GraphicsContext gc) {
		
		this.people = new Vector<Person>();
		
		for (int a = 0; a < this.numberOfPeople; a ++) {
			
			Person person = new Person(a + 1, false, 0);
			this.people.add(person);
			
		}
		
		this.algorithmManager.setPeople(this.people);	
		this.algorithmManager.setCount(0);
		this.threadedImplementation = new ThreadedImplementation(this.algorithmManager);
		
		gc.setFill(Color.GREY);
		gc.setLineWidth(5);
		
		int xord = 300;
		int yord = 300;
		int width = 30;
		int height = 30;
		int xorigin = 300;
		int yorigin = 300;
		double radius = 0;
		double theta = 0; //360 % 39 = 9.2
		double interval = 360 / this.numberOfPeople;
		
		gc.fillOval(xord, yord, width, height);
		
		for (int a = 0; a < this.numberOfPeople; a ++) {

			gc.setFill(Color.WHITE);
			radius = (xord ^ 2) + (yord ^ 2);
			radius = Math.sqrt(radius);
			theta += Math.toRadians(interval);
			xord = (int) (xorigin + ((radius + 200) * Math.cos(theta)));
			yord = (int) (yorigin + ((radius + 200) * Math.sin(theta)));
			gc.fillOval(xord, yord, width, height);
			
			gc.setFill(Color.RED);
			gc.fillText("" + a, xord + 10, yord + 15);
			
		}		
		
	}
	
	private void repaintCircles(GraphicsContext gc) {
		
		gc.setFill(Color.GREY);
		gc.setLineWidth(5);
		
		int xord = 300;
		int yord = 300;
		int width = 30;
		int height = 30;
		int xorigin = 300;
		int yorigin = 300;
		double radius = 0;
		double theta = 0; //360 % 39 = 9.2
		double interval = 360 / this.numberOfPeople;
		
		gc.fillOval(xord, yord, width, height);
		
		for (int a = 0; a < this.numberOfPeople; a ++) {

			gc.setFill(Color.WHITE);
			radius = (xord ^ 2) + (yord ^ 2);
			radius = Math.sqrt(radius);
			theta += Math.toRadians(interval);
			xord = (int) (xorigin + ((radius + 200) * Math.cos(theta)));
			yord = (int) (yorigin + ((radius + 200) * Math.sin(theta)));
			
			if (! (this.findPerson(a))) {
			
				gc.setFill(Color.RED);
				gc.fillOval(xord, yord, width, height);
			
			}
			
			gc.setFill(Color.RED);
			gc.fillText("" + a, xord + 10, yord + 15);
			
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
	        
			this.people = this.algorithmManager.getPeople();
			
			while (this.people.size() > 1) {
			
				this.algorithmManager.run();
				this.people = this.algorithmManager.getPeople();
				this.resultText = this.algorithmManager.getResultText();
				this.display.setText(this.resultText);
				this.repaintCircles(this.gc);
				this.pl(this.resultText);
				
				Thread.sleep(1000);
				
			}
			
			this.pl("SURVIVOR: " + this.people.get(this.people.size() - 1).getPosition());
            
        } 
		
		catch (InterruptedException iex) {
            
        	this.pl("Exception in thread: " + iex.getMessage());
        	iex.printStackTrace();
        	
        }
	
	}
	
	private boolean findPerson(int p) {
		
		boolean found = false;
		
		for (int b = 0; b < this.people.size(); b ++) {
			
			Person person = this.people.get(b);
			
			if (person.getPosition() == p) found = true;
			
		}
		
		return found;
		
	}
	
	private void pl(String s) { System.out.println(s); }
	
}
