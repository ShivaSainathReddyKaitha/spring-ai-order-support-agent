package com.example.demo.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Payment;
import com.example.demo.entity.PaymentStatus;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	
	@Query("""
		    SELECT p FROM Payment p JOIN p.order o
		    WHERE o.customerId = :customerId
		      AND p.paymentStatus = :paymentStatus
		      AND p.paidAt >= :previousDate
		    """)
	List<Payment> findByIDAndDate(
		    @Param("customerId") Long customerId,
		    @Param("paymentStatus") PaymentStatus paymentStatus,
		    @Param("previousDate") Instant previousDate
			);
}
