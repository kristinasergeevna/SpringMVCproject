package jdbc.Repositories;

import jdbc.DatabaseConfiguration;
import jdbc.Entities.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<Order>();
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        DatabaseConfiguration databaseConfiguration = (DatabaseConfiguration) context.getBean("databaseConfiguration");
        String url = databaseConfiguration.getDatabaseUrl();
        try {
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM ORDERS ORDER BY id";
            ResultSet results = stmt.executeQuery(sql);
            while (results.next()) {
                Order order = new Order();
                order.setID(results.getInt(1));
                order.setCustomerid(results.getInt(2));
                order.setSalesPersonid(results.getInt(3));
                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addOrder(Order order) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        DatabaseConfiguration databaseConfiguration = (DatabaseConfiguration) context.getBean("databaseConfiguration");
        String url = databaseConfiguration.getDatabaseUrl();
        try {
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO ORDERS (Customerid, SalesPersonid) VALUES(" + order.getCustomerid() + "," + order.getSalesPersonid() + ")";
            stmt.executeUpdate(sql);
            String sql2 = "SELECT ID FROM ORDERS WHERE Customerid=" + order.getCustomerid() + " AND SalesPersonid=" + order.getSalesPersonid();
            ResultSet resultSet = stmt.executeQuery(sql2);
            while (resultSet.next()) {
                order.setID(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrder(Order order) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        DatabaseConfiguration databaseConfiguration = (DatabaseConfiguration) context.getBean("databaseConfiguration");
        String url = databaseConfiguration.getDatabaseUrl();
        try {
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            String sql = "UPDATE ORDERS SET Customerid=" + order.getCustomerid() + ", SalesPersonid=" + order.getSalesPersonid() + " WHERE id=" + order.getId();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteOrder(int id) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        DatabaseConfiguration databaseConfiguration = (DatabaseConfiguration) context.getBean("databaseConfiguration");
        String url = databaseConfiguration.getDatabaseUrl();
        try {
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            String sql = "DELETE FROM ORDERS WHERE id=" + id;
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Order> findOrderByCustomerID(int customerID) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        DatabaseConfiguration databaseConfiguration = (DatabaseConfiguration) context.getBean("databaseConfiguration");
        String url = databaseConfiguration.getDatabaseUrl();
        List<Order> list = new ArrayList<Order>();
        try {
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM ORDERS WHERE Customerid=" + customerID + "";
            ResultSet results2 = stmt.executeQuery(sql);
            while (results2.next()) {
                Order order = new Order();
                order.setID(results2.getInt(1));
                order.setCustomerid(results2.getInt(2));
                order.setSalesPersonid(results2.getInt(3));
                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
