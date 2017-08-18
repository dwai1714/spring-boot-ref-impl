package com.das.liquor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.das.liquor.model.Liquor;

public interface LiquorRepository extends JpaRepository<Liquor, Long> {

}
