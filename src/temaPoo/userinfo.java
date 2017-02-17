package temaPoo;

public class userinfo extends systemCommand {

	String raspuns = "" ;
	
	public String execute(String type, spatiuUseri spatiu , User actual, Directory r) {
		String[] sir = type.split(" ");
		
		if(sir.length == 1){
			raspuns = spatiu.getUserbyNr(spatiu.getNumarOrdine()).toString();
		}else
		if(sir.length == 2){
			if(spatiu.contine(sir[1]))
			raspuns = spatiu.getUser(sir[1]).toString();
			else raspuns = "Nu exista userul " + sir[1] + " in sistem !"; 
		}
		else  raspuns  = "fail";
		return raspuns;
	}

	public String execute(String s, spatiuUseri spatiu , User act, Repository r){
		
		return r.accept(s, spatiu, act, r, this);
	};
	
	
	
	public String execute(String s, spatiuUseri spatiu , User act, File r){return "Fail";};
}
