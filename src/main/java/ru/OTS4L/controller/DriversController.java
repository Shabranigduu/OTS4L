package ru.OTS4L.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.OTS4L.entity.Drivers;
import ru.OTS4L.service.DriversService;

import java.util.List;

@Controller
@RequestMapping("/drivers")
public class DriversController {

    private final DriversService driversService;

    @Autowired
    public DriversController(DriversService driversService) {
        this.driversService = driversService;
    }

    @GetMapping("/all")
    public String showAllDrivers(Model model){
        List<Drivers> drivers = driversService.findAll();
        model.addAttribute("drivers", drivers);

        return "drivers/drivers-list";
    }

    @GetMapping("/create")
    public String addForm(@ModelAttribute(name = "driver") Drivers driver){

        return "drivers/driver-create";
    }

    @PostMapping("/create")
    public String add(@ModelAttribute(name = "driver") Drivers driver){
        driversService.save(driver);

        return "redirect:/drivers/all";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable(name = "id") Integer id, Model model){
        Drivers driver = driversService.findById(id);
        model.addAttribute("driver", driver);

        return "drivers/driver-update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(name = "driver") Drivers driver){
        driversService.save(driver);

        return "redirect:/drivers/all";
    }

    @GetMapping("/delete/{id}")
    public String remove(@PathVariable(name = "id") Integer id){
        driversService.removeById(id);

        return "redirect:/drivers/all";
    }

}
