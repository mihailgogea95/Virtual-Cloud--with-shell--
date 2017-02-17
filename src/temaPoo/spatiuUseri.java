package temaPoo;

import java.util.*;
 // aceasta clasa este pentru tinere de evidenta ce user este curent sau ce director este curent
 // dar si obiectul de tip Logger pentru Observer Pattern
public class spatiuUseri {
	
	public Logger logger = new Logger();
	public CloudService cloud ;
	public List<User> lista ;
	public int userActual = 1;
	public int directorActual = 0;
	public ArrayList<Repository> intregime ;
	
	public spatiuUseri(Repository baza){
		cloud = new CloudService();
		intregime = new ArrayList<>();
		intregime.add(baza);
		lista  = new ArrayList<>();
		lista.add(new User("root", "rootpas", "", "", new getDate(), new getDate()));
		lista.add(new User("guest", "", "", "", new getDate(), new getDate()));
	}
	
	public void adaug(User user){
		lista.add(user);
	}
	
	// returnez directorul curent . 
	public Directory getDirectorActual(){
		Iterator<Repository> it = this.intregime.iterator();
		while(it.hasNext()){
			Directory r = (Directory)it.next() ;
			if(r.getNumar() == this.directorActual)
				return r;
		}
		return null;
	}
	
	public void seteazaDirector(int j){
		this.directorActual = j;
	}
	
	public void stergeDimensiuneParinti(Directory r, int i){
		r.tata.dimensiune -= i;
		if(r.tata.tata != null)
			stergeDimensiuneParinti(r.tata, i);
		
	}
	
	public void adaugaDimensiuneParinti(Directory r, int i){
		r.tata.dimensiune += i;
		if(r.tata.tata != null)
			adaugaDimensiuneParinti(r.tata, i);
		
	}
	
	// verific daca contine userul dat ca parametru un string username
	public boolean contine(String username){
		Iterator<User> it = lista.iterator();
		while(it.hasNext())
			if(it.next().getUsername().equals(username))
				return true;
		
		return false;
	}
	
	public User getUserbyNr(int nr){
		return this.lista.get(nr);
	}
	
	public void setareUserActual(int n){
		this.userActual = n;
	}
	public int getNumarOrdine(){
		return this.userActual;
	}
	
	// aceasta functie returneaza id-ul unui user
	public int getNumber(String user){
		for(int i=0; i<this.lista.size(); i++){
			if(lista.get(i).getUsername().equalsIgnoreCase(user))
				return i;
		}
		return -1;
	}
	// aceasta functie returneaza user-ul  pe care este dat ca argument username al lui .
	public User getUser(String user){
		if(this.contine(user) == true){
			for(int i=0 ; i<this.lista.size(); i++){
				if(this.lista.get(i).getUsername().equals(user))
					return lista.get(i);
			}
		}
		return null;
			
	}
}
