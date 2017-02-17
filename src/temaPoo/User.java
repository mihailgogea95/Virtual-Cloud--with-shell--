package temaPoo;

public class User {
	
	   static int n = 0;
	   private String username;
	   private String parola;
	   private String nume;
	   private String prenume;
	   private String inregistration;
	   private String last;
	   private int numar;
	   
	   public User(String s1, String s2, String s3, String s4, getDate s5, getDate s6){
		   this.username = s1;
		   this.parola = s2;
		   this.nume = s3;
		   this.prenume = s4;
		   this.inregistration = s5.toString();
		   this.last = s6.toString();
		   numar = n;
		   n++;
	   }
	   
	   public String getUsername(){
		   return this.username;
	   }
	   
	   public String getParola(){
		   return this.parola;
	   }
	   
	   public String getNume(){
		   return this.nume;
	   }
	   public String getPren(){
		   return this.prenume;
	   }
	   public String getInreg(){
		   return this.inregistration;
	   }
	   public String getLast(){
		   return this.last;
	   }
	   public void setLastData(getDate g){
		   this.last = g.toString();
	   }
	   public int getNumarOrdine(){
		   return this.numar;
	   }
	   
	   public String toString(){
		   String aux  ;
		   aux =  "\n" + "Username: "+ this.getUsername() + "\n" ;
		   aux +="Nume: " + this.getNume() + " \n";
		   aux +="Prenume: " + this.getPren() + "\n";
		   aux +="Created :" + this.getInreg() + "\n";
		   aux +="Last Login: "+ this.getLast() + "\n";
		   return aux;
		   
	   }
	   

	}