package ru.OTS4L.service;

import ru.OTS4L.entity.Orders;
import ru.OTS4L.mappers.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.OTS4L.repository.OrdersRepository;
import ru.OTS4L.repository.TripsRepository;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final TripsRepository tripsRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository, TripsRepository tripsRepository) {
        this.ordersRepository = ordersRepository;
        this.tripsRepository = tripsRepository;
    }

    public List<Orders> findOrdersByDate(Date date) {
        return addCommentForList(ordersRepository.findAllByOrderDate(date));
    }

    public List<Orders> findOrdersByDateInRange(Date date1, Date date2) {
        return addCommentForList(ordersRepository.findAllByOrderDateBetween(date1, date2));
    }

    public void saveOrder(Orders order) {
        ordersRepository.save(order);
    }

    public void checkCompleteness(List<Orders> orders) {
        orders.forEach(order -> {
            order.setCompleted(tripsRepository.findAllByOrderId(order.getId()).size() == order.getNumberOfTrips());
        });
    }


    public Orders findById(Integer id) {

        return ordersRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        ordersRepository.deleteById(id);
    }

    public List<Orders> findAllByOrderDateAndTypeOfTruck(Date orderDate, String typeOfTruck) {
        return ordersRepository.findAllByOrderDateAndTypeOfTruckOrderByManager(orderDate, typeOfTruck);
    }

    public List<Date> getDateRange(Date startDate, Date endDate) {
        List<Date> dateList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        while (calendar.getTime().before(endDate) || calendar.getTime().equals(endDate)) {
            dateList.add(calendar.getTime());
            calendar.add(Calendar.DATE, 1);
        }
        return dateList;
    }

    public List<String> getDateRangeString(Date startDate, Date endDate) {
        List<String> dateList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        while (calendar.getTime().before(endDate) || calendar.getTime().equals(endDate)) {
            dateList.add(format.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
        return dateList;
    }

    public List<Orders> searchOrders(String keyword) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        List<Orders> resultList = new ArrayList<>();
        List<Orders> orders = ordersRepository.findAllByOrderDateBetween(calendar.getTime(), new Date());
        orders.forEach(order -> {
            if (order.getCommentsOrder().toLowerCase().contains(keyword.toLowerCase())) {
                resultList.add(order);
            }
        });
        return addCommentForList(resultList);
    }

    private List<Orders> addCommentForList(List<Orders> orders) {
        orders.forEach(OrderMapper::map);
        return orders;
    }
}
