package jdbc.Repositories;


import jdbc.DatabaseConfiguration;
import jdbc.Entities.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<User>();
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        DatabaseConfiguration databaseConfiguration = (DatabaseConfiguration) context.getBean("databaseConfiguration");
        String url = databaseConfiguration.getDatabaseUrl();
        try {
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM USERS ORDER BY id";
            ResultSet results = stmt.executeQuery(sql);
            while (results.next()) {
                User user = new User();
                user.setId(results.getInt(1));
                user.setFirstname(results.getString(2));
                user.setLastname(results.getString(3));
                user.setAge(results.getInt(4));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addUser(User user) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        DatabaseConfiguration databaseConfiguration = (DatabaseConfiguration) context.getBean("databaseConfiguration");
        String url = databaseConfiguration.getDatabaseUrl();
        try {
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO USERS (firstname, lastname, age) VALUES('" + user.getFirstname() + "','" + user.getLastname() + "'," + user.getAge() + ")";
            stmt.executeUpdate(sql);
            String sql2 = "SELECT ID FROM USERS WHERE firstname='" + user.getFirstname() + "' AND lastname='" + user.getLastname() + "'";
            ResultSet resultSet = stmt.executeQuery(sql2);
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        DatabaseConfiguration databaseConfiguration = (DatabaseConfiguration) context.getBean("databaseConfiguration");
        String url = databaseConfiguration.getDatabaseUrl();
        try {
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            String sql = "UPDATE USERS SET lastname='" + user.getLastname() + "',firstname='" + user.getFirstname() + "',age=" + user.getAge() + " WHERE id=" + user.getId();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        DatabaseConfiguration databaseConfiguration = (DatabaseConfiguration) context.getBean("databaseConfiguration");
        String url = databaseConfiguration.getDatabaseUrl();
        try {
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            String sql = "DELETE FROM USERS WHERE id=" + id;
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findUserByFirstname(String firstname) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        DatabaseConfiguration databaseConfiguration = (DatabaseConfiguration) context.getBean("databaseConfiguration");
        String url = databaseConfiguration.getDatabaseUrl();
        List<User> list = new ArrayList<User>();
        try {
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM USERS WHERE firstname='" + firstname + "'";
            ResultSet results2 = stmt.executeQuery(sql);
            while (results2.next()) {
                User user = new User();
                user.setId(results2.getInt(1));
                user.setFirstname(results2.getString(2));
                user.setLastname(results2.getString(3));
                user.setAge(results2.getInt(4));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
