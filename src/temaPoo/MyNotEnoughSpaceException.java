package temaPoo;

public class MyNotEnoughSpaceException extends Exception implements Observer{

	private int dim;
	private String user;
	private String data;
	
	public MyNotEnoughSpaceException(int n , String u, String d){
		super(n + " " + u + " " + d);
		this.dim = n;
		this.user = u;
		this.data = d;
	}

	@Override
	public String update() {
		return this.toString();
	}
}
