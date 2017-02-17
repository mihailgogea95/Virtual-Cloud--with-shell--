package temaPoo;

public class sync extends WriteCommand{

String raspuns = "";
	
	
	public String execute(String s, spatiuUseri spatiu, User act, Repository r) {
			
		return r.accept(s, spatiu, act, r, this);
		
	}
	
	public String execute(String s, spatiuUseri spatiu, User act, Directory r) {
		String[] sir = s.split(" ");
	
		
		if(spatiu.getNumarOrdine() == 1)
			raspuns = "Guest nu are acest drept : sync !";
		
		//trebuie sa aibe drept de scriere aceast user pentru comanda sync
		else if(super.verifica(spatiu, spatiu.getDirectorActual()) == false)
			raspuns = "Nu aveti acest drept";
		
		else if(sir.length == 1)
			raspuns = "Introduceti si numele directorului !";
		
		else if(sir.length == 2 ){
			if(spatiu.cloud.sync(sir[1],spatiu) == true)
				return "Succes";
			else return "Fail";
		
		
		}
		return raspuns;
		

		
		
	}
	
	public String execute(String s, spatiuUseri spatiu, User act, File r) {
		
		return "Nu se poate da sync la fisiere";
		
	}
	
}
