package ru.OTS4L.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.OTS4L.entity.Manager;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    @Query("SELECT e FROM Manager e WHERE e.isDeleted = false")
    List<Manager> findAllNotDeleted();
}
