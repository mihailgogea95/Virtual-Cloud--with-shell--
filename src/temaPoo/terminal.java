package temaPoo;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.UIManager.*;
import java.awt.*;

public class terminal extends JFrame {

	public Repository baza = new Directory("radacina", null) ;
	static User userCurent;
	static spatiuUseri useriManagement;
	public  JTextField textF ;
	public JTextArea textA ; 
	String comanda;
	
	
	public terminal(){
		super("Gogea Mihail 324CC");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIcon();
		
		// aici introduc Look - and - Feel ( Nimbus ) 
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {

		}
		
		textF = new JTextField();
		textA = new JTextArea();
		
		
		useriManagement = new spatiuUseri(baza);
		userCurent = useriManagement.getUser("guest");
		// creez o clasa de commandFactory pentru a returna clasele respective dorite.
		final commandFactory fab = new commandFactory();
		
		textF.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try{
                    	// extrag comanda dorita din jtextfield
                        String comanda = textF.getText();
                        
                        // daca comanda este exit , atunci voi introduce tot ce este in obiectul
                        // de tip Logger in fisierul evidenta.txt si voi termina programul
                        if(comanda.equals("exit")){
                        	useriManagement.logger.notifyObservers();
                        	System.exit(0);
                        }

                        // scriu in jtextarea comanda data si mai scriu si un new line
                        textA.append(">> " + comanda);
                        textA.append("\n");
                        if (comanda.equals("")) return;
                        
                        if(comanda.equals("clear")){
                        	textA.setText("");
                        }
                        
                        // in caz de este data comanda ls -l -POO atunci voi lua particular acest caz
                        // deoarece trebuie afisat in noua fereastra (fereastra interna) outputul.
                        // dar pentru afisare ma voi ajuta de comanda ls -a.
                        else if(comanda.equalsIgnoreCase("ls -l -POO")){
                        	String prop = fab.getCommand("ls -a",useriManagement).execute("ls -a", useriManagement, userCurent, baza);
                        	String timp = new getDate().toString();
                        	FereastraInterna fereastra = new FereastraInterna(timp, prop);
                        	JDesktopPane desktop = new JDesktopPane();
                        	desktop.add(getContentPane());
                        	desktop.add(fereastra);
                        	setContentPane(desktop);
                        	fereastra.moveToFront();
                        	textF.setText("");
                        	
                        }
                        // aici la fel ca mai sus , dar de aceasta data pentru comanda userinfo -POO
                        // aici voi afisa cu ajutorul clasei FereastraUseri outputul intro fereastra interna
                        // intrun jlist
                        else if(comanda.equals("userinfo -POO")){
                        	String prop = fab.getCommand("userinfo",useriManagement).execute("userinfo", useriManagement, userCurent, baza);
                        	String timp = new getDate().toString();
                        	FereastraUseri fer = new FereastraUseri(timp, prop);
                        	JDesktopPane desktop = new JDesktopPane();
                        	desktop.add(getContentPane());
                        	desktop.add(fer);
                        	setContentPane(desktop);
                        	fer.moveToFront();
                        	textF.setText("");
                        }
                        // pentru comanda echo -POO voi creea o fereastra dialog pentru afisare 
                        // dar ma voi ajuta de asemenea de comanda echo .
                        else if(comanda.contains("echo -POO")){
                        	String[] aux = comanda.split(" ");
                        	StringBuilder bui = new StringBuilder();
                        	bui.append("echo");
                   
                        	for(int i = 2 ; i < aux.length; i++){
                        		bui.append(" ").append(aux[i]);
                        	}
                        	String fraza = bui.toString();
                        	String f = fab.getCommand(fraza,useriManagement).execute(fraza, useriManagement, userCurent, baza);
                        	JOptionPane.showMessageDialog(getContentPane(),
                        		    f);
                        	
                        }
                        // aici voi trata toate celelalte comenzi si le voi scrie outputul in jtextarea
                        // cu tot cu comanda data.
                        // iar titlul va fi schimbat in functie de userul curent.
                        else textA.append("<<< " + fab.getCommand(comanda,useriManagement).execute(comanda, useriManagement, userCurent,baza));
                        setTitle("User : " + useriManagement.getUserbyNr(useriManagement.getNumarOrdine()).getUsername());
                        textA.append("\n");
                        textF.setText("");
                        
                    }catch (Exception except){
                    	
                    }
                    
                }
              
            }

            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });
	
		
		
		
		textA.setBackground(Color.black);
		textA.setForeground(new Color(255,200,0));
		textA.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		textA.setEditable(false);
		
		JScrollPane jpane = new JScrollPane(textA);
		jpane.setBorder(BorderFactory.createLineBorder(Color.red));
		jpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jpane.setPreferredSize(new Dimension(900, 500));
		

        textF.setBackground(Color.black);
        textF.setForeground(new Color(255,200,0));
        textF.setCaretColor(Color.yellow);
        textF.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        textF.setBorder(BorderFactory.createLineBorder(Color.red));
        
 
        add(jpane);
        add(textF);
 
        setPreferredSize(new Dimension(900, 500));
        setSize(new Dimension(900, 500));
        setLocationRelativeTo(null);
        setResizable(true);
        pack();
        setVisible(true);

        textF.requestFocus();
	}
	
	private void setIcon() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon4.png")));
		
	}

	public String getString(){
		return this.comanda;
	}
	
	public void scrieArea(String c){
		if(c != null)
		textA.append(c.toString() + "\n");
	}
	
}
