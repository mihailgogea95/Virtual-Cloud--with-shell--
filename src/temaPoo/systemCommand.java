package temaPoo;

public abstract class systemCommand implements Command{
	
	public abstract String execute(String s, spatiuUseri spatiu , User act, Directory r);
	
	public abstract String execute(String s, spatiuUseri spatiu , User act, Repository r);
	public abstract String execute(String s, spatiuUseri spatiu , User act, File r);
}
