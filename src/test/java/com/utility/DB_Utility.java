package com.utility;

import java.sql.*;
import java.util.*;

public class DB_Utility {

    private static Connection conn; // make it static field so we can reuse in every methods we write
    private static Statement stmnt;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd ;

    public static void createConnection() {

        String url = ConfigurationReader.getProperty("url");
        String username = ConfigurationReader.getProperty("username");
        String password = ConfigurationReader.getProperty("password");

        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("CONNECTION SUCCESSFUL !! ");
        } catch (SQLException e) {
            System.out.println("CONNECTION HAS FAILED !!! " + e.getMessage());
        }

    }

    // MAKE ABOVE METHOD ACCEPT 3 PARAMETERS
    //        String connectionStr = ConfigurationReader.getProperty("hr.database.url");
    //        String username = ConfigurationReader.getProperty("hr.database.username");
    //        String password = ConfigurationReader.getProperty("hr.database.password");

    public static void createConnection(String url,String username,String password ) {

        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("CONNECTION SUCCESSFUL !! ");
        } catch (SQLException e) {
            System.out.println("CONNECTION HAS FAILED !!! " + e.getMessage());
        }

    }

    // Create a method called runQuery that accept a SQL Query
    // and return ResultSet Object
    public static ResultSet runQuery(String query) {

        // ResultSet rs  = null;
        // reusing the connection built from previous method
        try {
            stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmnt.executeQuery(query); // setting the value of ResultSet object
            rsmd = rs.getMetaData() ;  // setting the value of ResultSetMetaData for reuse
        }catch(SQLException e){
            System.out.println("ERROR OCCURRED WHILE RUNNING QUERY "+ e.getMessage() );
        }

        return rs ;

    }

    // create a method to clean up all the connection statement and result set after being used
    public static void destroy() {

        // WE HAVE TO CHECK IF WE HAVE THE VALID OBJECT FIRST BEFORE CLOSING THE RESOURCE
        // BECAUSE WE CAN NOT TAKE ACTION ON AN OBEJCT THAT DOES NOT EXIST
        try {
            if( rs!=null)  rs.close();
            if( stmnt!=null)  stmnt.close();
            if( conn!=null)  conn.close();
        } catch (SQLException e) {
            System.out.println("ERROR OCCURRED WHILE CLOSING RESOURCES " + e.getMessage() );
        }


    }

    /**
     * Method to reset Cursor to before first
     */
    private static  void resetCursor(){
        try {
            rs.beforeFirst();;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    /**
     * Count how many row we have
     *
     * @return the row count of the resultset we got
     */
    public static int getRowCount() {

        int rowCount = 0;

        try {
            rs.last();
            rowCount = rs.getRow();

            // move the cursor back to beforeFirst location to avoid accident
            rs.beforeFirst();

        } catch (SQLException e) {

            System.out.println("ERROR WHILE GETTING ROW COUNT " + e.getMessage());
        }finally {
            resetCursor();
        }

        return rowCount;

    }

    /**
     * Get the column count
     *
     * @return count of column the result set have
     */
    public static int getColumnCount() {

        int columnCount = 0;

        try {
            columnCount = rsmd.getColumnCount();

        } catch (SQLException e) {
            System.out.println("ERROR OCCURRED WHILE GETTING COLUMN COUNT " + e.getMessage() );
        }

        return columnCount ;
    }

    /**
     * a method that return all column names as List<String>
     */
    public static List<String> getAllColumnNamesAsList(){

        List<String> columnNameList = new ArrayList<>();

        try {
            for (int colIndex = 1; colIndex <= getColumnCount(); colIndex++) {

                String columnName = rsmd.getColumnLabel(colIndex);
                columnNameList.add(columnName);
            }

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ALL COLUMN NAMES " + e.getMessage());
        }
        return columnNameList;

    }

    /**
     * Create a method that return all row data as a List<String>
     *
     * @param rowNum Row number you want to get the data
     * @return the row data as List object
     */
    public static List<String> getRowDataAsList(int rowNum) {

        List<String> rowDataList = new ArrayList<>();

        // first we need to move the pointer to the location the rowNum specified
        try {
            rs.absolute(rowNum);

            for (int colNum = 1; colNum <= getColumnCount(); colNum++) {

                String cellValue = rs.getString(colNum);
                rowDataList.add(cellValue);

            }
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ROW DATA AS LIST " + e.getMessage());

        }finally {
            resetCursor();
        }
        return rowDataList;

    }


    /**
     * getting the cell value according to row num and column index
     * @param rowNum
     * @param columnIndex
     * @return the value in String at that location
     */
    public static String getCellValue(int rowNum , int columnIndex) {

        String cellValue = "" ;

        try {
            rs.absolute(rowNum) ;
            cellValue = rs.getString(columnIndex ) ;

        } catch (SQLException e) {
            System.out.println("ERROR OCCURRED WHILE getCellValue " + e.getMessage() );
        }
        return cellValue ;

    }

    /**
     * getting the cell value according to row num and column name
     * @param rowNum
     * @param columnName
     * @return the value in String at that location
     */
    public static String getCellValue(int rowNum ,String columnName){

        String cellValue = "" ;

        try {
            rs.absolute(rowNum) ;
            cellValue = rs.getString( columnName ) ;

        } catch (SQLException e) {
            System.out.println("ERROR OCCURRED WHILE getCellValue " + e.getMessage() );
        }finally {
            resetCursor();
        }
        return cellValue ;

    }

    /**
     * Get First Cell Value at First row First Column
     */
    public static String getFirstRowFirstColumn(){

        return getCellValue(1,1) ;

    }


    /**
     * return value of all cells in that column
     *
     * @param colNum the column number you want to get the list out of
     * @return value of all cells in that column as a List<String>
     */


    public static List<String> getColumnDataAsList(int colNum) {

        List<String> columnDataLst = new ArrayList<>();

        try {
            rs.beforeFirst(); // make sure the cursor is at before first location
            while( rs.next() ){

                String cellValue = rs.getString(colNum) ;
                columnDataLst.add(cellValue) ;
            }
            rs.beforeFirst(); // make sure to reset the cursor to before first location

        } catch (SQLException e) {
            System.out.println("ERROR OCCURRED WHILE getColumnDataAsList " + e.getMessage() );
        }finally {
            resetCursor();
        }


        return columnDataLst ;

    }


    /**
     * return value of all cells in that column using column name
     *
     * @param colName the column name you want to get the list out of
     * @return value of all cells in that column as a List<String>
     */
    public static List<String> getColumnDataAsList(String colName) {

        List<String> columnDataLst = new ArrayList<>();

        try {
            rs.beforeFirst(); // make sure the cursor is at before first location
            while( rs.next() ){

                String cellValue = rs.getString(colName) ;
                columnDataLst.add(cellValue) ;
            }
            rs.beforeFirst(); // make sure to reset the cursor to before first location

        } catch (SQLException e) {
            System.out.println("ERROR OCCURRED WHILE getColumnDataAsList " + e.getMessage() );
        }


        return columnDataLst ;


    }


    /**
     * A method that display all the Result Set data on console
     */
    public static void displayAllData(){

        try {
            rs.beforeFirst();

            while (rs.next()) {

                for (int colNum = 1; colNum <= getColumnCount(); colNum++) {
//                    System.out.print(rs.getString(colNum) + "\t");
                    //  for making it pretty
                    System.out.printf("%-35s", rs.getString(colNum));
                }
                System.out.println();
            }
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE PRINTING WHOLE TABLE " + e.getMessage());
        }
    }

    /**
     * A method that return the row data along with column name as Map object
     * @param rowNum row number you want to get the data
     * @return Map object -- column name as key and cell value as value
     */
    public static Map<String,String> getRowMap(int rowNum){

        Map<String,String>  rowMap = new LinkedHashMap<>() ;

        try{

            rs.absolute(rowNum) ;

            for (int colIndex = 1; colIndex <= rsmd.getColumnCount() ; colIndex++) {

                String columnName   =  rsmd.getColumnLabel( colIndex ) ;
                String cellValue    =  rs.getString( colIndex ) ;
                rowMap.put(columnName, cellValue) ;
                // (    key  ,   value)

            }
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE getting RowMap " + e.getMessage());
        }finally {
            resetCursor();
        }
        return rowMap;

    }

    public static List<Map<String,String>> getAllRowAsListOfMap(){

        List<Map<String,String>> allRowLstOfMap = new ArrayList<>();
        int rowCount = getRowCount() ;
        // move from first row till last row
        // get each row as map object and add it to the list

        for (int rowIndex = 1; rowIndex <= rowCount ; rowIndex++) {

            Map<String,String> rowMap = getRowMap(rowIndex);
            allRowLstOfMap.add( rowMap ) ;

        }
        resetCursor();

        return allRowLstOfMap ;
    }


}


