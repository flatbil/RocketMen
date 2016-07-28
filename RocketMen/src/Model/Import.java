package Model;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.jdbc.DatabaseMetaData;

public class Import {
    
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://sql3.freesqldatabase.com:3306/sql3128832";
    private static final String USERNAME = "sql3128832";
    private static final String PASSWORD = "42lZ1vdM6D";
    
    private Table myTable;
    private String myTableName;
    private ArrayList<Table> myTables;

    public Import(String tableName) {
        myTableName = tableName;
    	importer(tableName);
    }
    
    private void importer(String tableName) {
        Connection connection = null;
        //Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to DB");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("creating a SQL statement");
            //Accesses the meta data for the database.
            DatabaseMetaData meta = (DatabaseMetaData) connection.getMetaData();
            //Retrieves the list of tables matching the tableName.
            ResultSet res = meta.getTables(null, null, tableName, 
            	     new String[] {"TABLE"});
            //If the table name is found then it creates a new table. 
            	if(res.next()) {
            		System.out.println("We already have a " + tableName +"\nTry Again:");
            		  //add a getTable(String name) method and then update the table.
            		  //myTable.updateDB(connection, tableName);
            	  } else {
            		  myTable = new Table(tableName, connection);
            		  myTable.updateDB(connection, tableName);
            		  myTables.add(myTable);
            		  //myTable.createTable(connection, tableName);
            		  System.out.println("Import Complete");
            	  }
            //statement = connection.createStatement(); 	handled in table if a new one is made.
            //String SQL_Query_Text = "SELECT downrangedist, altitude FROM " + myTable.getTableName();
            //Also in Table
            
            //ResultSet rs = statement.executeQuery(SQL_Query_Text);
            //rs.close();
            //statement.close();
            connection.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
        
    }
    public ArrayList<Table> getTableList(){
    	return myTables;
    }
    /*
     * This code moved to the Table class.
     */
//    private void updateDB(Connection theConn) throws SQLException {
//        Statement statement = theConn.createStatement();
//        try {
//            theConn.setAutoCommit(false);
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        String SQL_Query_Text = null;
//        try {
//            FileInputStream file = new FileInputStream(new File("WhatALaunchShouldLookLike.xlsx"));
//            XSSFWorkbook workbook = new XSSFWorkbook(file);
//            XSSFSheet sheet = workbook.getSheetAt(0);
//            Iterator<Row> rowIterator = sheet.iterator();
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
//                Iterator<Cell> cellIterator = row.cellIterator();
//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//                    Cell cell2 = cellIterator.next();
//                    switch (cell.getCellType()) {
//                        case Cell.CELL_TYPE_NUMERIC:
////                            System.out.print(cell.getNumericCellValue() + "\t");
////                            System.out.print(cell2.getNumericCellValue() + "\t");
//                            SQL_Query_Text = "INSERT INTO launch1 (downrangedist, altitude) " + "VALUES(" + cell.getNumericCellValue() + " ," + cell2.getNumericCellValue() + ");";
//                            statement.addBatch(SQL_Query_Text);
//                            int[] count = statement.executeBatch();
//                            theConn.commit();
//                            break;
//                    }
//                }
////                System.out.println("");
//            }
//            file.close();
//        } 
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//   }   
    
}
