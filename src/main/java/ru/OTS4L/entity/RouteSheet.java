package ru.OTS4L.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "route_sheet")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteSheet {

    public RouteSheet(Trucks truck, Date routeDate) {
        this.truck = truck;
        this.routeDate = routeDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "truck_id", referencedColumnName = "id")
    private Trucks truck;

    @Column(name = "route_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date routeDate;


    @OneToMany(mappedBy = "routeSheet")
    @org.hibernate.annotations.OrderBy(clause = "id")
    private List<Trips> trips;

}