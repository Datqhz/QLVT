
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseHelper {
     public static Connection openConnection()throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connect = "jdbc:sqlserver://localhost:1433;databaseName=QLVT;user=sa;password=221544;encrypt=false";
        Connection  con = DriverManager.getConnection(connect);
       return con;
   }
}
