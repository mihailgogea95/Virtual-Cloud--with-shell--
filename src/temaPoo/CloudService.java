package temaPoo;

import java.util.Iterator;

// Acesta este serviciul cloud , in care am 3 statii cum este precizat in enuntul temei
public class CloudService {

	StoreStation st1 = new StoreStation();
	StoreStation st2 = new StoreStation();
	StoreStation st3 = new StoreStation();
	
	public boolean upload(Repository r, spatiuUseri spatiu){
		if(st1.adauga((Directory)r , spatiu) == true)
			return true;
		// aici in loc de else return false trebuia sa se adauge in statia urmatoare in caz ca 
		// in statia st1 nu mai e loc , iar apoi daca este umpluta si statia 2 sa se adauga in 
		// statia st3.
		else return false;
	}
	
	// extrag directorul care a fost salavat si il inlocuiesc cu cel pe care il voi sterge
	public boolean sync(String nume, spatiuUseri spatiu){
		
		
		if(spatiu.getDirectorActual().contine(nume)){
			int i = spatiu.getDirectorActual().getRepos(nume).getDimensiune();
			
			spatiu.getDirectorActual().dimensiune -= i;
			spatiu.stergeDimensiuneParinti(spatiu.getDirectorActual(), i);
			
			spatiu.getDirectorActual().remove(spatiu.getDirectorActual().getRepos(nume));
			
			spatiu.getDirectorActual().dimensiune += st1.extrage(nume).getDimensiune();
			spatiu.adaugaDimensiuneParinti(spatiu.getDirectorActual(), st1.extrage(nume).getDimensiune());
			
			spatiu.getDirectorActual().adauga(st1.extrage(nume));
			spatiu.intregime.add(spatiu.getDirectorActual().getRepos(nume));
			
			return true;
		}
		else return false;
		
	}
	

}
