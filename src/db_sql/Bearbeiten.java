package db_sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bearbeiten {
	
    private static final String url = "jdbc:mysql://localhost/mod223?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "IBZ12345$"; 
    
    static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	
	public static void main( String args[] )
	{
	   try 
	   {
	       Class.forName("com.mysql.cj.jdbc.Driver");
	       conn = DriverManager.getConnection(url, user, password);
	        //commented since doesn't exists in Java 6
            //System.out.println(con.getSchema());
            System.out.println(conn.getCatalog()+" hier wäre der Katalog");
            System.out.println("Opened database successfully");
            
	        stmt = conn.createStatement();
	        
	         // Bearbeiten
	
	         String sql = "UPDATE vokabel set FFILTER = 'GW200' where vID=1;";
	         stmt.executeUpdate(sql);
//	         conn.commit();

	         // Löschen
	         stmt = conn.createStatement();
	         sql = "DELETE from vokabel where FREMD='la vache qui rit';";
	         stmt.executeUpdate(sql);
//	         conn.commit();

	        
	        
	         conn.close();
	  	   
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0) ;
        }
        finally 
        {
            System.out.println("ende gut alles gut");
        }
    }

}
