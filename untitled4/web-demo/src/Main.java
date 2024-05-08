import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public Connection getConn() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql:///test?characterEncoding=utf8&useSSL=false";
            String username = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public void closeConn(Connection conn) {
        try {
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Main main = new Main();
        Connection conn = main.getConn();
    }

}



