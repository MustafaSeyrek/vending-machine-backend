package com.seyrek.vendingmachine.services;

import com.seyrek.vendingmachine.entities.Machine;
import com.seyrek.vendingmachine.entities.Product;
import com.seyrek.vendingmachine.exceptions.MachineNotFoundException;
import com.seyrek.vendingmachine.exceptions.SellingNotSuccessfulException;
import com.seyrek.vendingmachine.repositories.MachineRepository;
import com.seyrek.vendingmachine.requests.SellingRequest;
import com.seyrek.vendingmachine.responses.SellingResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MachineService {
    private final MachineRepository machineRepository;
    private final ProductService productService;

    public Machine getMachineById(int id) {
        return machineRepository.findById(id)
                .orElseThrow(() -> new MachineNotFoundException("Machine not found id: " + id));
    }

    public void updateMachineById(int id, Machine machine) {
        Machine oldMachine = getMachineById(id);
        oldMachine.setTotalPrice((machine.getTotalPrice()));
        oldMachine.setCoolingDate(machine.getCoolingDate());
        machineRepository.save(oldMachine);
    }

    public SellingResponse sellingAction(SellingRequest request) {
        Machine machine = getMachineById(request.getMachineId());
        Product product = productService.getProductById(request.getProductId());
        SellingResponse res = new SellingResponse();
        if (product.getStock() >= 1) {
            if (request.getPaidPrice() >= product.getPrice()) { //not change
                //update product's stock
                product.setStock(product.getStock() - 1);
                productService.updateProductById(product.getId(), product);

                //update machine's total price
                machine.setTotalPrice(machine.getTotalPrice() + product.getPrice());
                updateMachineById(machine.getId(), machine);

                res.setChange(0);
                if (request.getPaidPrice() > product.getPrice())
                    res.setChange(request.getPaidPrice() - product.getPrice());
                res.setMessage("Selling successful!");
            } else {
                throw new SellingNotSuccessfulException("Selling not successful! (Insufficient Balance)");
            }
        } else {
            throw new SellingNotSuccessfulException("Selling not successful! (No stock)");
        }
        return res;
    }
}
