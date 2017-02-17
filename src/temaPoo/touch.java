package temaPoo;

public class touch extends WriteCommand{

	
String raspuns = "";
	
	
	public String execute(String s, spatiuUseri spatiu, User act, Repository r) {
			
		return r.accept(s, spatiu, act, r, this);
		
	}
	
	public String execute(String s, spatiuUseri spatiu, User act, Directory r) {
		String[] sir = s.split(" ");
	
		
		if(spatiu.getNumarOrdine() == 1)
			raspuns = "Guest nu are acest drept : touch !";
		
		else if(super.verifica(spatiu, spatiu.getDirectorActual()) == false)
			raspuns = "Nu aveti acest drept";
		
		else if(sir.length == 1)
			raspuns = "Introduceti numele fisierului !";
		
		// in caz de se doreste a se face un fisier prin comanda simpla : "touch nume_fisier", se va creea cu dimensiune 0
		else if(sir.length == 2 && spatiu.getDirectorActual().contine(sir[1]) == false){
			spatiu.getDirectorActual().adauga(new File(sir[1], 0));
			raspuns = "Reusit.";
			spatiu.getDirectorActual().getRepos(sir[1]).setAllView();
			for(User u:spatiu.lista){
				String username = u.getUsername();
				spatiu.getDirectorActual().getRepos(sir[1]).adaugaPermisiune(username, true, true);
			}
		}
		else if(sir.length == 2 && spatiu.getDirectorActual().contine(sir[1]) == true){
			raspuns = "Este deja un director/file cu acest nume";
		}
		// aici daca se adauga doar numele fisierului si dimensiunea , dar fara permisiune, atunci
		// userul curent sau alt user nu vor avea drept , in afara de root
		else if(sir.length == 3 && spatiu.getDirectorActual().contine(sir[1]) == false){
			spatiu.getDirectorActual().adauga(new File(sir[1], Integer.parseInt(sir[2])));
			spatiu.getDirectorActual().aduna(Integer.parseInt(sir[2]));
			if(spatiu.getDirectorActual().tata != null)
				spatiu.adaugaDimensiuneParinti(spatiu.getDirectorActual(),Integer.parseInt(sir[2]) );

			spatiu.getDirectorActual().getRepos(sir[1]).setAllView();
			raspuns = "Reusit.";
			for(User u:spatiu.lista){
				String username = u.getUsername();
				spatiu.getDirectorActual().getRepos(sir[1]).adaugaPermisiune(username, true, true);
			}
		}
		// daca s-a introdus numele unui fisier deja existent nu va putea fi introdus
		else if(sir.length == 3 && spatiu.getDirectorActual().contine(sir[1]) == true)
			raspuns = "Este deja acest fisier";
		
		// aici in caz de se va introduce si permisiunile se vor adauga in lista de permisiuni a 
		// fisierului nou format utilizatorul curent cu permisiunea data
		// dimensiunea va creste in directorul curent si in directorul tata.
		else if(sir.length == 4 && spatiu.getDirectorActual().contine(sir[1]) == false){
	    	if(sir[3].equalsIgnoreCase("r")==true || sir[3].equalsIgnoreCase("w")==true || sir[3].equalsIgnoreCase("rw")==true){
	    	spatiu.getDirectorActual().adauga(new File(sir[1], Integer.parseInt(sir[2])));
	    	spatiu.getDirectorActual().aduna(Integer.parseInt(sir[2]));
	    	if(spatiu.getDirectorActual().tata != null)
	    		spatiu.adaugaDimensiuneParinti(spatiu.getDirectorActual(),Integer.parseInt(sir[2]) );
	    	
			raspuns = "Reusit.";
			spatiu.getDirectorActual().getRepos(sir[1]).adaugaPermisiune("root", true, true);
			String username = spatiu.getUserbyNr(spatiu.getNumarOrdine()).getUsername();
			if(sir[3].equals("r"))
				spatiu.getDirectorActual().getRepos(sir[1]).adaugaPermisiune(username, true, false);
			if(sir[3].equals("w"))
				spatiu.getDirectorActual().getRepos(sir[1]).adaugaPermisiune(username, false, true);
			if(sir[3].equals("rw"))
				spatiu.getDirectorActual().getRepos(sir[1]).adaugaPermisiune(username, true, true);
		}}else
	    if(sir.length == 4 && spatiu.getDirectorActual().contine(sir[1]) == true){
	    	if(sir[3].equalsIgnoreCase("r")==true || sir[3].equalsIgnoreCase("w")==true || sir[3].equalsIgnoreCase("rw")==true)
	    		raspuns = "Este deja acest fisier " + spatiu.getDirectorActual().getRepos(sir[1]).getPermisiuni();}
	    else if(sir.length > 4)
	    	raspuns = "Fail";
	
		return raspuns;
		

		
		
	}
	
	public String execute(String s, spatiuUseri spatiu, User act, File r) {
		
		return "Nu se poate creea fisiere in fisiere !";
		
	}
	

}
