package temaPoo;

import java.util.Iterator;


// Aceasta comanda cd este pentru a intra in alt director .
public class cd extends ReadCommand{

String raspuns = "";
	
	
	public String execute(String s, spatiuUseri spatiu, User act, Repository r) {
			
		return r.accept(s, spatiu, act, r, this);
		
	}
	
	public String execute(String s, spatiuUseri spatiu, User act, Directory r) {
		String[] sir = s.split(" ");
	
		try{
		if(spatiu.getNumarOrdine() == 1)
			raspuns = "Guest nu are acest drept : cd !";
		
		else if(super.verifica(spatiu, spatiu.getDirectorActual()) == false)
			raspuns = "Nu aveti acest drept";
		// sir[0] este parametrul comenzii adica "cd", de aceea verific
		// sa fie 2 lungimea array-ului sir
		else if(sir.length == 1){
			raspuns = "Introduceti si numele directorului";
		}
		// aici voi lua separat, deoarece trebuie tratat altfel
		// cand am cale absoluta
		else if(sir.length == 2 && sir[1].contains("/") == false){
			if(sir[1].equalsIgnoreCase("..") == true){
				if(spatiu.directorActual == 0)
					raspuns = "Fail";
				else{
					// in cazul in care am doar ".." trebuie sa schimb directorul
					// cu parintele celui actual , adica tatal directorului
					spatiu.seteazaDirector(spatiu.getDirectorActual().tata.getNumar());
					raspuns = "Succes";
				}
			}
			// altfel verific daca directorul actual il contine si daca verifica condita de readcomand
			//adica are permisiunea -r- user actual
			else if(spatiu.getDirectorActual().contine(sir[1]) == true && super.verifica(spatiu, spatiu.getDirectorActual().getRepos(sir[1])) == true){
				int k = spatiu.getDirectorActual().getRepos(sir[1]).getNumar();
				spatiu.seteazaDirector(k);
				raspuns = "Succes";
			}else {
				raspuns = "Nu aveti acest drept ori nu exista acest folder";
				throw new MyInvalidPathException(spatiu.getDirectorActual().getNume(), "cd", spatiu.getUserbyNr(spatiu.getNumarOrdine()).getUsername(), new getDate().toString());
			}
		}else if(sir.length == 2 && sir[1].contains("/") == true){
			String[] aux = sir[1].split("/");
			int k = spatiu.directorActual;
			// am salvat in k id-ul directorului actual deoarece in caz ca in calea absoluta
			// la final nu exista acel folder , trebuie sa revina la directorul in care s-a dat comanda cd.
			if(aux[aux.length - 1].equalsIgnoreCase("..")){
				for(int i=0 ; i < aux.length ; i++){
					if( spatiu.directorActual == 0){
						raspuns = "Fail";
						spatiu.directorActual = k;
						break;
					}else {
						spatiu.directorActual = spatiu.getDirectorActual().tata.getNumar();
						raspuns = "Succes";
					}
				}
			}else if(r.contine(aux[0])){
				spatiu.directorActual = r.getRepos(aux[0]).getNumar();
				for(int i=1 ; i < aux.length; i++){
					if(spatiu.getDirectorActual().contine(aux[i])){
						spatiu.directorActual = spatiu.getDirectorActual().getRepos(aux[i]).getNumar();
						raspuns = "Succes";
					}else {
						spatiu.directorActual = k;
						raspuns = "Fail";
						break;
					}
				}
			}
			else {
				for(int i=0 ; i < aux.length ; i++){
					if(aux[i].equals("..")){
						if(spatiu.directorActual == 0){
							spatiu.directorActual = k;
							raspuns = "Fail";
							break;
						}
						spatiu.directorActual = spatiu.getDirectorActual().tata.getNumar();
						raspuns = "Succes";
					}else if(spatiu.getDirectorActual().contine(aux[i])){
						spatiu.directorActual = spatiu.getDirectorActual().getRepos(aux[i]).getNumar();
						raspuns = "Succes";
						
					}
					else  { 
						spatiu.directorActual = k;
						raspuns = "Fail";
						throw new MyInvalidPathException(spatiu.getDirectorActual().getNume(), "cd", spatiu.getUserbyNr(spatiu.getNumarOrdine()).getUsername(), new getDate().toString());
					}
				}
				
			}
		}
		}catch (MyInvalidPathException m){
			spatiu.logger.registerObserver(m);
		}
		return raspuns ;
		
		

		
		
	}
	
	public String execute(String s, spatiuUseri spatiu, User act, File r) {
		
		return "Fail!";
		
	}
	
}
