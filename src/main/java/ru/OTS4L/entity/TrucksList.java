package ru.OTS4L.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrucksList {

    private List<Trucks> listOfLoner;

    private List<Trucks> listOfTrain;
}
