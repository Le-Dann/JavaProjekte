package db_sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Einfuegen 
{
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
	        boolean eingabe = stmt.execute
	        		( "insert into vokabel (vID,fremd,deutsch,dfok,fdok,dffalse,fdfalse,ffilter) values(5,\"vous\",\"ihr\",0,0,0,0,\"if01\")");
	        System.out.println(eingabe);
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
