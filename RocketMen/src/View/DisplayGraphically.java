package View;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class DisplayGraphically {

//    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    private static final String DB_URL = "jdbc:mysql://sql3.freesqldatabase.com:3306/sql3128832";
//    private static final String USERNAME = "sql3128832";
//    private static final String PASSWORD = "42lZ1vdM6D";

    public DisplayGraphically(String launchName, Connection theConnection) {
        showStuff(launchName, theConnection);
    }
    
    private void showStuff(String launchName, Connection theConnection) {
        Connection connection = theConnection;
        Statement statement = null;
        StringBuilder sb = new StringBuilder();
        sb.append("Tutorial15  ");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Connection made in Main
//            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            String SQL_Query_Text = "SELECT time, downrangedist, altitude FROM " + launchName;
            ResultSet rs = statement.executeQuery(SQL_Query_Text);
            while (rs.next()) {
                int time = rs.getInt("time");
                int downrangedist = rs.getInt("downrangedist");
                int altitude = rs.getInt("altitude");
                //TimeUnit.SECONDS.sleep(time);
                sb.append(downrangedist + " " + altitude + " ");
//                if (time % 10 == 0) {
//                    sb.append(downrangedist + " " + altitude + " ");
//                    System.out.print("time: " + time);
//                    System.out.print(", downrangedist: " + downrangedist);
//                    System.out.println(", altitude: " + altitude);  
//                }
            }
            rs.close();
            statement.close();
//            connection.close();
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(sb.toString());   

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
//                    connection.close();
//                }
//            } catch(SQLException se) {
//                se.printStackTrace();
//            }
        }
    }
    
}
