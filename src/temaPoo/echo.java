package temaPoo;

public class echo extends systemCommand{

	String raspuns = "" ;
	
	public String execute(String type, spatiuUseri spatiu , User actual, Directory r) {
		
		String[] sir = type.split(" ");
		String bui ;
		StringBuilder box = new StringBuilder("\n");
		
		if(sir.length == 1)
			raspuns = "Introduceti si fraza !";
		// in caz de nu am cu ghilimele afisez ce sa adaugat dupa echo , iar in caz de 
		// sunt si ghilimelele , le voi taia , ca afisarea sa fie fara asa cum este si in linux
		else if(sir.length > 1){
			StringBuilder a = new StringBuilder();
			StringBuilder b = new StringBuilder();
			
			if( type.charAt(5) == '\"' && type.charAt(type.length()-1) == '\"')
				bui = type.substring(type.indexOf('\"')+1, type.lastIndexOf('\"'));
			else 
				bui = type.substring(type.indexOf(' ')+1);
			
			box.append(bui);
			raspuns = box.toString();
				
		}
		else raspuns = "Fail";
		return raspuns;
				
		
	}
	
	public String execute(String s, spatiuUseri spatiu , User act, Repository r){
		
			return r.accept(s, spatiu, act, r, this);
	};
	
	
	
	public String execute(String s, spatiuUseri spatiu , User act, File r){return "Fail";};

}
