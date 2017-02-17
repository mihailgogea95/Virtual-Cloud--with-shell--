package temaPoo;
import java.util.*;
public class Directory extends AbsRepository{
	
// locatie e pentru a retine calea absoluta pentru afisare cand este comanda pwd.
	static int m = 0;
	public Directory tata;
	public ArrayList<Permisiune> permisiuni;
	public ArrayList<Repository> continut;
	public String locatie;
	public int numar ;
	
	public Directory(String n, Directory parinte) {
		this.nume = n;
		this.tata = parinte;
		this.dimensiune = 0;
		this.data = new getDate().toString();
		this.continut = new ArrayList<>();
		if(parinte == null)
			this.locatie = "";
		else {
			this.locatie = parinte.locatie + "/" + this.nume;
		}
		// adaug in lista de permisiuni pe guest si root ,deoarece ei sunt predefinti in enunt.
		permisiuni = new ArrayList<>();
		permisiuni.add(new Permisiune("guest", false, false));
		permisiuni.add(new Permisiune("root", true, true));
		this.numar = m;
		m++;
	}
	
	
	public void aduna(int dim){
		this.dimensiune += dim;
	}
	
	public void setLocatie(String n){
		this.locatie = n;
	}
	public void remove(Repository o){
		this.continut.remove(o);
	}
	
	public int getNumar(){
		return this.numar;
	}
	
	public ArrayList<Permisiune> getContinutPermisiuni(){
		return this.permisiuni;
	}
	
	public String locatie(){
		return this.locatie;
	}
	// aici este un string cu toate permisiunile , adica utilizatorii care au permisiuni asupra directorului
	// aceasta metoda am facuto pentru a imi testa programul.
	public String getPermisiuni(){
		String aux = " ";
		for(int i=0; i<this.permisiuni.size(); i++){
			aux += " " + this.permisiuni.get(i).getString()+",";
		}
		return aux;
	}
	
	public String afisare(){
		return "Acesta este un director";
	}
	
	// pentru ls -r am facut aceasta metoda.
	public void listingSimplu(StringBuilder bui){
		String aux = "";
		bui.append(this.nume);
		bui.append("\n");
		for(Repository var : this.continut){
			var.listingSimplu(bui);
		}
	}
	
	// pentru ls (argument) adica cand dau ls pe un file sau director sa afiseze aceasta.
	public String listatArgument(){
		StringBuilder bui = new StringBuilder("\n");
		Iterator<Repository> it = this.continut.iterator();
		while(it.hasNext()){
			bui.append(it.next().getNume());
			bui.append("\n");
		}
		return bui.toString();
	}
	// pentru ls -ar am facut aceasta metoda
	public void listingDetaliat(spatiuUseri spatiu , StringBuilder bui){
		String aux = "";
		bui.append(this.detaliat(spatiu));
		bui.append("\n");
		for(Repository var : this.continut){
			var.listingDetaliat(spatiu, bui);
		}
	}
	// adaugarea de permisiuni
	public void adaugaPermisiune(String nume, boolean x, boolean y){
		this.permisiuni.add(new Permisiune(nume, x, y));
	}
	// cand doresc sa intorc un repository din continutul acestui director, trebuie sa apelez
	// aceasta metoda , stiind doar numele repositoru-lui pe care il vreau.
	public Repository getRepos(String nume){
		Repository aux;
		Iterator<Repository> it = this.continut.iterator();
		while(it.hasNext()){
			aux = it.next();
			if(aux.getNume().equalsIgnoreCase(nume))
				return aux;
		}
		return null;
		
	}
	// afisarea in detaliu al acestui director
	public String detaliat(spatiuUseri spatiu){
		String aux = "";
		Permisiune per = this.getPermisiune(spatiu.getUserbyNr(spatiu.getNumarOrdine()).getUsername());
		if(per == null || this.isAllView() == 1)
			return "D: " + this.nume + " " + "dir" + " [" + this.data + "] " + this.dimensiune + " " + "-";
		else{
		aux = per.getPerm();
		return "D: " + this.nume + " " + "dir" + " [" + this.data + "] " + this.dimensiune + " " + aux;
		}
	}
	// functie pentru verificare daca contine un anumit repository cu numele n in continutul directorului.
	public boolean contine(String n){
		Iterator<Repository> it = this.continut.iterator();
		while(it.hasNext())
			if(it.next().getNume().equalsIgnoreCase(n))
				return true;
		return false;
	}
	
	public void adauga(Repository r){
		this.continut.add(r);
	}
	
	@Override
	public String accept(String s, spatiuUseri spatiu, User act, Repository r, Command c) {
		  return  c.execute(s, spatiu, act, this);
		
	}

	
}
