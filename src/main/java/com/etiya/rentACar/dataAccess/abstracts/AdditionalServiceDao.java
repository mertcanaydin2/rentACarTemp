package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.AdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdditionalServiceDao extends JpaRepository<AdditionalService, Integer> {

    List<AdditionalService> getAllById(int id);
}
