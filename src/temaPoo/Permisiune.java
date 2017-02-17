package temaPoo;

public class Permisiune {
	public boolean read;
	public boolean write;
	public String user;
	
	public Permisiune(String n, boolean a, boolean b){
		this.user = n;
		this.write = b;
		this.read = a;
	}
	
	public String getUser(){
		return this.user;
	}
	
	public String getPerm(){
		String aux = "";
		if(this.read == true && this.write == false)
			aux = "-r-";
		else if(this.read == false && this.write == true)
			aux = "-w-";
		else if(this.read == true && this.write == true)
			aux = "-rw-";
		else if(this.read == false && this.write == false)
			aux = "";
		else aux = "";
		return aux;
	}
	
	public String getString(){
		String a = "fals",b = "fals";
		if(this.read == true)
			a = "adev";
		if(this.write == true)
			b = "adev";
		return this.user + " " + a + " " +  b; 
	}
}
