package temaPoo;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

// aceasta clasa este pentru obiectele de tip statie 
public class StoreStation  {

	public int dimensiune = 0;
	
	ArrayList<Repository> continut ;
	
	public StoreStation(){
		continut = new ArrayList<Repository>();
		
	}
	
	// am presupus ca dimensiunea fisierelor este data in biti, astfel pentru a nu se depasi numarul
	// de 10KB voi compara cu 1024 de biti care este aceeasi cu 10kb
	public boolean adauga(Directory r , spatiuUseri spatiu){
		if(this.dimensiune > 1024)
			return false;
		else if(this.dimensiune + r.getDimensiune() > 1024)
			return false;
		else {
			Directory nou = clonare(r,r.tata, spatiu);
			this.continut.add(nou);
			this.dimensiune += nou.dimensiune;
			return true;
		}
	}
	
	// functie pentru extragerea unui repository , dand ca argument numele celui dorit pentru extragere.
	public Repository extrage(String nume){
		Iterator<Repository> it = this.continut.iterator();
		while(it.hasNext()){
			Repository t;
			t = it.next();
			if(t.getNume().equals(nume))
				return t;
		}
		return null;
	}
	
	//functia clonare , returneaza o clona a directorului copil .
	public Directory clonare(Directory copil, Directory tata, spatiuUseri spatiu){
		
		Directory clone = new Directory(copil.nume, tata);
		clone.dimensiune = copil.dimensiune;
		clone.data = copil.data;
		if(copil.isAllView() == 1)
			clone.setAllView();
		clone.permisiuni = copil.permisiuni;
		if(copil.continut.size() !=0 ){
			Iterator<Repository> it = copil.continut.iterator();
			while(it.hasNext()){
				Repository aux = it.next();
				if(aux.detaliat(spatiu).contains("F: "))
					clone.continut.add(aux);
				else {
					Directory subClone = clonare((Directory)aux , clone, spatiu);
					clone.continut.add(subClone);
				}
			}
		}
		
		return clone;
	}
}
