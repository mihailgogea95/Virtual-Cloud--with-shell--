package temaPoo;

import java.util.ArrayList;
import java.util.*;

// O clasa abstracta pentru a ma ajuta sa implementez cele 2 clase Directory si File
public abstract class AbsRepository implements Repository{

	public String nume;
	public int dimensiune;
	public String data;
	public int all = 0;
	
	
	public int isAllView(){
		return this.all;
	}
	
	public void setAllView(){
		this.all = 1;
	}
	
	public String getNume(){
		return this.nume;
	}
	public int getDimensiune(){
		return this.dimensiune;
	}
	public String data(){
		return this.data;
	}
	public String normal(){
		return this.nume;
	}
	public String complicat(){
		return this.nume + " " + this.dimensiune + " " + this.data + " " ;
	}
	public Permisiune getPermisiune(String username){
		ArrayList<Permisiune> aux =  this.getContinutPermisiuni();
		Permisiune per ;
		Iterator<Permisiune> it = aux.iterator();
		while(it.hasNext()){
			per = it.next();
			if(per.user.equalsIgnoreCase(username) == true)
				return per;
		}
		return null;
	}
	

	
}
