package ru.OTS4L.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.OTS4L.entity.Trips;
import ru.OTS4L.repository.OrdersRepository;
import ru.OTS4L.repository.TripsRepository;

import java.util.List;

@Service
public class TripsService {

    private final TripsRepository tripsRepository;
    private final OrdersRepository ordersRepository;
    @Autowired
    public TripsService(TripsRepository tripsRepository, OrdersRepository ordersRepository) {
        this.tripsRepository = tripsRepository;
        this.ordersRepository = ordersRepository;
    }

    public List<Trips> findAll() {
        return tripsRepository.findAll();
    }

}
