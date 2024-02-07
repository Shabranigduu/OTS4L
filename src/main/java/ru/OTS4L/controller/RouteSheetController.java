package ru.OTS4L.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.OTS4L.entity.RouteSheet;
import ru.OTS4L.dto.RouteSheetDTO;
import ru.OTS4L.service.RouteSheetService;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/routeSheet")
public class RouteSheetController {

    private final RouteSheetService routeSheetService;

    @Autowired
    public RouteSheetController(RouteSheetService routeSheetService) {
        this.routeSheetService = routeSheetService;
    }

    @GetMapping("/{date}")
    public String showAllRouteSheetsByDate(@PathVariable(name = "date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date, Model model) {
        List<RouteSheetDTO> routeSheetList = routeSheetService.getAllRouteSheetDTOByDate(date);
        model.addAttribute("routeSheetList", routeSheetList);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        model.addAttribute("date", format.format(date));

        return "routeSheet/list";
    }

    @GetMapping("/rate")
    public String showOrdersRate(@RequestParam("date")
                                 @DateTimeFormat(pattern = "dd.MM.yyyy") Date date,
                                 Model model){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        model.addAttribute("date", format.format(date));
        List<RouteSheet> routeSheetListLoners = routeSheetService.findAllByRouteDateAndTruckTypeWithoutCreate(date, "Одиночка");
        List<RouteSheet> routeSheetListTrains = routeSheetService.findAllByRouteDateAndTruckTypeWithoutCreate(date, "Электричка");
        model.addAttribute("routeSheetListLoners", routeSheetListLoners);
        model.addAttribute("routeSheetListTrains", routeSheetListTrains);

        return "routeSheet/rate";
    }
}
