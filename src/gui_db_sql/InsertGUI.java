package gui_db_sql;
//import für SQL
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import für GUI
	import java.awt.*;           // Ur-Grafik
	import javax.swing.*;        // Moderne Grafik
	import java.awt.event.*;     // einige Unterkapitel müssen extra importiert werden
	import javax.swing.*;        // Elemente beginnen mit J

	//Ganze Klasse ist ein Frame
public class InsertGUI extends JFrame //throws Exception
                     implements ActionListener  
{
	// Eigenschaften SQL
    private static final String url = "jdbc:mysql://localhost/mod223?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "IBZ12345$"; 
    static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	
	// Eigenschaft Nummer letzter Datensatz
	static int id;
	
	// Eigenschaften GUI
	/* __________________________________________
	 * ¦      Erfassen einer Vokabel            ¦
	 * ¦________________________________________¦    Titelbalken
	 * ¦                                        ¦
	 * ¦                                        ¦
	 * ¦              ListBox                   ¦    Listbox   BorderLayout CENTER
	 * ¦                                        ¦
	 * ¦                                        ¦
	 * ¦                                        ¦
	 * ¦                                        ¦
	 * ¦________________________________________¦    BorderLayout SOUTH
	 * ¦ Deutsch ¦  TextBox ¦  Fremd ¦          ¦    Panel GridLayout 2 Zeilen 4 Spalten
	 * ¦________________________________________¦    Label TextBox Label TextBox
   *   |Select | TextBox  |	Abfrage  |	label	|	 Label TextBox Füller Füller 
   * ¦ Filter  ¦  TextBox ¦  einf! ¦ loesch!  ¦
   * ¦________________________________________¦    Label TextBox Button Button
	 * 
	 * 
	 * 
	 */
	
	
	
	JList myList;          // Liste
	JScrollPane jsp;       // Feld mit Rollbalken
	DefaultListModel dlm ; // Gefäss für ListenInhalt
	JLabel lbl_deutsch;
	JTextField txt_deutsch;
	JLabel lbl_fremd;
	JTextField txt_fremd;
	
	JLabel lbl_select;
	JTextField txt_select;
	JButton cmd_abfrage;
	JLabel lbl_fueller2;
	
	JLabel lbl_filter;
	JTextField txt_filter;
	JButton einfuegen;
	JButton loeschen;   
	JButton cmd_delete;
	
	// Methoden Konstruktor actionPerformed main
	public InsertGUI()    // Konstruktor
	{
		super(); // Konstruktor der Oberklasse aufrufen
		this.setTitle("Vokabel erfassen INSERT");
           // "Kreuz" oben links beendet die Anwendung 		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	

		// Objekte der Steuerelemente erzeugen
		lbl_deutsch = new JLabel("Deutsch");
		txt_deutsch = new JTextField("");

		lbl_fremd = new JLabel("Fremd");
		txt_fremd = new JTextField("");

		lbl_filter = new JLabel("Filter");
		txt_filter = new JTextField("");		

		lbl_select = new JLabel("select");
		txt_select = new JTextField("where vID < 8");
		
		cmd_abfrage = new JButton("Abfrage");
		cmd_abfrage.addActionListener(this);
		cmd_abfrage.setActionCommand("abfrage");
		//lbl_fueller2 = new JLabel("---");

		cmd_delete = new JButton("Delete");
		cmd_delete.addActionListener(this);
		cmd_delete.setActionCommand("delete");
		
		einfuegen = new JButton("Einfügen");
		einfuegen.addActionListener(this);
		einfuegen.setActionCommand("einfuegen");
		
		loeschen = new JButton("Reset");   
		loeschen.addActionListener(this);    
		loeschen.setActionCommand("loeschen");
		
		dlm = new DefaultListModel();   // Inhalt der ListBox
		myList = new JList();           // ListBox
		myList.setModel(dlm);           // Inhalt der ListBox zuordnen
		
		jsp = new JScrollPane(myList);  // ListBox mit ScrollEigenschaften umhüllen

		// Layout und Panel
		JPanel platz = new JPanel();
		                           //  z s az as    AbstandSpalten
		platz.setLayout(new GridLayout(3,4,10,10));
		platz.add(lbl_deutsch); 
		platz.add(txt_deutsch);
		platz.add(lbl_fremd); 
		platz.add(txt_fremd);
		
		platz.add(lbl_select);
		platz.add(txt_select);
		platz.add(cmd_abfrage);
		platz.add(cmd_delete);
		
		platz.add(lbl_filter); 
		platz.add(txt_filter);
		platz.add(einfuegen);
		platz.add(loeschen);     //    Reihenfolge spielt eine Rolle
		
		// Panel auf Frame ziehen
		
		this.setLayout(new BorderLayout());
		this.add(jsp, BorderLayout.CENTER);
		this.add(platz, BorderLayout.SOUTH);
		
		// am Schluss des Konstruktors
		this.setSize(800,400);
		this.setVisible(true);
	}
	
	
	
	public void keyPressed(KeyEvent ke) {}     // drei obligatorische Listener
	public void keyTyped(KeyEvent ke)   {}
	public void keyReleased(KeyEvent ke){}
		
		
	public void actionPerformed(ActionEvent evt) 
	{
		try 
		{
			if(evt.getActionCommand()=="loeschen")
		        loeschen();
			else
				if(evt.getActionCommand()=="einfuegen")
				einfuegen();
			else
				if(evt.getActionCommand()=="delete")
				delete();
			else
				abfragen();
		}			
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
	        System.exit(0) ;
	    }
	}		
	
	public void delete() throws Exception
	{
		 stmt = con.createStatement();
		try
		 {
		//Can be used to select element from list to be deleted from database table
        int index = myList.getSelectedIndex();
        //System.out.println("Index Selected: " + index);
        String row = (String) myList.getSelectedValue();
        //System.out.println("Value Selected: " + row);
        String SelectedID = row.substring(row.indexOf('=')+1,row.indexOf('F'));
        System.out.println(SelectedID);
        
        boolean eingabe = stmt.execute("Delete From vokabel where vID ="+SelectedID+";");
		dlm.clear();
		dlm.addElement("Erfolgreich gelöscht");
		 }
		 catch(Exception e)
		 {
			 dlm.addElement("Bitte whälen Sie die Datenzeile, dass Sie löschen möchten");
		 }
	}
	
	public void loeschen()
	{
		txt_deutsch.setText("");
		txt_fremd.setText("");
		//txt_filter.setText("");
	}
	
	public static void getHoechstID()
	{
		//---------------
      try
      {
         stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM vokabel order by vid desc;" );
      
         // Ausgabe ResultSet
	       if ( rs.next() ) 
	       {
            id = rs.getInt("vid");
	       }
 
      } 
      catch (Exception e) 
      {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0) ;
      }
      finally 
      {
          System.out.println("Höchster gefunden:" + id);
      }
  }
	public void einfuegen() throws Exception
	{
	  dlm.clear();
      stmt = con.createStatement();
      String befehl;
      befehl = ("insert into vokabel (vID,fremd,deutsch,dfok,fdok,dffalse,fdfalse,ffilter)");
      befehl = befehl + "values(" + ++id + ",\"";          // kompaktes Konstrukt
      befehl = befehl + txt_fremd.getText() + ("\",\"");
      befehl = befehl + txt_deutsch.getText() +  ("\",0,0,0,0,\"");
      befehl = befehl + txt_filter.getText() +  ("\")");
      System.out.println(befehl);
      dlm.addElement(befehl);
      
      
      boolean eingabe = stmt.execute(befehl);
	}
	
	public void abfragen()
	{
	   try 
	   {
		    dlm.clear();
	        stmt = con.createStatement();
	        String Befehl = txt_select.getText();
	        
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM vokabel;");
	        if(Befehl != "")
	         rs = stmt.executeQuery( "SELECT * FROM vokabel "+Befehl+";");
	     
	        
	        
	        // Ausgabe ResultSet
 	        while ( rs.next() ) 
 	        {
               int id = rs.getInt("vid");
               String  fremd = rs.getString("FREMD");
               String  deutsch = rs.getString("DEUTSCH");
               int dfok  = rs.getInt("DFOK");
               int fdok  = rs.getInt("FDOK");
               int dffalse  = rs.getInt("DFFALSE");
               int fdfalse  = rs.getInt("FDFALSE");
               String  filter = rs.getString("FFILTER");
               String zeile = ("vID = " + id+" FREMD = " + fremd+" DEUTSCH = " + deutsch+" DFOK = " + dfok+
            	 " FDOK = " + fdok+ " DFFALSE = " + dffalse+" FDFALSE = " + fdfalse+ " FILTER = " + filter );
               System.out.print( "vID = " + id );
               System.out.print( " FREMD = " + fremd );
               System.out.print( " DEUTSCH = " + deutsch );
               System.out.print( " DFOK = " + dfok );
               System.out.print( " FDOK = " + fdok );
               System.out.print( " DFFALSE = " + dffalse );
               System.out.print( " FDFALSE = " + fdfalse );
               System.out.print( " FILTER = " + filter );
               System.out.println();
               dlm.addElement(zeile);
          }
	   } 
       catch (Exception e) 
	   {
	       e.printStackTrace();
	       System.err.println(e.getClass().getName()+": "+e.getMessage());
	       System.exit(0) ;
	   }
	   finally 
	   {
           System.out.println("Ende auflisten");
	   }
	}
	
	public static void main(String[] args)   throws Exception  
	{
      try 
      {
          Class.forName("com.mysql.cj.jdbc.Driver");
          con = DriverManager.getConnection(url, user, password);
          //commented since doesn't exists in Java 6
          //System.out.println(con.getSchema());
          System.out.println(con.getCatalog());
          getHoechstID();
          InsertGUI ig = new InsertGUI();           
      } 
      catch (Exception e) 
      {
          e.printStackTrace();
          System.err.println(e.getClass().getName()+": "+e.getMessage());
          System.exit(0) ;
      }
      finally 
      {
         // con.close();
      }
  }
}
