package ru.OTS4L.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteSheetList {

    private List<RouteSheet> listOfLoner;
    private List<RouteSheet> listOfTrain;

}

