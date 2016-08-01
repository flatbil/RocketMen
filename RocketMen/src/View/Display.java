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


public class Display {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://sql3.freesqldatabase.com:3306/sql3128832";
    private static final String USERNAME = "sql3128832";
    private static final String PASSWORD = "42lZ1vdM6D";
    public Display(String launchName) {
        showStuff(launchName);
    }
    
    private void showStuff(String launchName) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            String SQL_Query_Text = "SELECT time, downrangedist, altitude FROM " + launchName;
            ResultSet rs = statement.executeQuery(SQL_Query_Text);
            while (rs.next()) {
                int time = rs.getInt("time");
                int downrangedist = rs.getInt("downrangedist");
                int altitude = rs.getInt("altitude");
                System.out.print("time: " + time);
                System.out.print(", downrangedist: " + downrangedist);
                System.out.println(", altitude: " + altitude);  
            }
            rs.close();
            statement.close();
            connection.close();
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
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
}
