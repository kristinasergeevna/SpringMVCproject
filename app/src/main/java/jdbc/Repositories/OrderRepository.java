package jdbc.Repositories;


import jdbc.Entities.Order;

import java.util.List;

/**
 * Created by SDS on 09.10.2015.
 */
public interface OrderRepository {


    List<Order> getAllOrders();

    void addOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(int id);

    List<Order> findOrderByCustomerID(int customerID);
}
