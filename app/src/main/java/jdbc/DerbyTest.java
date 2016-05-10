package jdbc;

import jdbc.Entities.Order;
import jdbc.Entities.User;
import jdbc.Repositories.OrderRepository;
import jdbc.Repositories.OrderRepositoryImpl;
import jdbc.Repositories.UserRepository;
import jdbc.Repositories.UserRepositoryImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DerbyTest {
    public static void main(String[] args) {
//        try {
//            System.setOut(new PrintStream(System.out, true,"utf-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        String[] names = {"Полина", "Айрат", "Кристина", "Вероника", "Катя", "Анна", "Даша"};
        String[] lastnames = {"Дубровина", "Натфуллин", "Алчина", "Давлетшина", "Кутнякова", "Давлетшина", "Сычёва"};
        int[] ages = {18, 19, 19, 20, 18, 21, 18};
        int[] customerId = {3, 2, 1, 2, 5};
        int[] salesPersonId = {1, 3, 2, 6, 3};
        UserRepository u = new UserRepositoryImpl();
        User user;
        for (int i = 0; i < 7; i++) {
            user = new User();
            user.setFirstname(names[i]);
            user.setLastname(lastnames[i]);
            user.setAge(ages[i]);
            u.addUser(user);
        }
        int col = u.getAllUsers().size();
        System.out.println("Количество пользователей в БД: " + col);
        System.out.println("Cписок пользователей:");
        List list = u.getAllUsers();
        if (col > 10) {
            for (int i = col - 10; i < col; i++) {
                System.out.println(list.get(i));
            }
        } else {
            System.out.println(list);
        }
        System.out.println("Добавим двух пользователей...");
        User user1 = new User();
        user1.setFirstname("Настя");
        user1.setLastname("Соколова");
        user1.setAge(19);
        u.addUser(user1);
        User user2 = new User();
        user2.setFirstname("Вероника");
        user2.setLastname("Афанасьева");
        user2.setAge(19);
        u.addUser(user2);
        System.out.println("Cписок пользователей:");
        List list2 = u.getAllUsers();
        if (col > 10) {
            for (int i = col - 10; i < col; i++) {
                System.out.println(list2.get(i));
            }
        } else {
            System.out.println(list2);
        }
        System.out.println("Количество пользователей в БД: " + u.getAllUsers().size());
        User user3 = u.findUserByFirstname("Вероника").get(0);
        System.out.println("1-й пользователь по имени Вероника: " + user3);
//        System.out.println("Удалим этого пользователя...");
//        u.deleteUser(user3.getId());
        System.out.println("Изменим у пользователя с id=" + user1.getId() + " имя на Марусю и возраст=20...");
        User user4 = new User();
        user4.setFirstname("Маруся");
        user4.setLastname("Соколова");
        user4.setId(user1.getId());
        user4.setAge(20);
        u.updateUser(user4);
        System.out.println("Cписок пользователей:");
        List list3 = u.getAllUsers();
        int col2 = u.getAllUsers().size();
        if (col2 > 10) {
            for (int i = col2 - 10; i < col2; i++) {
                System.out.println(list3.get(i));
            }
        } else {
            System.out.println(list3);
        }
        System.out.println("Количество пользователей в БД: " + col2);
        OrderRepository us = new OrderRepositoryImpl();
        //зададим массивы из существующих id пользователей для заполнения полей таблицы ORDERS
        int m = 0;
        Random r = new Random();
        List arr = m();
        while (m < 5) {
            customerId[m] = (int) arr.get(r.nextInt(8));
            salesPersonId[m] = (int) arr.get(r.nextInt(8));
            m++;
        }
        Order order;
        for (int i = 0; i < 5; i++) {
            order = new Order();
            order.setCustomerid(customerId[i]);
            order.setSalesPersonid(salesPersonId[i]);
            us.addOrder(order);
        }
        int colOrd = us.getAllOrders().size();
        System.out.println("Количество заказов в БД: " + colOrd);
        System.out.println("Cписок заказов:");
        List listOrd = us.getAllOrders();
        if (colOrd > 10) {
            for (int i = colOrd - 10; i < colOrd; i++) {
                System.out.println(listOrd.get(i));
            }
        } else {
            System.out.println(listOrd);
        }
        System.out.println("Добавим два заказа...");
        Order order1 = new Order();
        order1.setCustomerid((int) arr.get(r.nextInt(8)));
        order1.setSalesPersonid((int) arr.get(r.nextInt(8)));
        us.addOrder(order1);
        Order order2 = new Order();
        order2.setCustomerid((int) arr.get(r.nextInt(8)));
        order2.setSalesPersonid((int) arr.get(r.nextInt(8)));
        us.addOrder(order2);
        System.out.println("Cписок заказов:");
        List listOrd2 = us.getAllOrders();
        if (colOrd > 10) {
            for (int i = colOrd - 10; i < colOrd; i++) {
                System.out.println(listOrd2.get(i));
            }
        } else {
            System.out.println(listOrd2);
        }
        System.out.println("Количество заказов в БД: " + us.getAllOrders().size());
        Order order3 = us.findOrderByCustomerID(order1.getCustomerid()).get(0);
        System.out.println("1-й заказ с покупателем с id=" + order1.getCustomerid() + ": " + order3);
        System.out.println("Удалим этот заказ...");
        us.deleteOrder(order3.getId());
        int id2 = (int) arr.get(r.nextInt(8));
        Order ord = us.getAllOrders().get(r.nextInt(6));
        System.out.println("Изменим у заказа с id=" + ord.getId() + " номер id продавца на " + id2 + "...");
        Order order4 = new Order();
        order4.setID(ord.getId());
        order4.setCustomerid(ord.getCustomerid());
        order4.setSalesPersonid(id2);
        us.updateOrder(order4);
        System.out.println("Cписок заказов:");
        List listOrd3 = us.getAllOrders();
        int colOrd2 = us.getAllOrders().size();
        if (colOrd2 > 10) {
            for (int i = colOrd2 - 10; i < us.getAllOrders().size(); i++) {
                System.out.println(listOrd3.get(i));
            }
        } else {
            System.out.println(listOrd3);
        }
        System.out.println("Количество заказов в БД: " + colOrd2);
//        resetTables();
    }

    //для очистки таблиц при повторном запуске
    private static void resetTables() {
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
        try {
            Connection coon = DriverManager.getConnection(url, user, password);
            Statement stmt = coon.createStatement();
            String sql1 = "DELETE FROM ORDERS";
            stmt.executeUpdate(sql1);
            String sql = "DELETE FROM USERS";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //метод для получения списка id пользователей
    private static List m() {
        List list = null;
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        DatabaseConfiguration databaseConfiguration = (DatabaseConfiguration) context.getBean("databaseConfiguration");
        String url = databaseConfiguration.getDatabaseUrl();
        try {
            Connection coon = DriverManager.getConnection(url);
            Statement stmt = coon.createStatement();
            String sql = "SELECT ID FROM USERS";
            list = new ArrayList<>();
            ResultSet results = stmt.executeQuery(sql);
            while (results.next()) {
                list.add(results.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
   /* public static void println(Object s){
        PrintStream stdout = null;
        try {
            stdout = new PrintStream(
                    System.out,true,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assert stdout != null;
        stdout.println(s);
    }*/
}