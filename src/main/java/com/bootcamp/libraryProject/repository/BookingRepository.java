package com.bootcamp.libraryProject.repository;

import com.bootcamp.libraryProject.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByBookIdAndBookedFalse(int bookId);
}
