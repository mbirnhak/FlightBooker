package com.kata.payment.controller;

import com.kata.payment.exception.ResourceNotFoundException;
import com.kata.payment.model.Payment;
import com.kata.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/p1/payments")
public class Paymentcontroller {

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping
    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }

    //Create API
    @PostMapping
    public Payment createPayment(@RequestBody Payment payment){
        return paymentRepository.save(payment);
    }

    //get PAyment By id
    @GetMapping("{id}")
    public ResponseEntity<Payment> getPaymentbyId(@PathVariable long id){

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("payment not exits with id"+id));
        return ResponseEntity.ok(payment);

    }

    //update payment
    @PutMapping("{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable long id,@RequestBody Payment paymentDetails){
        Payment updatePayment = paymentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Payment Exits with id"+id));

        updatePayment.setAccountNo(paymentDetails.getAccountNo());
        updatePayment.setCardHolderName(paymentDetails.getCardHolderName());
        updatePayment.setCardNo(paymentDetails.getCardNo());
        updatePayment.setCvv(paymentDetails.getCvv());
        updatePayment.setExpDate(paymentDetails.getExpDate());


        paymentRepository.save(updatePayment);

        return ResponseEntity.ok(updatePayment);
    }

    //delete Payment
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deletePayment(@PathVariable long id){
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Payment Not exits with id"+id));

        paymentRepository.delete(payment);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
