package temaPoo;

import java.util.Iterator;

public abstract class WriteCommand implements Command{

// in aceasta clasa abstracta am introdus aceasta metoda pentru verificare
	// in caz daca repositoryul r are drept de write , adica verific daca
	// userul curent are drept de scriere asupra lui r

	public boolean verifica(spatiuUseri spatiu , Repository r){
		
		String nume = spatiu.getUserbyNr(spatiu.getNumarOrdine()).getUsername();
		if(r.getNume().equals("radacina"))
			return true;
		else{
		Iterator<Permisiune> it = r.getContinutPermisiuni().iterator();
		while(it.hasNext()){
			Permisiune per;
			per = it.next();
			if(per.user.equalsIgnoreCase(nume) == true && per.write == true)
				return true;
		}}
		return false;
	}
	
	
}
