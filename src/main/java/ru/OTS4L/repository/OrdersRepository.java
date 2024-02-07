package ru.OTS4L.repository;

import ru.OTS4L.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findAllByOrderDate(Date orderDate);

    List<Orders> findAllByOrderDateAndTypeOfTruckOrderByManager(Date orderDate, String typeOfTruck);

    List<Orders> findAllByOrderDateBetween(Date date1, Date date2);


}
