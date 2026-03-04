package com.project.uber.services.Impl;

import com.project.uber.dto.WalletTransactionDTO;
import com.project.uber.entities.WalletTransaction;
import com.project.uber.repositories.WalletTransectionRepository;
import com.project.uber.services.WalletTransectionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransectionServiceImpl implements WalletTransectionService {
    private final WalletTransectionRepository walletTransectionRepository;
    private final ModelMapper modelMapper;
    @Override
    public void createNewWalletTransection(WalletTransaction walletTransaction) {
        walletTransectionRepository.save(walletTransaction);
    }
}
