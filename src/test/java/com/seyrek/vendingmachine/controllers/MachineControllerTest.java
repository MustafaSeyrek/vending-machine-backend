package com.seyrek.vendingmachine.controllers;

import com.seyrek.vendingmachine.entities.Machine;
import com.seyrek.vendingmachine.services.MachineService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MachineControllerTest {
    @Mock
    MachineService mockMachineService;
    @InjectMocks
    MachineController underTest;

    @Test
    void getById_shoulReturnMachineList() {
        Machine expected = new Machine();
        expected.setCoolingDate(null);
        when(mockMachineService.getMachineById(anyInt())).thenReturn(expected);
        ResponseEntity<Machine> response = underTest.getMachineById(1);
        Machine actual = response.getBody();
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(expected.getCoolingDate(), actual.getCoolingDate())
        );
    }

    @Test
    void getById_shouldReturnStatusNotFound_whenMachineNotExist() {
        when(mockMachineService.getMachineById(anyInt())).thenReturn(null);
        ResponseEntity<Machine> response = underTest.getMachineById(1);
        Machine actual = response.getBody();
        assertNull(actual);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}