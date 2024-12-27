package com.bootcamp.libraryProject.repository;

import com.bootcamp.libraryProject.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository <Loan, Integer> {
}
