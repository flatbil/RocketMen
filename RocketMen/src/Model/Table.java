package Model;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

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
		String SQL_Query_Text = "SELECT time, downrangedist, altitude FROM " + tableName;
		ResultSet rs = statement.executeQuery(SQL_Query_Text);
		rs.close();
		statement.close();
	}
	public ResultSet queryTable(String tableName, String colName, int rowNumber){
		
		return null;
	}
	
    private String getFile() {
        HashMap hm = new HashMap();
        int j = 1;
        System.out.println("\nHere are a list of files to choose from:");
        Scanner console = new Scanner(System.in);
        String filename = "";
        File rootFiles = new File("./");
        File[] fileList = rootFiles.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile() && fileList[i].toString().substring(fileList[i].toString().length() -1 ).equals("x")) {
                System.out.println(j + ". " + fileList[i].getName());
                //System.out.println("\t" + fileList[i].getName());
                hm.put(j, fileList[i].getName());
                j++;
            } 
        }
        System.out.print("\nEnter the number for the file you want to import data from> ");
        filename = (String) (hm.get(console.nextInt()));
        return filename;
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
        	String filename = getFile();
            FileInputStream file = new FileInputStream(new File(filename));//"Orbcomm2.xlsx"));
            //FileInputStream file = new FileInputStream(new File("Orbcomm2.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    Cell cell3 = cellIterator.next();
                    Cell cell2 = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
//                            System.out.print(cell.getNumericCellValue() + "\t");
//                            System.out.print(cell2.getNumericCellValue() + "\t");
                            SQL_Query_Text = "INSERT INTO " + theTableName + " (time, downrangedist, altitude) " + "VALUES(" + cell.getNumericCellValue() + " ," + cell2.getNumericCellValue()+ " ," + cell3.getNumericCellValue() + ");";
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
	
	public String getTableName(){
		
		return myTableName;
	}
	
    public Connection getConnection(){
    	
    	return myConnection;
    }
	public void createTable(Connection connection, String tableName) throws SQLException {
		Statement statement = connection.createStatement();
		String SQL_Create_Text = "CREATE TABLE " + tableName+"\n(\ntime int,\ndownrangedist int,\naltitude int\n);";
		System.out.println(SQL_Create_Text);
		statement.executeUpdate(SQL_Create_Text);
		//rs.close();
		statement.close();
		
	}
	
}
