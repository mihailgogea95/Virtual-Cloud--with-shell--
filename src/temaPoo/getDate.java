package temaPoo;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
// aceasta clasa getDate este pentru a prelua data curenta cu ora curenta. 
public class getDate {

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	String data = dateFormat.format(date);
	
	public String toString(){
		return this.data;
	}
	
}
