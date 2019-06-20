package gui_db_sql;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class AdressenAIO extends JFrame //throws Exception
                         implements ActionListener 
{
    private static final String url = "jdbc:mysql://localhost/mod223?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "IBZ12345$"; 
    static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	
	
	//--------------------------
	
	// Eigenschaft Nummer letzter Datensatz
	static int id;
	
	// Eigenschaften GUI
	/* __________________________________________
	 * ¦      Erfassen einer Adresse            ¦
	 * ¦________________________________________¦    Titelbalken
	 * ¦                                        ¦
	 * ¦                                        ¦
	 * ¦              ListBox                   ¦    Listbox   BorderLayout CENTER
	 * ¦                                        ¦
	 * ¦                                        ¦
	 * ¦                                        ¦
	 * ¦                                        ¦
	 * ¦________________________________________¦    BorderLayout SOUTH
	 * ¦                                        ¦    Panel GridLayout 1 Zeilen 4 Spalten
     * ¦ Anzeigen¦  TextBox ¦  einf! ¦ loesch!  ¦
     * ¦________________________________________¦    Button TextBox Button Button
	 * ¦ Name	 ¦  TextBox ¦Vorname ¦ Textbox  ¦
     * ¦________________________________________¦    Label TextBox Label TextBox
	 * ¦ Strasse ¦  TextBox ¦  Land  ¦ Textbox  ¦
     * ¦________________________________________¦    Label TextBox Label TextBox 
     * ¦ PLZ     ¦  TextBox ¦  Ort   ¦ Textbox  ¦
     * ¦________________________________________¦    Label TextBox Label TextBox
       ¦ Füller	 ¦  Füller  ¦ Füller ¦ Insert!  ¦
     * ¦________________________________________¦    Label Label Label Button
	 */
	
	
	
	JList myList;          // Liste
	JScrollPane jsp;       // Feld mit Rollbalken
	DefaultListModel dlm ; // Gefäss für ListenInhalt
	JButton anzeigen;
	JTextField txt_filter;
	JButton einfuegen;
	JButton loeschen;  
	
	JLabel lbl_name;
	JTextField txt_name;
	JLabel lbl_vorname;
	JTextField txt_vorname;
	
	JLabel lbl_strasse;
	JTextField txt_strasse;
	JLabel lbl_land;
	JTextField txt_land;
	
	JLabel lbl_PLZ;
	JTextField txt_PLZ;
	JLabel lbl_ort;
	JTextField txt_ort;
	
	JLabel lbl_fueller1;
	JLabel lbl_fueller2;
	JLabel lbl_fueller3;
	JButton insert;
	
	
	// Methoden Konstruktor actionPerformed main
	public AdressenAIO()    // Konstruktor
	{
		super(); // Konstruktor der Oberklasse aufrufen
		this.setTitle("Adressen anzeigen");
             // "Kreuz" oben links beendet die Anwendung 		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	

		// Objekte der Steuerelemente erzeugen
		txt_filter = new JTextField("");		

		lbl_name = new JLabel("Name");
		lbl_vorname = new JLabel("Vorname");
		lbl_strasse = new JLabel("Strasse");
		lbl_PLZ = new JLabel("PLZ");
		lbl_ort = new JLabel("Stadt");
		lbl_land = new JLabel("Land");
		lbl_fueller1 = new JLabel("--");
		lbl_fueller2 = new JLabel("--");
		lbl_fueller3 = new JLabel("--");
		
		
		
		txt_name = new JTextField("");
		txt_vorname = new JTextField("");
		txt_strasse = new JTextField("");
		txt_PLZ = new JTextField("");
		txt_ort = new JTextField("");
		txt_land = new JTextField("");
		
		
		einfuegen = new JButton("Alle einfügen");
		einfuegen.addActionListener(this);
		einfuegen.setActionCommand("einfuegen");
		
		loeschen = new JButton("löschen");   
		loeschen.addActionListener(this);    
		loeschen.setActionCommand("loeschen");

		anzeigen = new JButton("anzeigen");
		anzeigen.addActionListener(this);
		anzeigen.setActionCommand("anzeigen");
		
		insert = new JButton("insert");
		insert.addActionListener(this);    
		insert.setActionCommand("insert");
		
		dlm = new DefaultListModel();   // Inhalt der ListBox
		myList = new JList();           // ListBox
		myList.setModel(dlm);           // Inhalt der ListBox zuordnen
		jsp = new JScrollPane(myList);  // ListBox mit ScrollEigenschaften umhüllen

		// Layout und Panel
		JPanel platz = new JPanel();
		                           //  z s az as    AbstandSpalten
		platz.setLayout(new GridLayout(5,4,10,10));
		platz.add(anzeigen); 
		platz.add(txt_filter);
		platz.add(einfuegen);
		platz.add(loeschen);     //    Reihenfolge spielt eine Rolle
		platz.add(lbl_name); 
		platz.add(txt_name); 
		platz.add(lbl_vorname); 
		platz.add(txt_vorname); 
		platz.add(lbl_strasse);
		platz.add(txt_strasse);
		platz.add(lbl_land); 
		platz.add(txt_land); 
		platz.add(lbl_PLZ);
		platz.add(txt_PLZ);
		platz.add(lbl_ort); 
		platz.add(txt_ort);
		platz.add(lbl_fueller1);
		platz.add(lbl_fueller2);
		platz.add(lbl_fueller3); 
		platz.add(insert);
		
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
				if (evt.getActionCommand()=="einfuegen")
				   einfuegen();
			else
				if(evt.getActionCommand()=="insert")
						insert();
			else
				   auflisten();
		}			
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
	        System.exit(0) ;
	    }
	}		

	
	//--------------------------
	
	public static void oeffnen() 
	{
		try 
		{    
			Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
	        System.out.println(conn.getCatalog()+" hier wäre der Katalog");
            System.out.println("Opened database successfully");
	    
	    } 
        catch (Exception e) 
	    {
	        e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
	        System.exit(0) ;
	    }
	    finally 
	    {
            System.out.println("Ende verbinden/oeffnen");
	    }
	}
	
	public void auflisten()
	{
	   try 
	   {
		    dlm.clear();  // JLIST löschen GUI
		    String filter = txt_filter.getText();
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM adressen " + filter + ";" );
	        
	        // Ausgabe ResultSet
 	        while ( rs.next() ) 
 	        {
               int id = rs.getInt("PersonID");
               String  name = rs.getString("Name");
               String  vorname = rs.getString("Vorname");
               String  strasse = rs.getString("Strasse");
               String  land = rs.getString("Land");
               int plz  = rs.getInt("PLZ");
               String  stadt = rs.getString("City");
               
               String ausgabe = "PersonID = " + id + " Name = " + name + " Vorname = " + vorname + " Strasse = " + strasse ;
               ausgabe = ausgabe + " Land = " + land + " PLZ = " + plz + " Stadt = " + stadt ;
               System.out.println(ausgabe);
               dlm.addElement(ausgabe);
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
	
	public void einfuegen()
	{
		   try 
		   {
		        stmt = conn.createStatement();
		        boolean eingabe = stmt.execute
	("insert into adressen (PersonID, Name, VorName, Strasse, Land, PLZ, City) "
			+  "values(1,\"Müller\",\"Alice\",\"Sonnhalde 24\",\"CH\",3400,\"Burgdorf\")");

		        System.out.println(eingabe);

		        eingabe = stmt.execute
		        ("insert into adressen (PersonID, Name, VorName, Strasse, Land, PLZ, City) "
		    			+  "values(2,\"Meier\",\"Manuela\",\"Obsthalde 5\",\"CH\",8105,\"Watt\")");

		        eingabe = stmt.execute
		        ("insert into adressen (PersonID, Name, VorName, Strasse, Land, PLZ, City) "
		    			+  "values(3,\"Friedli\",\"Fritz\",\"Grünberg 6\",\"CH\",8848,\"Mühlehorn\")");

		        eingabe = stmt.execute
		        ("insert into adressen (PersonID, Name, VorName, Strasse, Land, PLZ, City) "
		    			+  "values(4,\"Zgraggen\",\"Alfred\",\"Bergstrasse 17\",\"CH\",4900,\"Langenthal\")");

		        eingabe = stmt.execute
		        ("insert into adressen (PersonID, Name, VorName, Strasse, Land, PLZ, City) "
		    			+  "values(5,\"Abegglen\",\"Zoe\",\"Aprikosenweg 13\",\"CH\",3415,\"Hasle-Rüegsau\")");

		        eingabe = stmt.execute
		        ("insert into adressen (PersonID, Name, VorName, Strasse, Land, PLZ, City) "
		    			+  "values(6,\"Moser\",\"Monika\",\"Nussgasse 69\",\"CH\",4242,\"Laufen\")");

		        eingabe = stmt.execute
		        ("insert into adressen (PersonID, Name, VorName, Strasse, Land, PLZ, City) "
		    			+  "values(7,\"Pulver\",\"Liselotte\",\"Bahnhofstrasse 4\",\"CH\",3000,\"Bern\")");

		        eingabe = stmt.execute
		        ("insert into adressen (PersonID, Name, VorName, Strasse, Land, PLZ, City) "
		    			+  "values(8,\"Traber\",\"Tristan\",\"Treberweg 15\",\"CH\",4303,\"Kaiseraugst\")");

		        eingabe = stmt.execute
		        ("insert into adressen (PersonID, Name, VorName, Strasse, Land, PLZ, City) "
		    			+  "values(9,\"Degen\",\"Alfred\",\"Südweg 55\",\"CH\",4253,\"Liesberg\")");

		        eingabe = stmt.execute
		        ("insert into adressen (PersonID, Name, VorName, Strasse, Land, PLZ, City) "
		    			+  "values(10,\"Gerber\",\"Gustav\",\"Rue d'Etoile 33\",\"CH\",1200,\"Genf\")");

		        eingabe = stmt.execute
		        ("insert into adressen (PersonID, Name, VorName, Strasse, Land, PLZ, City) "
		    			+  "values(11,\"Obrist\",\"Olga\",\"oberer Rain 7\",\"CH\",4313,\"Möhlin\")");
		        
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	            System.err.println(e.getClass().getName()+": "+e.getMessage());
	            System.exit(0) ;
	        }
	        finally 
	        {
	            System.out.println("Ende einfuegen");
	        }
	}
	
	public static void kreieren()
	{
		   try 
		   {
		        String eingabe = "CREATE TABLE Adressen (PersonID int,Name varchar(25),VorName varchar(25)";
		        eingabe = eingabe + ",Strasse varchar(55), Land varchar(5), PLZ int, City varchar(55) );";
                System.out.println(eingabe);
		        stmt = conn.createStatement();
                boolean x = stmt.execute(eingabe);
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	            System.err.println(e.getClass().getName()+": "+e.getMessage());
	            System.exit(0) ;
	        }
	        finally 
	        {
	            System.out.println("Ende einfuegen");
	        }
	}
	
	public void loeschen()
	{
		   try 
		   {
		        String eingabe = "delete from Adressen;";
                System.out.println(eingabe);
		        stmt = conn.createStatement();
                boolean x = stmt.execute(eingabe);
                dlm.clear();
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	            System.err.println(e.getClass().getName()+": "+e.getMessage());
	            System.exit(0) ;
	        }
	        finally 
	        {
	            System.out.println("Ende loeschen");
	        }
	}
	
	public static void getHoechstID()
	{
		//---------------
      try
      {
         stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM adressen order by PersonID desc;" );
      
         // Ausgabe ResultSet
	       if ( rs.next() ) 
	       {
            id = rs.getInt("PersonID");
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
	
	public void insert() throws Exception
	{

			String eingabe = "insert into adressen (PersonID, Name, VorName, Strasse, Land, PLZ, City) ";
			eingabe =  eingabe + "values(" + ++id + ",\"";  
			eingabe = eingabe + txt_name.getText() + ("\",\"");
			eingabe = eingabe + txt_vorname.getText() + ("\",\"");
			eingabe = eingabe + txt_strasse.getText() + ("\",\"");
			eingabe = eingabe + txt_land.getText() + ("\",\"");
			eingabe = eingabe + txt_PLZ.getText() + ("\",\"");
			eingabe = eingabe + txt_ort.getText()+  ("\")");
			System.out.println(eingabe);
			
			boolean executed = stmt.execute(eingabe);

	}
	public static void main( String args[] ) 
	{
		oeffnen();
		getHoechstID();
		AdressenAIO myaAIO = new AdressenAIO();
		//kreieren();		Nur läuft einmal um die Tabelle zu erstellen
	}
	
}

