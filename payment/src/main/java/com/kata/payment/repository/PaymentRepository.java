package com.kata.payment.repository;

import com.kata.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment,Long> {

    //all crud methods
}
