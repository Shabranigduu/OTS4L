package ru.OTS4L.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.OTS4L.entity.Manager;
import ru.OTS4L.repository.ManagerRepository;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ManagerServiceTest {
    @InjectMocks
    private ManagerService managerService;
    @Mock
    private ManagerRepository managerRepository;

    @Test
    void removeById_managerExist() {
        Manager manager = new Manager(1, "name", "89112223344", false, Collections.emptyList());
        Mockito.when(managerRepository.findById(1)).thenReturn(Optional.of(manager));
        managerService.removeById(1);
        Assertions.assertTrue(manager.isDeleted());
        Assertions.assertEquals(manager.getName(), "name (УДАЛЕН)");
        Mockito.verify(managerRepository, Mockito.times(1)).findById(1);
    }

    @Test
    void removeById_managerNotExist() {
        Mockito.when(managerRepository.findById(1)).thenReturn(Optional.empty());
        managerService.removeById(1);
        Mockito.verify(managerRepository, Mockito.never()).save(Mockito.any(Manager.class));
    }
}