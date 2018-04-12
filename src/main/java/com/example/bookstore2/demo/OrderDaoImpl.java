package com.example.bookstore2.demo;

import com.example.bookstore2.demo.Entity.Order;
import com.example.bookstore2.demo.Entity.OrderDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mr Tu on 28-05-2017.
 */
public class OrderDaoImpl implements OrderDao {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void create(final Order order, final List<OrderDetail> orderDetails) {
        try {
            getSession().beginTransaction();
            getSession().save(order);
            getSession().doWork(new Work() {
                public void execute(Connection connection) throws SQLException {
                    PreparedStatement preparedStatement =
                            connection.prepareStatement("INSERT INTO order_detail(id_book,id_order," +
                                    "quantity,price,status)VALUES(?,?,?,?,?)");
                    for (OrderDetail orderDetail : orderDetails) {
                        preparedStatement.setInt(1, orderDetail.getBookid());
                        preparedStatement.setInt(2, order.getId());
                        preparedStatement.setInt(3, orderDetail.getQuantity());
                        preparedStatement.setBigDecimal(4, orderDetail.getPrice());
                        preparedStatement.setInt(5, 1);
                        preparedStatement.executeUpdate();
                    }
                }
            });

            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
    }

}
