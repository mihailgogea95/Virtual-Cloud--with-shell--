package temaPoo;

public class upload extends ReadCommand{

	public String raspuns = "";
	@Override
	public String execute(String s, spatiuUseri spatiu, User act, Repository r) {
		return r.accept(s, spatiu, act, r, this);
	}

	@Override
	public String execute(String s, spatiuUseri spatiu, User act, Directory r) {
		String[] sir = s.split(" ");
		try{
		if(spatiu.getNumarOrdine() == 1)
			raspuns = "Guest nu are acest drept : upload !";
		
		//trebuie sa aibe userul drept de citire pentru aceasta comanda
		else if(super.verifica(spatiu, spatiu.getDirectorActual()) == false)
			raspuns = "Nu aveti acest drept";
		
		else if(spatiu.getDirectorActual().contine(sir[1]) && super.verifica(spatiu, spatiu.getDirectorActual().getRepos(sir[1]))){
			if(spatiu.cloud.upload(spatiu.getDirectorActual().getRepos(sir[1]),spatiu) == true){
				raspuns = "Succes";				
				
			}else {
				raspuns = "Fail";
				throw new MyNotEnoughSpaceException(spatiu.getDirectorActual().getRepos(sir[1]).getDimensiune(), spatiu.getUserbyNr(spatiu.getNumarOrdine()).getUsername(), new getDate().toString());
			}
		}else raspuns = "Fail";
		}catch (MyNotEnoughSpaceException m){
			spatiu.logger.registerObserver(m);
		}
		
		
		return raspuns;
	}

	@Override
	public String execute(String s, spatiuUseri spatiu, User act, File r) {
		return "Nu se poate";
	}

	
}
