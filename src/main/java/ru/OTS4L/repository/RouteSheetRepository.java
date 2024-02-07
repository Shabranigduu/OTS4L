package ru.OTS4L.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.OTS4L.entity.RouteSheet;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RouteSheetRepository extends JpaRepository<RouteSheet, Integer> {

    List<RouteSheet> findAllByRouteDateAndTruckTypeOrderByTruckId(Date date, String typeOfTruck);

    List<RouteSheet> findAllByRouteDate(Date date);

    Optional<RouteSheet> findByRouteDateAndTruck_Id(Date date, Integer id);
}
