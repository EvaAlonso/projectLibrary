package com.bootcamp.libraryProject.service;

import com.bootcamp.libraryProject.exception.ObjectNotFoundException;
import com.bootcamp.libraryProject.model.Book;
import com.bootcamp.libraryProject.model.Booking;
import com.bootcamp.libraryProject.model.Loan;
import com.bootcamp.libraryProject.model.Member;
import com.bootcamp.libraryProject.repository.BookRepository;
import com.bootcamp.libraryProject.repository.BookingRepository;
import com.bootcamp.libraryProject.repository.LoanRepository;
import com.bootcamp.libraryProject.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LoanRepository loanRepository;

    public BookingService(BookingRepository bookingRepository, BookRepository bookRepository, MemberRepository memberRepository, LoanRepository loanRepository) {
        this.bookingRepository = bookingRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.loanRepository = loanRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    public String bookingBook(int memberId, int bookId){
        Member member = memberRepository.findById(memberId).orElseThrow(()->new ObjectNotFoundException("Member", memberId));
        Book book = bookRepository.findById(bookId).orElseThrow(()->new ObjectNotFoundException("Book", bookId));

        if(!"Available".equals(book.getState())){
            throw new RuntimeException("Book is not available");
        }

        Booking booking = new Booking();
        booking.setMember(member);
        booking.setBook(book);
        booking.setBookingDate(LocalDateTime.now());
        booking.setBooked(false);

        bookingRepository.save(booking);

        book.setState("Booked");
        bookRepository.save(book);

        return "Booked made successfully";
    }

    public String loanBook(int bookingId){
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()->new ObjectNotFoundException("Booking", bookingId));

        if(booking.isBooked()){
            throw new RuntimeException("Book has been loaned");
        }

        booking.setBooked(true);
        bookingRepository.save(booking);

        Book book = booking.getBook();
        book.setState("Loaned");
        bookRepository.save(book);

        Loan loan = new Loan();
        loan.setBooking(booking);
        loan.setLoanDate(LocalDateTime.now());
        loan.setReturnDate(LocalDateTime.now().plusDays(10));

        loanRepository.save(loan);

        return "Book loaned successfully. Return date: " + loan.getReturnDate();
    }
}
