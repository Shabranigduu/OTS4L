package ru.OTS4L.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.OTS4L.entity.Trucks;
import ru.OTS4L.repository.TrucksRepository;


import java.util.List;
import java.util.Optional;

@Service
public class TrucksService {

    private final TrucksRepository trucksRepository;

    @Autowired
    public TrucksService(TrucksRepository trucksRepository) {
        this.trucksRepository = trucksRepository;
    }


    public List<Trucks> findAll() {
        return trucksRepository.findAll();
    }

    public void save(Trucks truck) {
        trucksRepository.save(truck);
    }

    public void removeById(Integer id) {

        Optional<Trucks> opt = trucksRepository.findById(id);
        if (opt.isPresent()) {
            Trucks truck = opt.get();
            truck.setIsDeleted(true);
            truck.setGosNumber(truck.getGosNumber() + " УДАЛЕН");
            trucksRepository.save(truck);
        }
    }

    public void returnTruckById(Integer id){
        Optional<Trucks> opt = trucksRepository.findById(id);
        if (opt.isPresent()) {
            Trucks truck = opt.get();
            truck.setIsDeleted(false);
            trucksRepository.save(truck);
        }
    }

    public Trucks findById(Integer id) {
        return trucksRepository.findById(id).orElse(null);
    }

    public List<Trucks> findAllByType(String typeOfTruck) {
        return trucksRepository.findAllByTypeOrderById(typeOfTruck);
    }

    public void saveAll(List<Trucks> trucks) {
        trucksRepository.saveAll(trucks);
    }
}
