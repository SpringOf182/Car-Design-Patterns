package car;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class DBManager extends HttpServlet {
	ServletConfig config;                           
    private static String username;                  
    private static String password;                   
    private static String url;                        
    private static Connection connection;             

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);                                  
        this.config = config;                               
        username = config.getInitParameter("DBUsername");    
        password = config.getInitParameter("DBPassword");    
        url = config.getInitParameter("ConnectionURL");      
    }

  
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public static void closeAll(Connection connection, Statement statement,
            ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
                System.out.println("resultSetl:close");
            }
            if (statement != null) {
                statement.close();
                System.out.println("statement:close");
            }
            if (connection != null) {
                connection.close();
                System.out.println("connection:close");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void closeAll(Connection connection, Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
