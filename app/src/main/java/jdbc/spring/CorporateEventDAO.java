package jdbc.spring;

import jdbc.Entities.User;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by SDS on 01.04.2016.
 */
interface CorporateEventDAO {
    void setDataSource(DataSource dataSource);
    List<User> findAllUsers();
}
