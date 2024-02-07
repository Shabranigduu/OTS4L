package ru.OTS4L.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.OTS4L.entity.Trips;

import java.util.List;

public interface TripsRepository extends JpaRepository<Trips, Integer> {
    List<Trips> findAllByRouteSheetId(Integer id);

    List<Trips> findAllByOrderId(Integer id);
}
