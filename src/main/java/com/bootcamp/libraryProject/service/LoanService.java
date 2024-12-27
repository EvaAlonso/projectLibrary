package com.bootcamp.libraryProject.service;

import com.bootcamp.libraryProject.model.Loan;
import com.bootcamp.libraryProject.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository LoanRepository;

    public LoanService(LoanRepository LoanRepository) {
        this.LoanRepository = LoanRepository;
    }

    public List<Loan> getAll(){
        return LoanRepository.findAll();
    }
    public Loan addLoan(Loan newLoan){
        return LoanRepository.save(newLoan);
    }
    public void deleteLoan(int id){
        LoanRepository.deleteById(id);
    }
    public Optional<Loan> findLoan(int id){
        return LoanRepository.findById(id);
    }
    public Loan updatedLoan(int id, Loan updatedLoan){
        Optional<Loan> foundLoan = LoanRepository.findById(id);
        if(foundLoan.isPresent()){
            Loan existingLoan = foundLoan.get();
            existingLoan.setLoanDate(updatedLoan.getLoanDate());
            existingLoan.setReturnDate(updatedLoan.getReturnDate());
            return LoanRepository.save(existingLoan);
        }
        throw new RuntimeException("Loan not found with id: " + id);
    }
}
