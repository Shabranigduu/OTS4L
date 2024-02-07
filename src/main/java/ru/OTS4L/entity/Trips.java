package ru.OTS4L.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "trips")
@NoArgsConstructor
@AllArgsConstructor
public class Trips {

    public Trips(Orders order) {
        this.order = order;
    }
    public Trips(RouteSheet routeSheet) {
        this.routeSheet = routeSheet;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "truck_id", referencedColumnName = "id")
    private Trucks truck;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private Drivers driver;

    @ManyToOne

    @JoinColumn(name = "route_sheet_id")
    private RouteSheet routeSheet;

}
