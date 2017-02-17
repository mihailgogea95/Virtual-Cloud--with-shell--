package temaPoo;

import java.util.ArrayList;

public interface Repository {
	public String accept(String s, spatiuUseri spatiu, User act,Repository r , Command c);
	
	public void adaugaPermisiune(String nume, boolean x, boolean y);

	public String getNume();
	
	public String getPermisiuni();
	
	public int getDimensiune();
	
	public ArrayList<Permisiune> getContinutPermisiuni();
		
	public String afisare();
	
	public String detaliat(spatiuUseri spatiu);
	
	public String locatie();
	
	public void setLocatie(String n);
	
	public int getNumar();
	
	public void listingSimplu(StringBuilder bui);
	
	public void listingDetaliat(spatiuUseri spatiu, StringBuilder bui);
	
	public String listatArgument();
	
	public int isAllView();
	
	public void setAllView();
}
