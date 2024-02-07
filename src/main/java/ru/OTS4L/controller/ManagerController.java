package ru.OTS4L.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.OTS4L.entity.Manager;
import ru.OTS4L.service.ManagerService;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    
    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/all")
    public String showAllManager(Model model){
        List<Manager> manager = managerService.getAllManagers();
        model.addAttribute("manager", manager);

        return "manager/manager-list";
    }

    @GetMapping("/create")
    public String addForm(@ModelAttribute(name = "manager") Manager manager){

        return "manager/manager-create";
    }

    @PostMapping("/create")
    public String add(@ModelAttribute(name = "manager") Manager manager){
        managerService.save(manager);

        return "redirect:/manager/all";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable(name = "id") Integer id, Model model){
        Manager manager = managerService.findById(id);
        model.addAttribute("manager", manager);

        return "manager/manager-update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(name = "manager") Manager manager){
        managerService.save(manager);

        return "redirect:/manager/all";
    }

    @GetMapping("/delete/{id}")
    public String remove(@PathVariable(name = "id") Integer id){
        managerService.removeById(id);

        return "redirect:/manager/all";
    }
}
