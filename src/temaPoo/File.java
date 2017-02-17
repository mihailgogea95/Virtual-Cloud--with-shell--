package temaPoo;

import java.util.*;

public class File extends AbsRepository {
	
	Random rand  = new Random();
	private String tip;
	public ArrayList<Permisiune> permisiuni;
	public Vector vector;
	public File(String n, int dim){
		
		this.nume = n;
		this.dimensiune = dim;
		this.data = new getDate().toString();
		permisiuni = new ArrayList<>();
		permisiuni.add(new Permisiune("guest", false, false));
		permisiuni.add(new Permisiune("root", true, true));
		int i = rand.nextInt(37);
		if(i % 2 == 0)
			this.tip = "text";
		else this.tip = "binar";
		this.vector = new Vector<Character>();
		
		String alpha = "123456789abcdefghijklmnopqrstwxyz";
		String alphb = "01";
		if( this.tip.equals("text"))
		for(int k = 0; k < dim ; k++){
			this.vector.add(alpha.charAt(rand.nextInt(alpha.length())));
		}
		else if(this.tip.equals("binar"))
			for(int k=0; k < dim; k++){
				this.vector.add(alphb.charAt(rand.nextInt(alphb.length())));
			}
		
	}
	
	// aceasta functie este pentru afisarea continutului fisierului cu comanda cat.
	public String afisare(){
		Iterator<Character> it  = this.vector.iterator();
		StringBuilder bui = new StringBuilder();
		while(it.hasNext()){
			bui.append(it.next());
			bui.append("");
		}
		return bui.toString();
	}
	
	public String complicat(){
		return super.complicat() + this.tip ;
	}
	// pentru cand se da comanda ls fisier.
	public String listatArgument(){
		return this.nume;
	}
	
	public void listingSimplu(StringBuilder bui){
		bui.append(this.nume) ;
		bui.append("\n");
	}
	
	public void listingDetaliat(spatiuUseri spatiu , StringBuilder bui){
		bui.append(this.detaliat(spatiu)) ;
		bui.append("\n");
	}
	// afisarea cu ls a fisierului , dar detaliat ( -a )
	public String detaliat(spatiuUseri spatiu){
		String aux = "";
		Permisiune per = this.getPermisiune(spatiu.getUserbyNr(spatiu.getNumarOrdine()).getUsername());
		if(per == null || this.isAllView() == 1)
			return "F: " + this.nume + " "+ this.tip +" [" + this.data + "] " + this.dimensiune + " " + "-";
		else {
		aux = per.getPerm();
		return "F: " + this.nume + " "+ this.tip +" [" + this.data + "] " + this.dimensiune + " " + aux;
		}
	}
	
	public ArrayList<Permisiune> getContinutPermisiuni(){
		return this.permisiuni;
	}
	
	
	@Override
	public String accept(String s, spatiuUseri spatiu, User act, Repository r, Command c) {
		return c.execute(s, spatiu, act, this);
		
	}
	// functie pentru adaugare de permisiuni
	public void adaugaPermisiune(String nume, boolean x, boolean y){
		this.permisiuni.add(new Permisiune(nume, x, y));
	}

	public String locatie(){
		return "Fail";
	}
	public void setLocatie(String n){
		
	}
	
	public int getNumar(){
		return 0;
	}
	
	public String getPermisiuni(){
		String aux = " ";
		for(int i=0; i<this.permisiuni.size(); i++){
			aux += " " + this.permisiuni.get(i).getString()+",";
		}
		return aux;
	}
	
}
