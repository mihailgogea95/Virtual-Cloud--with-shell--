package temaPoo;

import java.util.ArrayList;
import java.util.Iterator;

public class rm extends WriteCommand{

String raspuns = "";
	
	
	public String execute(String s, spatiuUseri spatiu, User act, Repository r) {
			
		return r.accept(s, spatiu, act, r, this);
		
	}
	
	
	
	public String execute(String s, spatiuUseri spatiu, User act, Directory r) {
		
		String[] sir = s.split(" ");
		
		try {
		if(spatiu.getNumarOrdine() == 1)
			raspuns = "Guest nu are acest drept : rm !";
		
		else if(super.verifica(spatiu, spatiu.getDirectorActual()) == false)
			raspuns = "Nu aveti acest drept";
		
		else if(sir.length == 1)
			raspuns = "Introduceti numele fisierului/directorului care doriti sa il stergeti ";
		
		// daca parametrul data contine "/" atunci voi lua separat acest caz , deoarece este o cale absoluta . 
		else if(sir.length == 2 && sir[1].contains("/")){
			String[] aux = sir[1].split("/");
			int save = spatiu.directorActual;
			if(r.contine(aux[0])){
				spatiu.directorActual = r.getRepos(aux[0]).getNumar();
				if(aux.length == 2){
					// verifica daca contine in directorul actual si daca are dreptu de write.
					if(spatiu.getDirectorActual().contine(aux[1]) == true && super.verifica(spatiu, spatiu.getDirectorActual().getRepos(aux[1]))){
						raspuns = "Succes";
						// scad acum dimensiunea lui si dimensiunea tatalui inainte de al sterge.
						int dim = spatiu.getDirectorActual().getRepos(aux[1]).getDimensiune();
						spatiu.getDirectorActual().dimensiune -= spatiu.getDirectorActual().getRepos(aux[1]).getDimensiune();
						if(spatiu.getDirectorActual().getRepos(aux[1]).detaliat(spatiu).contains("F:") == false)
							spatiu.stergeDimensiuneParinti((Directory)spatiu.getDirectorActual().getRepos(aux[1]), dim);
		
						spatiu.getDirectorActual().remove(spatiu.getDirectorActual().getRepos(aux[1]));
						spatiu.directorActual = save;
					}else {
						raspuns = "Fail";
						spatiu.directorActual = save;
						throw new MyInvalidPathException(spatiu.getDirectorActual().getNume(), "rm", spatiu.getUserbyNr(spatiu.getNumarOrdine()).getUsername(), new getDate().toString());
					}
				}else{
				for(int i=1; i<aux.length - 1; i++){
					if(spatiu.getDirectorActual().contine(aux[i]) == true){
						raspuns = "Succes";
						spatiu.directorActual = spatiu.getDirectorActual().getRepos(aux[i]).getNumar();
					}else {
						raspuns = "Fail";
						spatiu.directorActual = save;
						break;
					}
				}
				if(raspuns.equals("Succes") == true && spatiu.getDirectorActual().contine(aux[aux.length - 1]) && super.verifica(spatiu, spatiu.getDirectorActual().getRepos(aux[aux.length - 1 ]))){
					raspuns = "Succes";
					int dim = spatiu.getDirectorActual().getRepos(aux[aux.length-1]).getDimensiune();
					spatiu.getDirectorActual().dimensiune -= spatiu.getDirectorActual().getRepos(aux[aux.length-1]).getDimensiune();
					if(spatiu.getDirectorActual().getRepos(aux[aux.length - 1]).detaliat(spatiu).contains("F:") == false)
						spatiu.stergeDimensiuneParinti((Directory)spatiu.getDirectorActual().getRepos(aux[aux.length-1]), dim);
			
					spatiu.getDirectorActual().remove(spatiu.getDirectorActual().getRepos(aux[aux.length - 1]));
					spatiu.directorActual = save;
				}else  {
					raspuns = "Fail";
					spatiu.directorActual = save;
					throw new MyInvalidPathException(spatiu.getDirectorActual().getNume(), "rm", spatiu.getUserbyNr(spatiu.getNumarOrdine()).getUsername(), new getDate().toString());
				}
			}
			}
		}
		// pentru expresii regulate voi lua separat.
		// dar pentru stergere va fi la fel ca in conditia de mai sus.
		else if(sir.length == 2 && sir[1].contains("(.*)")){
			raspuns = "Fail";
			String aux = sir[1].substring(4, 5);
			ArrayList<String> listaSters = new ArrayList<String>();
			for(Repository bax : spatiu.getDirectorActual().continut){
				if(super.verifica(spatiu, bax) == true && bax.getNume().contains(aux)){
					listaSters.add(bax.getNume());
				}
			}
			Iterator<String> it = listaSters.iterator();
			while(it.hasNext()){
				Repository bax = spatiu.getDirectorActual().getRepos(it.next());
				spatiu.getDirectorActual().dimensiune -= bax.getDimensiune();
				if(spatiu.getDirectorActual().tata != null)
					spatiu.stergeDimensiuneParinti((Directory)spatiu.getDirectorActual(), bax.getDimensiune());
		
				spatiu.getDirectorActual().remove(bax);
				raspuns = "Success";
			}
			
		}
		else if(sir.length == 2 && spatiu.getDirectorActual().contine(sir[1]) == false){
			raspuns = "Nu exista acest fisier/director !";
			throw new MyInvalidPathException(spatiu.getDirectorActual().getNume(), "rm", spatiu.getUserbyNr(spatiu.getNumarOrdine()).getUsername(), new getDate().toString());
		}
		else if(sir.length == 2 && spatiu.getDirectorActual().getRepos(sir[1]).getDimensiune() == 0 && super.verifica(spatiu, spatiu.getDirectorActual().getRepos(sir[1]))){
			spatiu.getDirectorActual().remove(spatiu.getDirectorActual().getRepos(sir[1]));
			raspuns = "Succes";
		}
		// daca este fisier nu conteaza dimensiunea lui , deoarece un fisier daca are dimensiunea diferita
		// de 0 poate fi sters in comparatie cu directorul care nu poate fi sters daca dimensiunea e diferita de 0.
		else if(sir.length == 2 && spatiu.getDirectorActual().getRepos(sir[1]).getDimensiune() != 0 && super.verifica(spatiu, spatiu.getDirectorActual().getRepos(sir[1]))){
			String aux = spatiu.getDirectorActual().getRepos(sir[1]).detaliat(spatiu);
			if(aux.contains("F: ")){
				spatiu.getDirectorActual().dimensiune -= spatiu.getDirectorActual().getRepos(sir[1]).getDimensiune();
				if(spatiu.getDirectorActual().tata != null)
					spatiu.stergeDimensiuneParinti((Directory)spatiu.getDirectorActual(), spatiu.getDirectorActual().getRepos(sir[1]).getDimensiune());
	
				spatiu.getDirectorActual().remove(spatiu.getDirectorActual().getRepos(sir[1]));
				raspuns = sir[1] + " a fost sters";
			}
			else {
				raspuns = "Fail";
				throw new MyInvalidPathException(spatiu.getDirectorActual().getNume(), "rm", spatiu.getUserbyNr(spatiu.getNumarOrdine()).getUsername(), new getDate().toString());
			}
		}
		else if(sir.length == 3 && spatiu.getDirectorActual().contine(sir[2]) == true && super.verifica(spatiu, spatiu.getDirectorActual().getRepos(sir[2]))){
			spatiu.getDirectorActual().dimensiune -= spatiu.getDirectorActual().getRepos(sir[2]).getDimensiune();
			if(spatiu.getDirectorActual().tata != null)
				spatiu.stergeDimensiuneParinti((Directory)spatiu.getDirectorActual(), spatiu.getDirectorActual().getRepos(sir[2]).getDimensiune());
	
			spatiu.getDirectorActual().remove(spatiu.getDirectorActual().getRepos(sir[2]));
			raspuns = sir[2] + " a fost sters";
		
		}
		else {
			raspuns = "Fail";
			throw new MyInvalidPathException(spatiu.getDirectorActual().getNume(), "rm", spatiu.getUserbyNr(spatiu.getNumarOrdine()).getUsername(), new getDate().toString());
		}
		}catch (MyInvalidPathException m){
			spatiu.logger.registerObserver(m);
		}
		return raspuns;
		
		
	}
	
	
	
	public String execute(String s, spatiuUseri spatiu, User act, File r) {
		
		return "File nu contine fisiere sau directoare";
		
	}
}
