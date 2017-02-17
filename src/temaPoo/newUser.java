package temaPoo;

public class newUser extends systemCommand{

	String raspuns = "" ;
	
	public String execute(String type, spatiuUseri spatiu , User actual, Directory r) {
		String[] sir = type.split(" ");
		
		if(sir.length > 5)
			raspuns = "Prea multe argumente !";
		else if(sir[1].equalsIgnoreCase("guest"))
			raspuns = "Nu se poate, guest este deja !";
		else if(sir[1].equalsIgnoreCase("root"))
			raspuns  = "Nu se poate, root este deja !";
		else if(spatiu.contine(sir[1]))
			raspuns = "Este deja existent !";
		else if(sir[1].equalsIgnoreCase(""))
			raspuns = "Nu se poate !";
		else{
			String s2 = "",s3 = "" ,s4 = "";
			String s1 = sir[1];
			if(sir[2] != null)
				s2 = sir[2];
			if(sir[3] != null)
				s3 = sir[3];
			if(sir[4] != null)
				s4 = sir[4];
			User aux = new User(s1, s2, s3, s4, new getDate(), new getDate());
			spatiu.adaug(aux);
			raspuns = "User "+ sir[1] + " adaugat !";
			}
		return raspuns;
				
		
	}
public String execute(String s, spatiuUseri spatiu , User act, Repository r){
		
		return r.accept(s, spatiu, act, r, this);
	};
	
	
	
	public String execute(String s, spatiuUseri spatiu , User act, File r){return "Fail";};

	
}
