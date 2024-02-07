package ru.OTS4L.mappers;

import ru.OTS4L.entity.Orders;

public class OrderMapper {
    public static void map(Orders order) {
        StringBuilder comment = new StringBuilder();
        comment.append("Погрузка ").append(order.getLoadingLocation()).append("\n")
                .append("Материал ").append(order.getMaterial()).append("\n")
                .append("Грузим от ").append(order.getLoadingOrganization()).append("\n").append("\n")
                .append("Грузим ").append(order.getLoading()).append("\n")
                .append("Сдаём ").append(order.getUnloading()).append("\n").append("\n")
                .append("ГО ").append(order.getShipper()).append("\n")
                .append("ГП ").append(order.getConsignee()).append("\n").append("\n")
                .append("Адрес: ").append(order.getUploadingAddress()).append("\n")
                .append("к.т. приёмщика: ").append(order.getReceiverСontact()).append("\n").append("\n")
                .append("Ставка перевозки: ").append(order.getFreightRate()).append("\n")
                .append("Ставка материала: ").append(order.getMaterialRate()).append("\n")
                .append("Ставка 1С: ").append(order.getRate1c()).append("\n").append("\n")
                .append(order.getCommentsOrder());
        order.setCommentForOrdersList(comment.toString());
    }
}
