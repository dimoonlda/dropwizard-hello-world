package ua.kiev.dimoon.dropwizard.hello.world.repositories;

import ua.kiev.dimoon.dropwizard.hello.world.entity.Order;

public interface OrderRepository {
    Order findById(Integer orderId);
}
