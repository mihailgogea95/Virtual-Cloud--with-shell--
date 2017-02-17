package temaPoo;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

// aceasta clasa FereastraInterna este pentru afisarea cu ls -l -POO
public class FereastraInterna extends JInternalFrame {
	static final int x = 30 , y = 30;
	public FereastraInterna(String titlu , String sir){
		
		super( titlu,
				true,
				true,
				true,
				true);
		setLocation(x , y);

		setLayout(new FlowLayout());
		
		String[] rows = sir.split("\n");
		
		Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
		for (String row : rows) {
            row = row.trim();  
            Vector<String> data = new Vector<String>();
            data.addAll(Arrays.asList(row.split("\\s+")));
            dataVector.add(data);
        }
		
		Vector<String> header = new Vector<String>();
        header.add("Repository");
        header.add("Nume");
        header.add("Tipul");
        header.add("Data");
        header.add("Ora");
        header.add("Dimensiune");
        header.add("Permisiune");


        JTable table = new JTable(dataVector, header);
        JScrollPane jpanel = new JScrollPane(table);
        int i = table.getWidth();
        int j = table.getHeight();
        Dimension d = table.getPreferredSize();
        jpanel.setPreferredSize(new Dimension(d.width, table.getRowHeight()*rows.length+1));
        setLayout(new FlowLayout());
        this.add(jpanel);
        this.pack();
        this.setVisible(true);
		
	}
}
