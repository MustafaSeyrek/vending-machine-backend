package com.seyrek.vendingmachine.controllers;

import com.seyrek.vendingmachine.entities.Machine;
import com.seyrek.vendingmachine.exceptions.MachineNotFoundException;
import com.seyrek.vendingmachine.exceptions.SellingNotSuccessfulException;
import com.seyrek.vendingmachine.requests.SellingRequest;
import com.seyrek.vendingmachine.responses.SellingResponse;
import com.seyrek.vendingmachine.services.MachineService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/machine")
@CrossOrigin("http://localhost:3000")
public class MachineController {
    private final MachineService machineService;

    @GetMapping("/{id}")
    public ResponseEntity<Machine> getMachineById(@PathVariable int id) {
        return new ResponseEntity<>(getMachine(id), OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMachineById(@PathVariable int id, @RequestBody Machine machine) {
        machineService.updateMachineById(id, machine);
        return new ResponseEntity<>(OK);
    }

    //manages the actions related to the selling process
    @PostMapping("/selling")
    public ResponseEntity<SellingResponse> sellingAction(@RequestBody SellingRequest request) {
        return new ResponseEntity<>(machineService.sellingAction(request), OK);
    }

    private Machine getMachine(int id) {
        return machineService.getMachineById(id);
    }

    @ExceptionHandler(MachineNotFoundException.class)
    public ResponseEntity<String> handleMachineNotFoundException(MachineNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(SellingNotSuccessfulException.class)
    public ResponseEntity<String> handleSellingNotSuccessfulException(SellingNotSuccessfulException ex) {
        return new ResponseEntity<>(ex.getMessage(), NOT_ACCEPTABLE);
    }
}
