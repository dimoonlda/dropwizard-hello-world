package ua.kiev.dimoon.dropwizard.hello.world.repositories;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.kiev.dimoon.dropwizard.hello.world.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class OrderRepositoryImpl extends BaseRepositoryImpl implements OrderRepository {

    public static final class OrderRowMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Order()
                    .setOrderId(resultSet.getInt("orderid"))
                    .setShipAddress(resultSet.getString("shipaddress"))
                    .setShipName(resultSet.getString("shipname"))
                    .setOrderDate(resultSet.getDate("orderdate").toLocalDate());
        }
    }

    @Override
    public Order findById(Integer orderId) {
        Order order = jdbcTemplate.queryForObject("select * from orders where orderid=?", new Object[]{orderId}, new OrderRowMapper());
        return order;
    }
}
