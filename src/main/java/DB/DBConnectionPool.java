package DB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBConnectionPool {
    //    protected static Connection initializeDatabase
    private static DBConnectionPool instance;
    private BasicDataSource basicDataSource;

    private DBConnectionPool() {

        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");     //this is the driver class for the mysql
        basicDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/homeauto");         //should be of the form jdbc:mysql://localhost:3306/<db name>
        basicDataSource.setUsername("root");                                //db username
        basicDataSource.setPassword("");                            //db password
//        basicDataSource = new BasicDataSource();
//        basicDataSource.setDriverClassName(properties.getProperty("driverClassName"));     //this is the driver class for the mysql
//        basicDataSource.setUrl(properties.getProperty("url"));         //should be of the form jdbc:mysql://localhost:3306/<db name>
//        basicDataSource.setUsername(properties.getProperty("userName"));                                //db username
//        basicDataSource.setPassword(properties.getProperty("password"));                            //db password
        basicDataSource.setMinIdle(2);
        basicDataSource.setMaxIdle(5);
        basicDataSource.setMaxTotal(10);
    }

    public static DBConnectionPool getInstance() {
        if(instance == null)
        {
            instance = new DBConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return this.basicDataSource.getConnection();
    }

    public void close(AutoCloseable closeable)
    {
        try
        {
            if(closeable != null)
            {
                closeable.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
