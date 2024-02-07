package ru.OTS4L.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.util.List;

@Data
@Entity
@Table(name = "drivers")
public class Drivers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ReadOnlyProperty
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "driver")
    private List<Trips> trips;

    @OneToMany(mappedBy = "driver")
    private List<Trucks> trucks;
}
