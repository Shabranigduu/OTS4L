package ru.OTS4L.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.OTS4L.entity.Drivers;
import ru.OTS4L.entity.Trucks;
import ru.OTS4L.entity.TrucksList;
import ru.OTS4L.service.DriversService;
import ru.OTS4L.service.TrucksService;

import java.util.List;

@Controller
@RequestMapping("/trucks")
public class TrucksController {

    private final TrucksService trucksService;
    private final DriversService driversService;

    @Autowired
    public TrucksController(TrucksService trucksService, DriversService driversService) {
        this.trucksService = trucksService;
        this.driversService = driversService;
    }

    @GetMapping("/all")
    public String showAllTrucks(Model model) {
        List<Trucks> trucks = trucksService.findAll();
        model.addAttribute("trucks", trucks);

        return "trucks/trucks-list";
    }

    @GetMapping("/create")
    public String addForm(@ModelAttribute(name = "truck") Trucks truck) {

        return "trucks/truck-create";
    }

    @PostMapping("/create")
    public String add(@ModelAttribute(name = "truck") Trucks truck) {
        truck.setDriver(driversService.findById(1));
        trucksService.save(truck);

        return "redirect:/trucks/all";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable(name = "id") Integer id, Model model) {
        Trucks truck = trucksService.findById(id);
        model.addAttribute("truck", truck);

        return "trucks/truck-update";
    }

    @PostMapping("/returnTruck")
    public String returnTruck(@ModelAttribute(name = "truck") Trucks truck) {
        trucksService.returnTruckById(truck.getId());

        return "redirect:/trucks/all";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(name = "truck") Trucks truck) {
        trucksService.save(truck);

        return "redirect:/trucks/all";
    }

    @GetMapping("/delete/{id}")
    public String remove(@PathVariable(name = "id") Integer id) {
        trucksService.removeById(id);

        return "redirect:/trucks/all";
    }

    @GetMapping("/setDrivers")
    public String showDrivers(Model model){
        List<Trucks> trucksLoner = trucksService.findAllByType("Одиночка");
        List<Trucks> trucksTrain = trucksService.findAllByType("Электричка");
        TrucksList trucks = new TrucksList(trucksLoner, trucksTrain);
        List<Drivers> drivers = driversService.findAll();
        model.addAttribute("trucks", trucks);
        model.addAttribute("drivers", drivers);

        return "trucks/set-drivers-on-trucks";
    }

    @PostMapping("/setDrivers")
    public String setDrivers(@ModelAttribute("trucks") TrucksList trucks){
        trucksService.saveAll(trucks.getListOfLoner());
        trucksService.saveAll(trucks.getListOfTrain());

        return "redirect:/trucks/setDrivers";
    }
}
