package com.project.uber.strategies.impl;

import com.project.uber.entities.Driver;
import com.project.uber.entities.Payment;
import com.project.uber.entities.Rider;
import com.project.uber.entities.enums.PaymentStatus;
import com.project.uber.entities.enums.TransectionMethod;
import com.project.uber.repositories.PaymentRepository;
import com.project.uber.services.PaymentService;
import com.project.uber.services.WalletService;
import com.project.uber.strategies.PaymentStrategy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();
        walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(), null, payment.getRide(), TransectionMethod.RIDE);
        double driverCut = payment.getAmount() * (1 - PLATFORM_COMMISSION);
        walletService.addMoneyToWallet(driver.getUser(), driverCut, null, payment.getRide(), TransectionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
