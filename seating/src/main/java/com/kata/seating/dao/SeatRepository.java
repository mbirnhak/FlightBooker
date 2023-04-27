package com.kata.seating.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kata.seating.Entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer>{

}