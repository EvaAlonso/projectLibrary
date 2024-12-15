package com.bootcamp.libraryProject.service;

import com.bootcamp.libraryProject.model.Loan;
import com.bootcamp.libraryProject.repository.ILoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final ILoanRepository ILoanRepository;

    public LoanService(ILoanRepository ILoanRepository) {
        this.ILoanRepository = ILoanRepository;
    }

    public List<Loan> getAll(){
        return ILoanRepository.findAll();
    }
    public Loan addLoan(Loan newLoan){
        return ILoanRepository.save(newLoan);
    }
    public void deleteLoan(int id){
        ILoanRepository.deleteById(id);
    }
    public Optional<Loan> findLoan(int id){
        return ILoanRepository.findById(id);
    }
    public Loan updatedLoan(int id, Loan updatedLoan){
        Optional<Loan> foundLoan = ILoanRepository.findById(id);
        if(foundLoan.isPresent()){
            Loan existingLoan = foundLoan.get();
            existingLoan.setLoan_date(updatedLoan.getLoan_date());
            existingLoan.setReturn_date(updatedLoan.getReturn_date());
            return ILoanRepository.save(existingLoan);
        }
        throw new RuntimeException("Loan not found with id: " + id);
    }
}
