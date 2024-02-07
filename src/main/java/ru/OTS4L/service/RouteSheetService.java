package ru.OTS4L.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.OTS4L.entity.RouteSheet;
import ru.OTS4L.entity.RouteSheetList;
import ru.OTS4L.entity.Trips;
import ru.OTS4L.entity.Trucks;
import ru.OTS4L.mappers.RouteSheetMapper;
import ru.OTS4L.dto.RouteSheetDTO;
import ru.OTS4L.repository.DriversRepository;
import ru.OTS4L.repository.RouteSheetRepository;
import ru.OTS4L.repository.TripsRepository;
import ru.OTS4L.repository.TrucksRepository;
import ru.carier.pesokNada.entity.*;
import ru.carier.pesokNada.repository.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RouteSheetService {

    private final RouteSheetRepository routeSheetRepository;
    private final TrucksRepository trucksRepository;
    private final TripsRepository tripsRepository;
    private final DriversRepository driversRepository;
    ;


    @Autowired
    public RouteSheetService(RouteSheetRepository routeSheetRepository, TrucksRepository trucksRepository, TripsRepository tripsRepository, DriversRepository driversRepository) {
        this.routeSheetRepository = routeSheetRepository;
        this.trucksRepository = trucksRepository;
        this.tripsRepository = tripsRepository;
        this.driversRepository = driversRepository;
    }

    public List<RouteSheet> findAllByRouteDateAndTruckType(Date date, String typeOfTruck) {
        checkRouteSheetListByDate(date, typeOfTruck);
        List<RouteSheet> routeSheetList = routeSheetRepository.findAllByRouteDateAndTruckTypeOrderByTruckId(date, typeOfTruck);
//
        return routeSheetList;
    }

    public List<RouteSheet> findAllByRouteDateAndTruckTypeWithoutCreate(Date date, String typeOfTruck) {
        List<RouteSheet> routeSheetList = routeSheetRepository.findAllByRouteDateAndTruckTypeOrderByTruckId(date, typeOfTruck);

        return routeSheetList;
    }



    private void checkRouteSheetListByDate(Date date, String typeOfTruck) {
        List<Trucks> trucks = trucksRepository.findAllByTypeOrderById(typeOfTruck).stream()
                .filter(trucks1 -> !trucks1.getIsDeleted())
                .toList();

        for (Trucks truck : trucks) {
            Optional<RouteSheet> optTruck = routeSheetRepository.findByRouteDateAndTruck_Id(date, truck.getId());

            if (optTruck.isEmpty()) {
                RouteSheet routeSheet = new RouteSheet(truck, date);
                routeSheetRepository.saveAndFlush(routeSheet);
                if ((routeSheet.getTrips() == null) || (routeSheet.getTrips().isEmpty())) {
                    for (int j = 0; j < 5; j++) {
                        Trips trip = new Trips();
                        trip.setRouteSheet(routeSheet);
                        tripsRepository.save(trip);
                    }
                } else if (routeSheet.getTrips().size() < 5) {
                    for (int j = 0; j < 5 - routeSheet.getTrips().size(); j++) {
                        Trips trip = new Trips();
                        trip.setRouteSheet(routeSheet);
                        tripsRepository.save(trip);
                    }
                }
            }
        }
    }

    public void saveAllTrips(RouteSheetList routeSheetList) {
        List<RouteSheet> listRS = routeSheetList.getListOfLoner();
        listRS.addAll(routeSheetList.getListOfTrain());
        for (RouteSheet routeSheet : listRS) {
            routeSheet.getTrips()
                    .forEach(trip -> {
                        if (trip.getOrder().getId() == null) {
                            trip.setOrder(null);
                            routeSheetRepository.saveAndFlush(routeSheet);
                            tripsRepository.save(trip);
                            return;
                        }
                        trip.setTruck(routeSheet.getTruck());
                        trip.setDriver(routeSheet.getTruck().getDriver());
                        routeSheetRepository.saveAndFlush(routeSheet);
                        tripsRepository.save(trip);
                    });
            trucksRepository.getReferenceById(routeSheet.getTruck().getId()).setDriver(routeSheet.getTruck().getDriver());
        }
    }

    public List<RouteSheetDTO> getAllRouteSheetDTOByDate(Date date) {
        List<RouteSheet> routeSheetList = routeSheetRepository.findAllByRouteDate(date);
        return routeSheetList.stream()
                .map(RouteSheetMapper::map)
                .toList();
    }

    public void saveAll(RouteSheetList routeSheetList) {
        routeSheetRepository.saveAllAndFlush(routeSheetList.getListOfLoner());
        routeSheetRepository.saveAllAndFlush(routeSheetList.getListOfTrain());
    }
}
