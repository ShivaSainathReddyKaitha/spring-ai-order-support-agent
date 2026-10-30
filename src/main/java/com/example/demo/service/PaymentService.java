package com.example.demo.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Payment;
import com.example.demo.entity.PaymentStatus;
import com.example.demo.repository.PaymentRepository;

@Service
public class PaymentService {
	
	private final PaymentRepository paymentRepository;

	public PaymentService(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}
//	
//	 getFailedPayments(long customerId, int lastNDays) returns failed payments
//	 * for that customer.
//	
	
	public List<Payment> getFailedPayments(Long customerId, int lastNDays) {
		
		if(lastNDays<1) {
			throw new RuntimeException("Atleast 1 day");
		}
		
		Instant previousDate = Instant.now().minus(lastNDays, ChronoUnit.DAYS);
		return paymentRepository.findByIDAndDate(customerId, PaymentStatus.FAILED, previousDate);
	}

}
