package ru.OTS4L.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.OTS4L.entity.Drivers;
import ru.OTS4L.entity.Orders;
import ru.OTS4L.entity.RouteSheet;
import ru.OTS4L.entity.RouteSheetList;
import ru.OTS4L.service.OrdersService;
import ru.OTS4L.service.RouteSheetService;
import ru.OTS4L.util.Utils;
import ru.carier.pesokNada.entity.*;
import ru.OTS4L.service.DriversService;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/trips")
public class TripsController {

    private final OrdersService ordersService;
    private final RouteSheetService routeSheetService;
    private final DriversService driversService;

    @Autowired
    public TripsController(OrdersService ordersService, RouteSheetService routeSheetService, DriversService driversService) {
        this.ordersService = ordersService;
        this.routeSheetService = routeSheetService;
        this.driversService = driversService;
    }

    @GetMapping("/create/{date}")
    public String createTrips(@PathVariable(name = "date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date, Model model) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        model.addAttribute("date", format.format(date));
        List<Orders> ordersLoner = ordersService.findAllByOrderDateAndTypeOfTruck(date, "Одиночка");
        List<Orders> ordersTrain = ordersService.findAllByOrderDateAndTypeOfTruck(date, "Электричка");
        ordersService.checkCompleteness(ordersLoner);
        ordersService.checkCompleteness(ordersTrain);
        model.addAttribute("ordersLoner", ordersLoner);
        model.addAttribute("ordersTrain", ordersTrain);
        List<RouteSheet> listRSLoner = routeSheetService.findAllByRouteDateAndTruckType(date, "Одиночка");
        List<RouteSheet> listRSTrain = routeSheetService.findAllByRouteDateAndTruckType(date, "Электричка");
        RouteSheetList routeSheetList = new RouteSheetList(listRSLoner, listRSTrain);
        model.addAttribute("routeSheetList", routeSheetList);
        List<Drivers> drivers = driversService.findAll();
        model.addAttribute("drivers", drivers);
        List<String> colors = Utils.getColorList();
        model.addAttribute("colors", colors);

        if (listRSLoner.get(0).getTrips() == null) {
            return "redirect:/trips/create/" + format.format(date);
        }

        return "trips/list";
    }

    @PostMapping("/create/{date}")
    public String saveRouteSheet(@PathVariable(name = "date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date,
                                 @ModelAttribute("routeSheetList") RouteSheetList routeSheetList) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyy");
        routeSheetService.saveAll(routeSheetList);
        routeSheetService.saveAllTrips(routeSheetList);

        return "redirect:/trips/create/" + format.format(date);
    }

    @GetMapping("/show")
    public String showTrips(@RequestParam("date")
                            @DateTimeFormat(pattern = "dd.MM.yyyy") Date date,
                            Model model) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        model.addAttribute("date", format.format(date));
        List<Orders> ordersLoner = ordersService.findAllByOrderDateAndTypeOfTruck(date, "Одиночка");
        List<Orders> ordersTrain = ordersService.findAllByOrderDateAndTypeOfTruck(date, "Электричка");
        ordersService.checkCompleteness(ordersLoner);
        ordersService.checkCompleteness(ordersTrain);
        model.addAttribute("ordersLoner", ordersLoner);
        model.addAttribute("ordersTrain", ordersTrain);
        List<RouteSheet> listRSLoner = routeSheetService.findAllByRouteDateAndTruckTypeWithoutCreate(date, "Одиночка");
        List<RouteSheet> listRSTrain = routeSheetService.findAllByRouteDateAndTruckTypeWithoutCreate(date, "Электричка");
        RouteSheetList routeSheetList = new RouteSheetList(listRSLoner, listRSTrain);
        model.addAttribute("routeSheetList", routeSheetList);
        List<String> colors = Utils.getColorList();
        model.addAttribute("colors", colors);

        return "trips/saved-trips";
    }
}

