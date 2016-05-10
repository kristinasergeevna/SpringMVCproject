package jdbc.spring;

import jdbc.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by SDS on 01.04.2016.
 */
public class JdbcCorporateEventDao implements CorporateEventDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public List<User> findAllUsers() {
        List<User> users=this.jdbcTemplate.query("SELECT * FROM USERS",BeanPropertyRowMapper.newInstance(User.class));
        return users;
    }

    public void insertOne() {
        this.jdbcTemplate.update("INSERT INTO USERS (firstname, lastname, age)  VALUES('Ayrat', 'N', 32)");
    }

    private static final class UserMapper implements RowMapper<User>{

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user=new User();
            user.setFirstname(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));
            user.setAge(resultSet.getInt("age"));
            return user;
        }
    }
}
