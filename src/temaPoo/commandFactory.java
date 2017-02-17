package temaPoo;


// aici am clasa commandFactory in care intorc clasa cu comanda potrivita in functie de comanda data.
// la clasele login si logout este schimbat fata de celelalte clase deoarece , acele clase login si logout, 
// cand sunt create trebuie sa le adaug in Logger deoarece ele 2 intra in observer pattern.
public class commandFactory {

	public Command getCommand(String type, spatiuUseri spatiu){
		if(type == null)
			return null;
		String[] aux = type.split(" ");
		String cuv = aux[0];
		if(cuv.equalsIgnoreCase(""))
			return null;
		if(cuv.equalsIgnoreCase("newuser"))
			return new newUser();
		if(cuv.equalsIgnoreCase("login")){
			login newlogin = new login();
			spatiu.logger.registerObserver(newlogin);
			return newlogin;
		}
		if(cuv.equalsIgnoreCase("logout")){
			logout newlogout = new logout();
			spatiu.logger.registerObserver(newlogout);
			return newlogout;
		}
		if(cuv.equalsIgnoreCase("userinfo"))
			return new userinfo();
		if(cuv.equalsIgnoreCase("mkdir"))
			return new mkdir();
		if(cuv.equalsIgnoreCase("touch"))
			return new touch();
		if(cuv.equalsIgnoreCase("rm"))
			return new rm();
		if(cuv.equalsIgnoreCase("cat"))
			return new cat();
		if(cuv.equalsIgnoreCase("echo"))
			return new echo();
		if(cuv.equalsIgnoreCase("ls"))
			return new ls();
		if(cuv.equalsIgnoreCase("pwd"))
			return new pwd();
		if(cuv.equalsIgnoreCase("cd"))
			return new cd();
		if(cuv.equalsIgnoreCase("upload"))
			return new upload();
		if(cuv.equalsIgnoreCase("sync"))
			return new sync();
		else return null;
	}
}
