package jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.*;

/**
 * Created by SDS on 09.10.2015.
 */
public class CreateTables {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        DatabaseConfiguration databaseConfiguration = (DatabaseConfiguration) context.getBean("databaseConfiguration");
        try {
            Class.forName(databaseConfiguration.getDatabaseDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = databaseConfiguration.getDatabaseUrl();
        String user=databaseConfiguration.getDatabaseUsername();
        String password=databaseConfiguration.getDatabasePassword();
        Connection con;
        Statement stmt;
        try {
            con = DriverManager.getConnection(url, user,password);
            DatabaseMetaData dbmd = con.getMetaData();
            System.out.println("\nConnected with " +
                    dbmd.getDriverName() + " " + dbmd.getDriverVersion() +
                    "{ " + dbmd.getDriverMajorVersion() + ",” " +
                    dbmd.getDriverMinorVersion() + " }" + " to " +
                    dbmd.getDatabaseProductName() + " " +
                    dbmd.getDatabaseProductVersion() + "\n");
            stmt = con.createStatement();
            String sql = "CREATE TABLE USERS " +
                    "(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    "firstname VARCHAR(255), " +
                    "lastname VARCHAR(255), " +
                    "age INTEGER, " +
                    "PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                // OK
            } else {
                try {
                    throw e;
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        try {
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String sqlOrd = "CREATE TABLE ORDERS " +
                    "(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    "Customerid integer, " +
                    "SalesPersonid integer, " +
                    "FOREIGN KEY(Customerid) REFERENCES USERS(id), " +
                    "FOREIGN KEY(SalesPersonid) REFERENCES USERS(id), " +
                    "PRIMARY KEY ( id ))";
            stmt.executeUpdate(sqlOrd);
            System.out.println("dd");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                // OK
            } else {
                try {
                    throw e;
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
