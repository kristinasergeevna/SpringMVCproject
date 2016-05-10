package jdbc.Entities;

/**
 * Created by SDS on 09.10.2015.
 */
public class Order {
    int id;
    int customerid;
    int salesPersonid;

    public void setID(int id) {
        this.id = id;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public void setSalesPersonid(int salesPersonid) {
        this.salesPersonid = salesPersonid;
    }

    public int getId() {
        return id;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public Integer getSalesPersonid() {
        return salesPersonid;
    }

    public String toString() {
        return id + "||" + customerid + "||" + salesPersonid;
    }
}
