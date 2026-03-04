package com.project.uber.services.Impl;

import com.project.uber.entities.Payment;
import com.project.uber.entities.Ride;
import com.project.uber.entities.enums.PaymentStatus;
import com.project.uber.exceptions.ResourceNotFoundException;
import com.project.uber.repositories.PaymentRepository;
import com.project.uber.services.PaymentService;
import com.project.uber.strategies.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;

    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride).orElseThrow(() -> new ResourceNotFoundException("Ride is not available with the given ride id: "+ ride.getId()));
        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);
    }



    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment = Payment.builder()
                .amount(ride.getFare())
                .paymentStatus(PaymentStatus.PENDING)
                .paymentMethod(ride.getPaymentMethod())
                .ride(ride)
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
        payment.setPaymentStatus(paymentStatus);
        paymentRepository.save(payment);
    }
}
