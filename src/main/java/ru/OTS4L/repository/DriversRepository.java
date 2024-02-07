package ru.OTS4L.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.OTS4L.entity.Drivers;

public interface DriversRepository extends JpaRepository<Drivers, Integer> {
}
