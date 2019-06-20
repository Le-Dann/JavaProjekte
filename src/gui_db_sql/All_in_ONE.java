package gui_db_sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class All_in_ONE 
{
    private static final String url = "jdbc:mysql://localhost/mod223?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "IBZ12345$"; 
    
    static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	
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
	
	public static void auflisten()
	{
	   try 
	   {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM vokabel;" );
	        
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
               System.out.print( "vID = " + id );
               System.out.print( " FREMD = " + fremd );
               System.out.print( " DEUTSCH = " + deutsch );
               System.out.print( " DFOK = " + dfok );
               System.out.print( " FDOK = " + fdok );
               System.out.print( " DFFALSE = " + dffalse );
               System.out.print( " FDFALSE = " + fdfalse );
               System.out.print( " FILTER = " + filter );
               System.out.println();
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
	
	public static void einfuegen()
	{
		   try 
		   {
		        stmt = conn.createStatement();
		        boolean eingabe = stmt.execute
	("insert into vokabel (vID,fremd,deutsch,dfok,fdok,dffalse,fdfalse,ffilter) "
			+ "values(5,\"la vache qui rit\",\"die Kuh die lacht\",0,0,0,0,\"mar01\")");
		        System.out.println(eingabe);
		  	   
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
	
	
	
	public static void main( String args[] ) 
	{
		oeffnen();
		einfuegen();
		auflisten();
	}
	
}