package jdbc.Entities;

/**
 * Created by SDS on 09.10.2015.
 */
public class User {
    int id;
    String firstname;
    String lastname;
    int age;

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return  lastname + "||" + firstname + "||" + age;
    }
}
