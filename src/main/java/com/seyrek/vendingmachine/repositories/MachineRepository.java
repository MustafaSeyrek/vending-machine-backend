package com.seyrek.vendingmachine.repositories;

import com.seyrek.vendingmachine.entities.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepository extends JpaRepository<Machine, Integer> {
}
