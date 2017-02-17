package temaPoo;

import java.util.Iterator;

// aceasta clasa abstracta este pentru extinderea claselor de citire.
public abstract class ReadCommand implements Command{

	
	// aceasta functie verifica daca r are in lista de permisiuni pe userul curent 
	// si daca acesta are dreptul de citire.
public boolean verifica(spatiuUseri spatiu , Repository r){
		
		String nume = spatiu.getUserbyNr(spatiu.getNumarOrdine()).getUsername();
		if(r.getNume().equals("radacina"))
			return true;
		else{
		Iterator<Permisiune> it = r.getContinutPermisiuni().iterator();
		while(it.hasNext()){
			Permisiune per;
			per = it.next();
			if(per.user.equalsIgnoreCase(nume) == true && per.read == true)
				return true;
		}}
		return false;
	}
}
