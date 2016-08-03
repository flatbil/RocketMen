package Controller;

import java.sql.SQLException;
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
import Model.Import;
import View.Display;
import View.UI;

//This is the rocket telemetry main class.
public class Main {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://sql3.freesqldatabase.com:3306/sql3128832";
    private static final String USERNAME = "sql3128832";
    private static final String PASSWORD = "42lZ1vdM6D";
    
    public static void main(String[] args) throws SQLException {
    	Connection connection = null;
    	 try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			UI u = new UI(connection);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    	connection.close();
    }//end main
}
