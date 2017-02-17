package temaPoo;


// Aceasta clasa cat , este pentru comanda cat care este pentru file .
// Va afisa continutul unui fisier , adica in format binaru sau text, in functie de tip;
public class cat extends ReadCommand{

String raspuns = "";
	
	
	public String execute(String s, spatiuUseri spatiu, User act, Repository r) {
			
		return r.accept(s, spatiu, act, r, this);
		
	}
	
	public String execute(String s, spatiuUseri spatiu, User act, Directory r) {
		String[] sir = s.split(" ");
	
		
		if(spatiu.getNumarOrdine() == 1)
			raspuns = "Guest nu are acest drept : cat !";
		
		else if(super.verifica(spatiu, spatiu.getDirectorActual()) == false)
			raspuns = "Nu aveti acest drept";
		
		else if(sir.length == 1)
			raspuns = "Introduceti si numele fisierului !";
		
		else if(sir.length == 2 && spatiu.getDirectorActual().contine(sir[1]) == false)
			raspuns = "Nu exista acest fisier !";
		
		else if(sir.length == 2 && spatiu.getDirectorActual().contine(sir[1]) == true){
			if(super.verifica(spatiu, spatiu.getDirectorActual().getRepos(sir[1])) == true)
			raspuns = spatiu.getDirectorActual().getRepos(sir[1]).afisare();
			else raspuns = "Nu aveti acest drept!";
		}
		else raspuns = "Fail";
	    	 
	
		return raspuns;
		

		
		
	}
	
	public String execute(String s, spatiuUseri spatiu, User act, File r) {
		
		return "Nu exista fisiere in fiser pentru a putea fi afisate !";
		
	}

	
}
