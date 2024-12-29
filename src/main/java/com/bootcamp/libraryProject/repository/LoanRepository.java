package com.bootcamp.libraryProject.repository;

import com.bootcamp.libraryProject.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository <Loan, Integer> {
}
