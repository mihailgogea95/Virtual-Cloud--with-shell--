package temaPoo;

import java.util.Iterator;

public class pwd extends ReadCommand{

	
String raspuns = "";
	
	
	public String execute(String s, spatiuUseri spatiu, User act, Repository r) {
			
		return r.accept(s, spatiu, act, r, this);
		
	}
	
	public String execute(String s, spatiuUseri spatiu, User act, Directory r) {
		String[] sir = s.split(" ");
	
		try{
		if(spatiu.getNumarOrdine() == 1)
			raspuns = "Guest nu are acest drept : pwd !";
		
		else if(super.verifica(spatiu, spatiu.getDirectorActual()) == false)
			raspuns = "Nu aveti acest drept";
		// voi afisa path-ul absolut al directorului curent. 
		// daca calea absoluta va contine mai mult de 255 de caractere atunci voi arunca o exceptie.
		else if(sir.length == 1){
			raspuns = spatiu.getDirectorActual().locatie();
			if(raspuns.length() > 255)
				throw new MyPathTooLongException(spatiu.getDirectorActual().getNume(), "pwd", spatiu.getUserbyNr(spatiu.getNumarOrdine()).getUsername(), new getDate().toString());
		
		}
		else raspuns = "Fail";
		
		}catch (MyPathTooLongException m){
			spatiu.logger.registerObserver(m);
		}
		
		return raspuns ;
		
		
	}
	
	public String execute(String s, spatiuUseri spatiu, User act, File r) {
		
		return "Nu exista fisiere in fiser pentru a putea fi listate !";
		
	}
}
