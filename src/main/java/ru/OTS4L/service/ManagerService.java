package ru.OTS4L.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.OTS4L.entity.Manager;
import ru.OTS4L.repository.ManagerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;
    @Autowired
    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public List<Manager> getAllManagers(){
        return managerRepository.findAll();
    }
    public List<Manager> getAllWorkedManagers(){
        return managerRepository.findAllNotDeleted();
    }


    public void save(Manager manager) {
        managerRepository.save(manager);
    }

    public Manager findById(Integer id) {
        return managerRepository.findById(id).orElseThrow();
    }

    public void removeById(Integer id) {
        Optional<Manager> opt = managerRepository.findById(id);
        if(opt.isPresent()){
            Manager manager = opt.get();
            manager.setIsDeleted(true);
            manager.setName(manager.getName()+" (УДАЛЕН)");
            managerRepository.save(manager);
        }
    }
}
