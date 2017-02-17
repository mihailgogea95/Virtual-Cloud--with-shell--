package temaPoo;

public class MyInvalidPathException extends Exception implements Observer{
	
	public MyInvalidPathException(String s1, String s2 , String s3, String s4){
		
		super(s1 + " " + s2 + " " + s3 + " " + s4);
	}

	@Override
	public String update() {
		return this.toString();
	}
	
	
}
