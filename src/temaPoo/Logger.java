package temaPoo;
import java.io.IOException;
import java.util.*;
import java.io.*;

// Aceasta clasa Logger este pentru Observer Pattern , in care adaug obiectele de tip Observer
public class Logger implements Subject{
	
	 private ArrayList<Observer> observers ;
	 
	 public Logger() {
		observers = new ArrayList<Observer>();
	}
	 
	 @Override
     public void registerObserver(Observer observer) {
            observers.add(observer);

     }

     @Override
     public void removeObserver(Observer observer) {
            observers.remove(observer);

     }

     // aceasta functie pe care o apelez la final inainte sa se iesa din program
     // iar observarile pentru exceptii si logari a userilor vor fi scrise in 
     // fisierul evidenta.txt
     @Override
     public void notifyObservers() {
    	 
    	 	FileWriter fw = null;
    	 	try{
    	 		fw = new FileWriter("evidenta.txt");
    	 		for (Observer ob : observers) {

                    fw.write(ob.update());
                    fw.write("\r\n");
                }
    	 		fw.close();
    	 	}catch (IOException e){
    	 		e.printStackTrace();
    	 	}


     }



}
