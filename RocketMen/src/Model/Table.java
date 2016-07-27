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
		statement = connection.createStatement();
		String SQL_Query_Text = "SELECT downrangedist, altitude FROM " + tableName;
		ResultSet rs = statement.executeQuery(SQL_Query_Text);
		rs.close();
	}
	public ResultSet queryTable(String tableName, String colName, int rowNumber){
		
		return null;
	}
	public void updateTable(String tableName){
		
	}
	public void deleteTable(String tableName){
		
	}
	public String getTableName(){
		
		return myTableName;
	}
	
}
