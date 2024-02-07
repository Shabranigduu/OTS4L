package ru.OTS4L.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.OTS4L.entity.Drivers;
import ru.OTS4L.repository.DriversRepository;

import java.util.List;

@Service
public class DriversService {

    private final DriversRepository driversRepository;
    @Autowired
    public DriversService(DriversRepository driversRepository) {
        this.driversRepository = driversRepository;
    }

    public List<Drivers> findAll() {
        return driversRepository.findAll();
    }

    public void save(Drivers driver) {
        driversRepository.save(driver);
    }

    public void removeById(Integer id) {
        driversRepository.deleteById(id);
    }

    public Drivers findById(Integer id) {
        return driversRepository.findById(id).orElse(null);
    }
}
