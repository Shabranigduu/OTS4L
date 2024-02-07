package ru.OTS4L.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.OTS4L.entity.Trucks;

import java.util.List;

public interface TrucksRepository extends JpaRepository<Trucks, Integer> {
    List<Trucks> findAllByTypeOrderById(String typeOfTruck);

}
