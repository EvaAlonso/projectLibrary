package com.bootcamp.libraryProject.controller;


import com.bootcamp.libraryProject.model.Booking;
import com.bootcamp.libraryProject.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping
    public String bookingBook(@RequestParam int memberId, @RequestParam int bookId) {
        return bookingService.bookingBook(memberId, bookId);
    }

    @PostMapping("/loan/{bookingId}")
    public String loanBook(@PathVariable int bookingId){
        return bookingService.loanBook(bookingId);
    }
}
