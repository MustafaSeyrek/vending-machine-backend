package com.seyrek.vendingmachine.services;

import com.seyrek.vendingmachine.entities.Machine;
import com.seyrek.vendingmachine.exceptions.MachineNotFoundException;
import com.seyrek.vendingmachine.repositories.MachineRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MachineServiceTest {
    @Mock
    MachineRepository mockMachineRepository;
    @InjectMocks
    MachineService underTest;

    @Test
    void getById_shouldReturnMachine() {
        Machine machine = new Machine();
        machine.setCoolingDate(null);
        Machine response = new Machine();
        response.setCoolingDate(null);
        when(mockMachineRepository.findById(anyInt())).thenReturn(Optional.of(machine));
        Machine actual = underTest.getMachineById(1);
        assertEquals(response, actual);
    }

    @Test
    void geById_shouldThrowNotFound_whenMachineNotFound() {
        when(mockMachineRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(MachineNotFoundException.class, () -> underTest.getMachineById(1));
    }
}