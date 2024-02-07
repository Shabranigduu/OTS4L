package ru.OTS4L.mappers;

import ru.OTS4L.dto.RouteSheetDTO;
import ru.OTS4L.entity.RouteSheet;
import ru.OTS4L.entity.Trips;

import java.util.*;

public class RouteSheetMapper {
    public static RouteSheetDTO map(RouteSheet from) {
        RouteSheetDTO toObject = new RouteSheetDTO();
        Map<Integer, String> mapOrdersComments = new HashMap<>();
        boolean isEmpty = true;
        for (Trips trip : from.getTrips()) {
            if (trip.getOrder() != null) {
                mapOrdersComments.put(trip.getOrder().getId(),
                        "Погрузка: " + trip.getOrder().getLoadingLocation()
                                + "\nВыгрузка: " + trip.getOrder().getUploadingLocation()
                                + "\n\n" + "Менеджер: " + trip.getOrder().getManager().getName()
                                + "\n" + "тел.: " + trip.getOrder().getManager().getPhoneNumber()
                                + "\n\n" + trip.getOrder().getMaterial()
                                + "\n" + "Грузим от: " + trip.getOrder().getLoadingOrganization()
                                + "\n\n" + "Грузим: " + trip.getOrder().getLoading()
                                + "\n" + "Сдаём: " + trip.getOrder().getUnloading()
                                + "\n\n" + "ГО: " + trip.getOrder().getShipper()
                                + "\n" + "ГП: " + trip.getOrder().getConsignee()
                                + "\n\n" + "Выгрузка: " + trip.getOrder().getUploadingAddress()
                                + "\n"  + trip.getOrder().getContractor()
                                + "\n\n" + trip.getOrder().getCommentsOrder());
                isEmpty = false;
            }
        }
        List<OneTrip> runCount = new ArrayList<>();
        for (int i = 0; i < from.getTrips().size(); i++) {
            if (from.getTrips().get(i).getOrder() != null) {
                if (i != 0 && Objects.equals(from.getTrips().get(i).getOrder().getId(), from.getTrips().get(i - 1).getOrder().getId())) {
                    runCount.get(runCount.size() - 1).routeCountNumber++;
                } else {
                    runCount.add(new OneTrip(from.getTrips().get(i).getOrder().getId()));
                }
            }
        }
        StringBuilder roadMap = new StringBuilder();
        roadMap.append(from.getTruck().getGosNumber())
                .append("\n")
                .append(from.getTruck().getDriver().getName())
                .append("\n")
                .append(from.getTruck().getDriver().getPhoneNumber())
                .append("\n");
        int tripCount = 1;
        for (int i = 0; i < runCount.size(); i++) {
            roadMap.append("_________________________")
                    .append("\n\n")
                    .append("Рейс №").append(tripCount)
                    .append("\n")
                    .append("Кол-во ходок: ").append(runCount.get(i).routeCountNumber)
                    .append("\n")
                    .append(mapOrdersComments.get(runCount.get(i).orderId))
                    .append("\n");
            tripCount += runCount.get(i).routeCountNumber;
        }
        toObject.setRouteSheet(roadMap.toString());
        toObject.setTruck(from.getTruck().getGosNumber());
        toObject.setTypeOfTruck(from.getTruck().getType());
        toObject.setDriverName(from.getTruck().getDriver().getName());
        toObject.setDriverPhoneNumber(from.getTruck().getDriver().getPhoneNumber());
        toObject.setEmpty(isEmpty);

        return toObject;
    }

    private static class OneTrip {
        private Integer routeCountNumber = 1;
        private final Integer orderId;


        public OneTrip(Integer orderId) {
            this.orderId = orderId;

        }
    }
}
