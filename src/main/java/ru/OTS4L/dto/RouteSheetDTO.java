package ru.OTS4L.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class RouteSheetDTO {

    private String truck;
    private String typeOfTruck;
    private String driverName;
    private String driverPhoneNumber;
    private String routeSheet;
    private boolean isEmpty;
}
