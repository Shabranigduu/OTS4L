package ru.OTS4L.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "manager")

public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private boolean isDeleted;


    @OneToMany(mappedBy = "manager")
    private List<Orders> orders;


    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public boolean getIsDeleted() {
        return this.isDeleted;
    }

    public List<Orders> getOrders() {
        return this.orders;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Manager)) return false;
        final Manager other = (Manager) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$Name = this.getName();
        final Object other$Name = other.getName();
        if (this$Name == null ? other$Name != null : !this$Name.equals(other$Name)) return false;
        final Object this$phoneNumber = this.getPhoneNumber();
        final Object other$phoneNumber = other.getPhoneNumber();
        if (this$phoneNumber == null ? other$phoneNumber != null : !this$phoneNumber.equals(other$phoneNumber))
            return false;
        if (this.getIsDeleted() != other.getIsDeleted()) return false;
        final Object this$orders = this.getOrders();
        final Object other$orders = other.getOrders();
        if (this$orders == null ? other$orders != null : !this$orders.equals(other$orders)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Manager;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $Name = this.getName();
        result = result * PRIME + ($Name == null ? 43 : $Name.hashCode());
        final Object $phoneNumber = this.getPhoneNumber();
        result = result * PRIME + ($phoneNumber == null ? 43 : $phoneNumber.hashCode());
        result = result * PRIME + (this.getIsDeleted() ? 79 : 97);
        final Object $orders = this.getOrders();
        result = result * PRIME + ($orders == null ? 43 : $orders.hashCode());
        return result;
    }

    public String toString() {
        return "Manager(id=" + this.getId() + ", Name=" + this.getName() + ", phoneNumber=" + this.getPhoneNumber() + ", isDeleted=" + this.getIsDeleted() + ", orders=" + this.getOrders() + ")";
    }
}
