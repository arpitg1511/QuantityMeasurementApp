package com.app.quantitymeasurement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.quantitymeasurement.model.QuantityRecord;

public interface QuantityRepository extends JpaRepository<QuantityRecord, Long> {
}
