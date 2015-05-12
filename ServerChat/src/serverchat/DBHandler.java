package serverchat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBHandler {
    
    private Connection conn;
    private static final String DB_PATH = "jdbc:mysql://localhost/chat";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    
    public DBHandler() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_PATH, DB_USER, DB_PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean LoginUser(String user, String pass) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement;
        String query = "SELECT User, Pass FROM Users WHERE User = ? AND Pass = ?";
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            if(!resultSet.first()) {return false;}
            if(resultSet.isLast()) {return true; }
            return false;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public int RegisterUser(String user, String pass) {
        int result = 0;
        PreparedStatement preparedStatement;
        String query = "INSERT INTO Users (user, pass) VALUES (?, ?)";
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}
