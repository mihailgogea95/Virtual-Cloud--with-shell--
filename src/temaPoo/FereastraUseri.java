package temaPoo;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.*;
// aceasta clasa este pentru afisarea cu userinfo -POO
public class FereastraUseri extends JInternalFrame{

	
	static final int x = 30 , y = 30;
	public FereastraUseri(String titlu , String sir){
		
		super(titlu,
				true,
				true,
				true,
				true);
		setLocation(x , y);
		setLayout(new FlowLayout());
		
		String[] rows = sir.split("\n");
		JList lista = new JList(rows);
		lista.setVisibleRowCount(rows.length);

        JScrollPane jpanel = new JScrollPane(lista);

        Dimension d = lista.getPreferredSize();
        d.width = 200;
        d.height += 100;
        jpanel.setPreferredSize(d);
        setLayout(new FlowLayout());
        this.add(jpanel);
        this.pack();
        this.setVisible(true);
		
	}
}
