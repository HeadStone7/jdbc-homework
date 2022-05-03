package DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    public static String URL = "jdbc:mysql://localhost:3306/school?serverTimezone=UTC";
    //My mysql username and password
    public static String USERNAME="root";
    public static String PWD="123456789";
    //The mysql drive connector path
    public static String DRIVE="com.mysql.cj.jdbc.Driver";


    /**
     * make connection to database
     * @return
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(DRIVE);
            conn = DriverManager.getConnection(URL, USERNAME, PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Close Connection and query's statement
     * @param connection
     * @param statement
     */
    public static void close(Connection connection, Statement statement){
        try {
            if(connection!=null){
                connection.close();
            }
            if(statement!=null){
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
