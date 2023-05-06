package com.kata.seating.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.kata.seating.Entity.Seat;

public interface SeatRepository extends MongoRepository<Seat, String>{

}