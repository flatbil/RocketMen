import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class LaunchInsert {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private static final String DB_URL = "jdbc:mysql://localhost/launchdata";

    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) {
        importer();
    }//end main
	   
        public static void importer() {
            Connection conn = null;
            Statement stmt = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");

                  //STEP 3: Open a connection
                  System.out.println("Connecting to database...");
                  conn = DriverManager.getConnection(DB_URL,USER,PASS);

                  //STEP 4: Execute a query
                  System.out.println("Creating statement...");
                  stmt = conn.createStatement();
                  String sql;
                  sql = "SELECT downrangedist, altitude FROM Launch1";
                  
                  updateDB(conn);  // <-- Look MA ... no Hands!!
                  
                  ResultSet rs = stmt.executeQuery(sql);

                  //STEP 5: Extract data from result set
                  while(rs.next()){
                     //Retrieve by column name
                     int downrangedist  = rs.getInt("downrangedist");
                     int altitude = rs.getInt("altitude");

                     //Display values
                     System.out.print("downrangedist: " + downrangedist);
                     System.out.println(", altitude: " + altitude);
                     
                  }
                  //STEP 6: Clean-up environment
                  rs.close();
                  stmt.close();
                  conn.close();
               }catch(SQLException se){
                  //Handle errors for JDBC
                  se.printStackTrace();
               }catch(Exception e){
                  //Handle errors for Class.forName
                  e.printStackTrace();
               }finally{
                  //finally block used to close resources
                  try{
                     if(stmt!=null)
                        stmt.close();
                  }catch(SQLException se2){
                  }// nothing we can do
                  try{
                     if(conn!=null)
                        conn.close();
                  }catch(SQLException se){
                     se.printStackTrace();
                  }//end finally try
               }//end try
               System.out.println("Goodbye!");
        }
    
	   public static void updateDB(Connection conn) throws SQLException {
		   // Create statement object
		   Statement stmt = conn.createStatement();
		   // Set auto-commit to false
		   conn.setAutoCommit(false);
		   // Create SQL statement
		   
		   String SQL = null;
	       try
	        {
//	          FileInputStream file = new FileInputStream(new File("launchdata.xlsx"));
	            FileInputStream file = new FileInputStream(new File("WhatALaunchShouldLookLike.xlsx"));

	            //Create Workbook instance holding reference to .xlsx file
	            XSSFWorkbook workbook = new XSSFWorkbook(file);

	            //Get first/desired sheet from the workbook
	            XSSFSheet sheet = workbook.getSheetAt(0);

	            //Iterate through each rows one by one
	            Iterator<Row> rowIterator = sheet.iterator();
	            while (rowIterator.hasNext()) 
	            {
	                Row row = rowIterator.next();
	                //For each row, iterate through all the columns
	                Iterator<Cell> cellIterator = row.cellIterator();
	                
	                while (cellIterator.hasNext()) 
	                {
	                    Cell cell = cellIterator.next();
	                    Cell cell2 = cellIterator.next();
	                    //Check the cell type and format accordingly
	                    switch (cell.getCellType()) 
	                    {
	                        case Cell.CELL_TYPE_NUMERIC:
	                            System.out.print(cell.getNumericCellValue() + "\t");
                                System.out.print(cell2.getNumericCellValue() + "\t");

	                            SQL = "INSERT INTO launch1 (downrangedist, altitude) " + "VALUES(" + cell.getNumericCellValue() + " ," + cell2.getNumericCellValue() + ");";
	                            stmt.addBatch(SQL);
	                            int[] count = stmt.executeBatch();
	                            conn.commit();


	                            break;
	                        case Cell.CELL_TYPE_STRING:
	                            System.out.print(cell.getStringCellValue() + "\t");
	                            break;
	                    }
	                    
	                }
	                System.out.println("");
	            }
	            file.close();
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
//		   String SQL = "DELETE FROM launch1 " + "WHERE downrangedist=*";
		   // Add above SQL statement in the batch.
	   }
	   
}//end FirstExample
