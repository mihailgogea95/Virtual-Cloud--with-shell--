package temaPoo;

public interface Command {
	public String execute(String s, spatiuUseri spatiu , User act, Repository r);
	public String execute(String s, spatiuUseri spatiu , User act, Directory r);
	public String execute(String s, spatiuUseri spatiu , User act, File r);
}
