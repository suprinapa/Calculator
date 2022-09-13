package com.example.confrence.repository;

import com.example.confrence.domain.Formula;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaRepository extends JpaSpecificationExecutor<Formula>, JpaRepository<Formula,Long> {

}
