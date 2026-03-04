package com.project.uber.services.Impl;

import com.project.uber.dto.RideDTO;
import com.project.uber.dto.WalletDTO;
import com.project.uber.dto.WalletTransactionDTO;
import com.project.uber.entities.Ride;
import com.project.uber.entities.User;
import com.project.uber.entities.Wallet;
import com.project.uber.entities.WalletTransaction;
import com.project.uber.entities.enums.TransectionMethod;
import com.project.uber.entities.enums.TransectionType;
import com.project.uber.exceptions.ResourceNotFoundException;
import com.project.uber.repositories.WalletRepository;
import com.project.uber.services.WalletService;
import com.project.uber.services.WalletTransectionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;
    private final WalletTransectionService walletTransectionService;

    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransectionMethod transectionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance() + amount);
        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transectionType(TransectionType.CREDIT)
                .transectionMethod(transectionMethod)
                .amount(amount)
                .build();
        wallet.getTransactions().add(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransectionMethod transectionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance() - amount);
        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .amount(amount)
                .wallet(wallet)
                .ride(ride)
                .transectionMethod(transectionMethod)
                .transectionType(TransectionType.DEBIT)
                .build();
        walletTransectionService.createNewWalletTransection(walletTransaction);
        return walletRepository.save(wallet);
    }

    // only for drivers to withdraw all money to their account
    @Override
    public void withdrawAllMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id: "+ walletId));
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("User is not found with Id: "+user.getId()));
    }
}
