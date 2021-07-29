import java.sql.*;
import java.nio.file.*;
import java.io.*;
import java.util.*;
 
public class Base {
	static String sqlQuery = "SELECT first_name, count (actor_id) from sakila.actor group by ('MORGAN', 'RIP') ";
	 public static void main(String[] args) {
         try{
            // Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
             try (Connection conn = getConnection();
            		Statement stmt = conn.createStatement();
            		 ResultSet rs = stmt.executeQuery(sqlQuery);
            		 ){
                  
                System.out.println("Connection to Store DB succesfull!");
                
                
                 			
    			
    			while(rs.next()) {
    				
    				int empNo = rs.getInt("actor_id");
    				String fName = rs.getString("first_name");
    				String lName = rs.getString("last_name");
    				String time = rs.getString("last_update");
    				System.out.println("<"+empNo+"> "+fName+" - "+lName+ "->"+time);
    			}
                
                
                
                
                
                
             }
         }
         catch(Exception ex){
             System.out.println("Connection failed...");
              
             System.out.println(ex);
         }
    }
     
    public static Connection getConnection() throws SQLException, IOException{
         
        Properties props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("src\\database.properties").toAbsolutePath())){
            props.load(in);
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");
         
        return DriverManager.getConnection(url, username, password);
    }
	
}
