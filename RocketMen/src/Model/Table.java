package Model;
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

public class Table {
	
	private String myTableName;
	private int[] myRows;
	private String[] myCols;
	private String[][] myTable;
	private Connection myConnection;
	private Statement statement;
	
	public Table(String tableName, Connection connection) throws SQLException{
		myTableName = tableName;
		myConnection = connection;
		createTable(connection, tableName);
		statement = connection.createStatement();
		String SQL_Query_Text = "SELECT downrangedist, altitude FROM " + tableName;
		ResultSet rs = statement.executeQuery(SQL_Query_Text);
		rs.close();
		statement.close();
	}
	public ResultSet queryTable(String tableName, String colName, int rowNumber){
		
		return null;
	}
    protected void updateDB(Connection theConn, String theTableName) throws SQLException {
        Statement statement = theConn.createStatement();
        try {
            theConn.setAutoCommit(false);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String SQL_Query_Text = null;
        try {
            FileInputStream file = new FileInputStream(new File("WhatALaunchShouldLookLike.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    Cell cell2 = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
//                            System.out.print(cell.getNumericCellValue() + "\t");
//                            System.out.print(cell2.getNumericCellValue() + "\t");
                            SQL_Query_Text = "INSERT INTO " + theTableName + " (downrangedist, altitude) " + "VALUES(" + cell.getNumericCellValue() + " ," + cell2.getNumericCellValue() + ");";
                            statement.addBatch(SQL_Query_Text);
                            int[] count = statement.executeBatch();
                            theConn.commit();
                            break;
                    }
                }
//                System.out.println("");
            }
            file.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
   } 
	public void deleteTable(Connection conn, String tableName) throws SQLException{
		  Statement stmt = conn.createStatement();
	      
	      String sql = "DROP TABLE " + tableName;
	 
	      stmt.executeUpdate(sql);
	      System.out.println("Table  deleted in given database...");
	}
	public String getTableName(){
		
		return myTableName;
	}
    public Connection getConnection(){
    	
    	return myConnection;
    }
	public void createTable(Connection connection, String tableName) throws SQLException {
		Statement statement = connection.createStatement();
		String SQL_Create_Text = "CREATE TABLE " + tableName+"\n(\ndownrangedist int,\naltitude int\n);";
		System.out.println(SQL_Create_Text);
		statement.executeUpdate(SQL_Create_Text);
		//rs.close();
		statement.close();
		
	}
	
}
