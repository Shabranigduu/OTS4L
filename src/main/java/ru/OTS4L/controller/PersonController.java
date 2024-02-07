package ru.OTS4L.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.OTS4L.entity.Person;
import ru.OTS4L.repository.PersonRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")

public class PersonController {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/users")
    public String showAllManager(Model model){
        List<Person> persons = personRepository.findAll();
        model.addAttribute("users", persons);

        return "admin/users";
    }

    @GetMapping("/add-user")
    public String addForm(@ModelAttribute(name = "user") Person user){

        return "admin/user-create";
    }

    @PostMapping("/add-user")
    public String add(@ModelAttribute(name = "user") Person user){
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       personRepository.save(user);

        return "redirect:/admin/users";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable(name = "id") Integer id, Model model){
        Person user = personRepository.getById(id);
        model.addAttribute("user", user);

        return "admin/user-update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(name = "user") Person user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        personRepository.save(user);

        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String remove(@PathVariable(name = "id") Integer id){
        personRepository.deleteById(id);

        return "redirect:/admin/users";
    }
}

