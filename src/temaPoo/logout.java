package temaPoo;

public class logout extends systemCommand implements Observer{

	private String user ; 
	private String data;
	String raspuns = "" ;
	
	// aici pur si simplu se va iesi din user curent si se va intra in modul guest

	public String execute(String type, spatiuUseri spatiu , User actual, Directory r) {
		String[] sir = type.split(" ");
		
		if(sir.length > 1)
			raspuns = "Ati introdus argumente gresite la aceasta comanda !";
		if(spatiu.getNumarOrdine() == 1)
			raspuns = "Sunteti deja guest! ";
		else {
			raspuns = "Goodbye " + spatiu.getUserbyNr(spatiu.getNumarOrdine()).getUsername();
			this.user = spatiu.getUserbyNr(spatiu.getNumarOrdine()).getUsername();
			this.data = new getDate().toString();
			raspuns += ", veti intra in modul Guest";
			spatiu.setareUserActual(1);
			spatiu.getUserbyNr(1).setLastData(new getDate());
			
			
		}
		return raspuns;
				
		
	}
	
public String execute(String s, spatiuUseri spatiu , User act, Repository r){
		
		return r.accept(s, spatiu, act, r, this);
	};
	
	
	
	public String execute(String s, spatiuUseri spatiu , User act, File r){return "Fail";}

	@Override
	public String update() {
		return  "Logout : " +  this.user + "  " + this.data;
	};
}
