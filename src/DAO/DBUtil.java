package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBUtil {

	private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {

                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306/Hosp";
                String user = "scott";
                String password = "tiger";
            	
            	Class.forName(driver); //verifica se o driver do bd está no class path...
                
                connection = DriverManager.getConnection(url, user, password);
                
                connection.setAutoCommit(true);
            
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            /*} catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();*/
            }
            return connection;
        }

    }
}
