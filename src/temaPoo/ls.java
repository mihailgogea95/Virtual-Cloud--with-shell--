package temaPoo;

import java.util.ArrayList;
import java.util.Iterator;

public class ls extends ReadCommand{
String raspuns = "";
	
	public String execute(String s, spatiuUseri spatiu, User act, Repository r) {
			
		return r.accept(s, spatiu, act, r, this);
		
	}
	
	public String execute(String s, spatiuUseri spatiu, User act, Directory r) {
		String[] sir = s.split(" ");
	
		
		if(spatiu.getNumarOrdine() == 1)
			raspuns = "Guest nu are acest drept : ls !";
		
		else if(super.verifica(spatiu, spatiu.getDirectorActual()) == false)
			raspuns = "Nu aveti acest drept";
		
		// daca se va da comanda ls simplu fara parametrii se va afisa ce este in continutul directorului curent
		else if(sir.length == 1){
			StringBuilder bui = new StringBuilder("\n");
			Iterator<Repository> it = spatiu.getDirectorActual().continut.iterator();
			while(it.hasNext()){
				bui.append(it.next().getNume());
				bui.append("\n");
			}
			raspuns = bui.toString();
		}
		else if(sir.length == 2 && spatiu.getDirectorActual().contine(sir[1])){
			raspuns = spatiu.getDirectorActual().getRepos(sir[1]).listatArgument();
		}
		// daca se va da comanda ls -a se va afisa ce este in continutul directorului , dar detaliat
		else if(sir.length == 2 && sir[1].equalsIgnoreCase("-a")){
			StringBuilder bui = new StringBuilder("\n");
			Iterator<Repository> it = spatiu.getDirectorActual().continut.iterator();
			while(it.hasNext()){
				bui.append(it.next().detaliat(spatiu));
				bui.append("\n");
			}
			raspuns = bui.toString();
		}
		// pentru comanda ls -r se va afisa continutul directorului curent , dar recursiv.
		else if(sir.length == 2 && sir[1].equalsIgnoreCase("-r")){
			StringBuilder bax = new StringBuilder();
			spatiu.getDirectorActual().listingSimplu(bax);
			raspuns = bax.toString();
			String[] aux = raspuns.split("\n");
			StringBuilder fax = new StringBuilder("\n");
			for(int i=1 ; i < aux.length; i++ ){
				fax.append(aux[i]);
				fax.append("\n");
			}
			raspuns = fax.toString();
		}
		// pentru comanda ls -ar se va afisa recursiv continutul directorului actual dar detaliat.
		else if(sir.length == 2 && sir[1].equalsIgnoreCase("-ar")){
			StringBuilder bax = new StringBuilder();
			spatiu.getDirectorActual().listingDetaliat(spatiu, bax);
			raspuns = bax.toString();
			String[] aux = raspuns.split("\n");
			StringBuilder fax = new StringBuilder("\n");
			for(int i=1 ; i < aux.length; i++ ){
				fax.append(aux[i]);
				fax.append("\n");
			}
			raspuns = fax.toString();
		}
		else if(sir.length == 2 && spatiu.getDirectorActual().contine(sir[1])){
			raspuns  = spatiu.getDirectorActual().getRepos(sir[1]).listatArgument();
		}
		// pentru expresii regulate voi verifica daca in continutul direct. actual , vreun repository face match
		// pe expresia regulata data intre cele 2 stelute.
		else if(sir.length == 2 && sir[1].contains("(.*)")){
			String aux = sir[1].substring(4, 5);
			StringBuilder bui = new StringBuilder("\n");
			for(Repository tap : spatiu.getDirectorActual().continut){
				if(tap.getNume().contains(aux))
					bui.append(tap.getNume()).append("\n");
			}
			raspuns = bui.toString();
			
		}
		else if(sir.length == 3 && sir[1].equalsIgnoreCase("-a") && sir[1].contains("(.*)")){
			String aux = sir[1].substring(4, 5);
			StringBuilder bui = new StringBuilder("\n");
			for(Repository tap : spatiu.getDirectorActual().continut){
				if(tap.getNume().contains(aux))
					bui.append(tap.detaliat(spatiu)).append("\n");
			}
			raspuns = bui.toString();
		}
		else raspuns = "Fail";
		return raspuns;
		
		

		
		
	}
	
	public String execute(String s, spatiuUseri spatiu, User act, File r) {
		
		return "Nu exista fisiere in fiser pentru a putea fi listate !";
		
	}

}
