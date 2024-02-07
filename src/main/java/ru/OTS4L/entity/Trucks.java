package ru.OTS4L.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "trucks")
public class Trucks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "gos_number")
    private String gosNumber;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "truck")
    private List<Trips> trips;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private Drivers driver;

    public Trucks() {
    }

    public Integer getId() {
        return this.id;
    }

    public String getGosNumber() {
        return this.gosNumber;
    }

    public String getType() {
        return this.type;
    }

    public List<Trips> getTrips() {
        return this.trips;
    }

    public boolean getIsDeleted() {
        return this.isDeleted;
    }

    public Drivers getDriver() {
        return this.driver;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGosNumber(String gosNumber) {
        this.gosNumber = gosNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTrips(List<Trips> trips) {
        this.trips = trips;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setDriver(Drivers driver) {
        this.driver = driver;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Trucks)) return false;
        final Trucks other = (Trucks) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$gosNumber = this.getGosNumber();
        final Object other$gosNumber = other.getGosNumber();
        if (this$gosNumber == null ? other$gosNumber != null : !this$gosNumber.equals(other$gosNumber)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$trips = this.getTrips();
        final Object other$trips = other.getTrips();
        if (this$trips == null ? other$trips != null : !this$trips.equals(other$trips)) return false;
        if (this.getIsDeleted() != other.getIsDeleted()) return false;
        final Object this$driver = this.getDriver();
        final Object other$driver = other.getDriver();
        if (this$driver == null ? other$driver != null : !this$driver.equals(other$driver)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Trucks;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $gosNumber = this.getGosNumber();
        result = result * PRIME + ($gosNumber == null ? 43 : $gosNumber.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $trips = this.getTrips();
        result = result * PRIME + ($trips == null ? 43 : $trips.hashCode());
        result = result * PRIME + (this.getIsDeleted() ? 79 : 97);
        final Object $driver = this.getDriver();
        result = result * PRIME + ($driver == null ? 43 : $driver.hashCode());
        return result;
    }

    public String toString() {
        return "Trucks(id=" + this.getId() + ", gosNumber=" + this.getGosNumber() + ", type=" + this.getType() + ", trips=" + this.getTrips() + ", isDeleted=" + this.getIsDeleted() + ", driver=" + this.getDriver() + ")";
    }
}
