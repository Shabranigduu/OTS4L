package ru.OTS4L.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.OTS4L.entity.Manager;
import ru.OTS4L.entity.Orders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.OTS4L.service.ManagerService;
import ru.OTS4L.service.OrdersService;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;
    private final ManagerService managerService;

    @Autowired
    public OrdersController(OrdersService ordersService, ManagerService managerService) {
        this.ordersService = ordersService;
        this.managerService = managerService;
    }


    @GetMapping("/range")
    public String findAllByOrderDateInRange(@RequestParam("startDate")
                                            @DateTimeFormat(pattern = "dd.MM.yyyy") Date startDate,
                                            @RequestParam("endDate")
                                            @DateTimeFormat(pattern = "dd.MM.yyyy") Date endDate,
                                            Model model) {
        List<Orders> orders = ordersService.findOrdersByDateInRange(startDate, endDate);
        model.addAttribute("orders", orders);
        List<Date> dateRange = ordersService.getDateRange(startDate, endDate);
        model.addAttribute("dateRange", dateRange);
        List<String> dateRangeString = ordersService.getDateRangeString(startDate, endDate);
        model.addAttribute("dateRangeString", dateRangeString);
        List<Manager> managers = orders.stream()
                .map(Orders::getManager)
                .distinct()
                .toList();
        model.addAttribute("managers", managers);

        return "orders/orders-range";
    }

    @GetMapping("/create")
    public String createOrderForm(@ModelAttribute("order") Orders order, Model model) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        order.setOrderDate(tomorrow);
        List<Manager> managers = managerService.getAllWorkedManagers();
        model.addAttribute("managers", managers);

        return "orders/order-create";
    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute("order") Orders order) {
        ordersService.saveOrder(order);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        return "redirect:/orders/date?date=" + format.format(order.getOrderDate());
    }

    @GetMapping("/update/{id}")
    public String updateOrder(Model model, @PathVariable("id") Integer id) {
        Orders order = ordersService.findById(id);
        model.addAttribute("order", order);
        List<Manager> managers = managerService.getAllWorkedManagers();
        model.addAttribute("managers", managers);

        return "orders/order-update";
    }

    @PostMapping("/update")
    public String updateOrder(@ModelAttribute("order") Orders order) {
        ordersService.saveOrder(order);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        return "redirect:/orders/date?date=" + format.format(order.getOrderDate());
    }

    @GetMapping("/copy/{id}")
    public String copyOfTheOrder(Model model, @PathVariable("id") Integer id) {
        Orders order = ordersService.findById(id);
        model.addAttribute("order", order);
        List<Manager> managers = managerService.getAllWorkedManagers();
        model.addAttribute("managers", managers);

        return "orders/order-copy";
    }

    @GetMapping("delete/{id}")
    public String deleteOrder(@PathVariable("id") Integer id) {
        ordersService.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("")
    public String findTodayOrders(Model model) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 0);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        List<Orders> orders = ordersService.findOrdersByDate(today);
        List<Manager> managers = orders.stream()
                .map(Orders::getManager)
                .distinct()
                .toList();
        model.addAttribute("managers", managers);
        model.addAttribute("orders", orders);
        model.addAttribute("date", format.format(today));

        return "orders/orders";
    }

    @GetMapping("/date")
    public String findOrdersByDate(@RequestParam("date")
                                   @DateTimeFormat(pattern = "dd.MM.yyyy") Date date,
                                   Model model) {
        List<Orders> orders = ordersService.findOrdersByDate(date);
        List<Manager> managers = orders.stream()
                .map(Orders::getManager)
                .distinct()
                .toList();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        model.addAttribute("managers", managers);
        model.addAttribute("orders", orders);
        model.addAttribute("date", format.format(date));

        return "orders/orders";
    }

    @GetMapping("/tomorrow")
    public String findTomorrowOrders(Model model) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        List<Orders> orders = ordersService.findOrdersByDate(tomorrow);
        List<Manager> managers = orders.stream()
                .map(Orders::getManager)
                .distinct()
                .toList();
        model.addAttribute("managers", managers);
        model.addAttribute("orders", orders);
        model.addAttribute("date", format.format(tomorrow));

        return "orders/orders";
    }

    @GetMapping("/search_orders")
    public String findOrdersByKeyword(@RequestParam("keyword") String keyword, Model model) {
        List<Orders> orders = ordersService.searchOrders(keyword);
        model.addAttribute("orders", orders);

        return "orders/searching-orders";
    }

    @GetMapping("/rate")
    public String showOrdersRate(@RequestParam("date")
                                 @DateTimeFormat(pattern = "dd.MM.yyyy") Date date,
                                 Model model){
        List<Orders> orders = ordersService.findOrdersByDate(date);
        List<Manager> managers = orders.stream()
                .map(Orders::getManager)
                .distinct()
                .toList();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        model.addAttribute("managers", managers);
        model.addAttribute("orders", orders);
        model.addAttribute("date", format.format(date));

        return "orders/rate";
    }
}
