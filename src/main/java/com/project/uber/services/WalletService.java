package com.project.uber.services;

import com.project.uber.entities.Ride;
import com.project.uber.entities.User;
import com.project.uber.entities.Wallet;
import com.project.uber.entities.enums.TransectionMethod;

public interface WalletService {
    Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransectionMethod transectionMethod);

    Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransectionMethod transectionMethod);

    void withdrawAllMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);

    Wallet findByUser(User user);
}
