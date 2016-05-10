package jdbc.spring;

import jdbc.Entities.User;
import jdbc.Repositories.UserRepository;
import jdbc.Repositories.UserRepositoryImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.util.List;


/**
 * Created by SDS on 01.04.2016.
 */
public class AppSpringData {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        DataSource dataSource = (DataSource) context.getBean("dataSource");
        UserRepository u = new UserRepositoryImpl();

        String[] names = {"Полина", "Айрат", "Кристина", "Вероника", "Катя", "Анна", "Даша"};
        String[] lastnames = {"Дубровина", "Натфуллин", "Алчина", "Давлетшина", "Кутнякова", "Давлетшина", "Сычёва"};
        int[] ages = {18, 19, 19, 20, 18, 21, 18};
        int[] customerId = {3, 2, 1, 2, 5};
        int[] salesPersonId = {1, 3, 2, 6, 3};

        User user;
        for (int i = 0; i < names.length; i++) {
            user = new User();
            user.setFirstname(names[i]);
            user.setLastname(lastnames[i]);
            user.setAge(ages[i]);
            u.addUser(user);
        }

        int col = u.getAllUsers().size();
        System.out.println("Количество пользователей в БД: " + col);



        JdbcCorporateEventDao jdbcCorporateEventDao=new JdbcCorporateEventDao();
        System.out.println("Cписок пользователей:");
        jdbcCorporateEventDao.setDataSource(dataSource);
        //jdbcCorporateEventDao.insertOne();
        List<User> list = jdbcCorporateEventDao.findAllUsers();
        for (User user1:list) {
            System.out.println(user1);
        }


    }
}
