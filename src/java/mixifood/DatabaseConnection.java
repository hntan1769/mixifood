package mixifood;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Macbook Air
 */
public class DatabaseConnection {
    private static Connection con;
    private static String URL = "jdbc:mysql://localhost:3306/mixifood";
    private static String USER = "root";
    private static String PASSWORD = "";
//get connection to sql server 
    public static Connection getMySQLConnection() {
        try {
            URL = "jdbc:mysql://localhost:3306/mixifood";
            USER = "root";
            PASSWORD = "";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = (Connection)DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("succesfull");
        } catch (SQLException ex) {
            System.out.println("fail");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

// check status connect
    public static boolean isConnected() throws SQLException {//check status connection
        if (getMySQLConnection()!= null) {
            return true;
        } else {
            return false;
        }
    }
//close connection
    public static void closeConnection() {//losing connection
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, URL, URL);
        }      
    }
    public static void main(String[] args) {
        DatabaseConnection a = new DatabaseConnection();
        a.getMySQLConnection();
    }
}
