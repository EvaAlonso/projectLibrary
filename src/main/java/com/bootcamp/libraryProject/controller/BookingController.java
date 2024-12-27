package com.bootcamp.libraryProject.controller;


import com.bootcamp.libraryProject.service.BookingService;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/Bookings")
public class BookingController {
    private BookingService bookingService;

    @PostMapping("/booking")
    public String bookingBook(@RequestParam int memberId, @RequestParam int bookId){
        return bookingService.bookingBook(memberId, bookId);
    }

    @PostMapping("/loan/{bookingId}")
    public String loanBook(@PathVariable int bookingId){
        return bookingService.loanBook(bookingId);
    }
}
