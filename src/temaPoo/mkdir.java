package temaPoo;

public class mkdir extends WriteCommand{

	String raspuns = "";
	
	
	public String execute(String s, spatiuUseri spatiu, User act, Repository r) {
			
		return r.accept(s, spatiu, act, r, this);
		
	}
	
	public String execute(String s, spatiuUseri spatiu, User act, Directory r) {
		String[] sir = s.split(" ");
	
		
		if(spatiu.getNumarOrdine() == 1)
			raspuns = "Guest nu are acest drept : mkdir !";
		
		else if(super.verifica(spatiu, spatiu.getDirectorActual()) == false)
			raspuns = "Nu aveti acest drept";
		
		else if(sir.length == 1)
			raspuns = "Introduceti si numele directorului !";
		
		else if(sir.length == 2 && spatiu.getDirectorActual().contine(sir[1]) == false ){
			spatiu.getDirectorActual().adauga(new Directory(sir[1], spatiu.getDirectorActual()));
			spatiu.intregime.add(spatiu.getDirectorActual().getRepos(sir[1]));
			raspuns = "Reusit";
			spatiu.getDirectorActual().getRepos(sir[1]).setAllView();
			for(User u:spatiu.lista){
				String username = u.getUsername();
				spatiu.getDirectorActual().getRepos(sir[1]).adaugaPermisiune(username, true, true);
			}
			
		}
		else if(sir.length == 2 && spatiu.getDirectorActual().contine(sir[1]) == true){
			raspuns = "Exista deja acest fisier/director";
		}
		// verific daca lungimea este 3 atunci inseamna ca voi avea si permisiune ( r , w  sau rw )
		// voi verifica daca contine directorul , iar daca nu il contine , verific daca s-a dat si permisiune
		// daca da atunci il adaug in directoru curent si adaug in lista de permisiuni a directorului nou adauga
		// userul curent cu permisiunea respectiva .
		else if(sir.length == 3 && spatiu.getDirectorActual().contine(sir[1]) == false){
	    	if(sir[2].equalsIgnoreCase("r")==true || sir[2].equalsIgnoreCase("w")==true || sir[2].equalsIgnoreCase("rw")==true){
			spatiu.getDirectorActual().adauga(new Directory(sir[1], spatiu.getDirectorActual()));
			spatiu.intregime.add(spatiu.getDirectorActual().getRepos(sir[1]));
			raspuns = "Reusit.";
			String username = spatiu.getUserbyNr(spatiu.getNumarOrdine()).getUsername();
			if(sir[2].equals("r"))
				spatiu.getDirectorActual().getRepos(sir[1]).adaugaPermisiune(username, true, false);
			if(sir[2].equals("w"))
				spatiu.getDirectorActual().getRepos(sir[1]).adaugaPermisiune(username, false, true);
			if(sir[2].equals("rw"))
				spatiu.getDirectorActual().getRepos(sir[1]).adaugaPermisiune(username, true, true);
	    	}
	    	else if(spatiu.getDirectorActual().contine(sir[1]) == false && spatiu.getDirectorActual().contine(sir[2]) == false){
		    	spatiu.getDirectorActual().adauga(new Directory(sir[1], spatiu.getDirectorActual()));
				spatiu.intregime.add(spatiu.getDirectorActual().getRepos(sir[1]));
				spatiu.getDirectorActual().adauga(new Directory(sir[2], spatiu.getDirectorActual()));
				spatiu.intregime.add(spatiu.getDirectorActual().getRepos(sir[2]));
				raspuns = "Reusit";
				spatiu.getDirectorActual().getRepos(sir[1]).setAllView();
				spatiu.getDirectorActual().getRepos(sir[2]).setAllView();
				for(User u:spatiu.lista){
					String username = u.getUsername();
					spatiu.getDirectorActual().getRepos(sir[1]).adaugaPermisiune(username, true, true);
					spatiu.getDirectorActual().getRepos(sir[2]).adaugaPermisiune(username, true, true);
				}
		    	}
	    	else raspuns = "Fail";
	    	}
		else
	    if(sir.length == 3 && spatiu.getDirectorActual().contine(sir[1]) == true){
	    	if(sir[2].equalsIgnoreCase("r")==true || sir[2].equalsIgnoreCase("w")==true || sir[2].equalsIgnoreCase("rw")==true)
	    		raspuns = "Este deja acest folder " + spatiu.getDirectorActual().getRepos(sir[1]).getPermisiuni();}
	    
	    else {
			raspuns = "Reusit";
			for(int i=1; i<sir.length; i++){
				if(spatiu.getDirectorActual().contine(sir[i]) == false){
					spatiu.getDirectorActual().adauga(new Directory(sir[i], spatiu.getDirectorActual()));
					spatiu.intregime.add(spatiu.getDirectorActual().getRepos(sir[i]));
					spatiu.getDirectorActual().getRepos(sir[i]).setAllView();
					
					for(User u:spatiu.lista){
						String username = u.getUsername();
						spatiu.getDirectorActual().getRepos(sir[i]).adaugaPermisiune(username, true, true);
					}
				}
					
				else return "Ati introdus un nume de folder deja existent !";
				 
			}
		} 
	
		return raspuns;
		

		
		
	}
	
	public String execute(String s, spatiuUseri spatiu, User act, File r) {
		
		return "Nu se poate creea directoare in fisiere !";
		
	}
	



	
}
