package com.bootcamp.libraryProject.repository;

import com.bootcamp.libraryProject.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByBookIdAndBookedFalse(int bookId);
}
