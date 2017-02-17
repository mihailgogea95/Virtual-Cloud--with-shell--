package temaPoo;

public class login extends systemCommand implements Observer{

	private String user;
	private String data;
	
String raspuns = "" ;
	
	public String execute(String type, spatiuUseri spatiu , User actual, Directory r) {
		String[] sir = type.split(" ");
		
		if(sir.length > 3)
			raspuns = "Prea multe argumente";
		else if(sir.length == 1)
			raspuns = "Ati vrut sa scrieti: login username parola";
		else if(sir.length == 2 && sir[1].equalsIgnoreCase("guest")==false)
			raspuns = "Va rog introduceti si parola !";
		else if(sir[1].equals(""))
			raspuns = "Nu ati introdus numele !";
		else if(spatiu.getNumarOrdine() != 1)
			raspuns = "Este deja logat alt utilizator !";
			else if(sir[1].equalsIgnoreCase("guest")){			// pentru login guest , se va intra in modul guest.
				spatiu.setareUserActual(1);
				this.user = "guest";
				this.data = new getDate().toString();
				spatiu.getUser("guest").setLastData(new getDate());
				raspuns = "Ati intrat in modul  -guest- " + spatiu.userActual;
			}
			else if(sir[1].equalsIgnoreCase("root")){		// pentru login root , se va intra in modul root,
				if(sir[2].equalsIgnoreCase("rootpas")){		// se va cere parola rootpas ca sa se poata intra.
					spatiu.setareUserActual(0);
					spatiu.directorActual = 0;
					spatiu.getUser("root").setLastData(new getDate());
					this.user = "root";
					this.data = new getDate().toString();
				raspuns = "Ati intrat in modul -root-";}
				else raspuns = "Parola incorecta !";
			}
			else if(spatiu.contine(sir[1])){
				if(sir[2].equalsIgnoreCase((spatiu.getUser(sir[1])).getParola())){
					spatiu.setareUserActual(spatiu.getUser(sir[1]).getNumarOrdine());   // daca parola a fost introdusa corect
					spatiu.directorActual = 0;											//se va schimba userul curent	
					spatiu.getUser(sir[1]).setLastData(new getDate());					// iar directorul curent va fi radacina
					this.user = sir[1];
					this.data = new getDate().toString();
					raspuns = "Bine ati venit " + sir[1];
				}
				else raspuns = "Parola incorecta !";}
			
			else if(spatiu.contine(sir[1]) == false)
				raspuns = "Nu exista in sistem userul  " + sir[1];
			else raspuns = "Fail";
		return raspuns;
					
						
					
			}
	
	public String execute(String s, spatiuUseri spatiu , User act, Repository r){
		
		return r.accept(s, spatiu, act, r, this);
	};
	
	
	
	public String execute(String s, spatiuUseri spatiu , User act, File r){return "Fail";}

	// aceasta metoda update() este pentru a scrie in fisierul evidenta.txt .
	@Override
	public String update() {
		return  "Login : " +  this.user + "  " + this.data;
		
	};
				
	
	}		
	

