package com.bootcamp.libraryProject.controller;

import com.bootcamp.libraryProject.model.Book;
import com.bootcamp.libraryProject.model.Loan;
import com.bootcamp.libraryProject.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService){
        this.loanService = loanService;
    }

    @GetMapping("/loans")
    public List<Loan> getAllLoans(){
        return loanService.getAll();
    }
    @PostMapping("/loans")
    public ResponseEntity<Loan> createLoan(@RequestBody Loan newLoan){
        try {
            Loan createdLoan =  loanService.addLoan(newLoan);
            return new ResponseEntity<>(createdLoan, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/loans/{id}")
    public ResponseEntity<Loan> findLoanById(@PathVariable int id){
        Optional<Loan> foundLoan = loanService.findLoan(id);
        if(foundLoan.isPresent()){
            return new ResponseEntity<>(foundLoan.get(), HttpStatus.FOUND);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/loans/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable int id, @RequestBody Loan updatedLoan){
        try {
            Loan loan = loanService.updatedLoan(id, updatedLoan);
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
