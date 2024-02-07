package ru.OTS4L.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date orderDate;  // дата заказа

    @Column(name = "loading_location")
    private String loadingLocation;  // погрузка

    @Column(name = "uploading_location")
    private String uploadingLocation; // выгрузка

    @Column(name = "cargo_volume")
    private Integer cargoVolume;  // общий объем

    @Column(name = "loading")
    private Integer loading; // грузим

    @Column(name = "unloading")
    private Integer unloading;  // сдаём

    @Column(name = "number_of_trips")
    private Integer numberOfTrips;  // число рейсов

    @Column(name = "type_of_truck")
    private String typeOfTruck;  // тип машины

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;  // менеджер

    @Column(name = "comments_order")
    private String commentsOrder;  // комментарий

    @Column(name = "short_comment")
    private String shortComment;  // доп. информация

    @Column(name = "rate_1c")
    private Integer rate1c;  // ставка 1С

    @Column(name = "material_rate")
    private Integer materialRate;  // ставка материала

    @Column(name = "freight_rate")
    private Integer freightRate;  // ставка перевозки

    @Column(name = "contractor")
    private String contractor;  // контрагент

    @Column(name = "material")
    private String material;  // материал

    @Column(name = "shipper")
    private String shipper;  // грузоотправитель

    @Column(name = "consignee")
    private String consignee;  // грузополусатель

    @Column(name = "receiver_contact")
    private String receiverСontact;  // контакт приёмщика

    @Column(name = "loading_organization")
    private String loadingOrganization;  // отгружающая организцация ("грузим от")

    @OneToMany(mappedBy = "order")
    private List<Trips> trips;  // списко рейсов

    @Column(name = "uploading_adress")
    private String uploadingAddress;  // точный адрес выгрузки

    @Transient
    private boolean isCompleted;  // назначенны ли все рейсы по заказу

    @Transient
    private String commentForOrdersList;  // комментарий для страницы со списком заказа

    public Orders() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Orders;
    }

}
