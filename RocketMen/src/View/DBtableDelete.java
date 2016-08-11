package View;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class DBtableDelete {

//    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    private static final String DB_URL = "jdbc:mysql://localhost/launchdata";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "1234";


//    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
//    private static final String DB_URL = "jdbc:mysql://sql3.freesqldatabase.com:3306/sql3128832";
//
//    static final String USERNAME = "sql3128832";
//    static final String PASSWORD = "42lZ1vdM6D";
    

    
    public DBtableDelete(String theName, Connection theConnection) {
        deleteStuff(theName, theConnection);
    }
    
    private void deleteStuff(String theName, Connection theConnection) {
        Connection connection = theConnection;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Connection created in Main.
//            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE " + theName); // Quick Delete all data on server
            statement.close();
            //connection.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch(SQLException se2) {
            }
//            try {
//                if (connection != null) {
////                    connection.close();
//                }
//            } catch(SQLException se) {
//                se.printStackTrace();
//            }
        }
    }
    
}
