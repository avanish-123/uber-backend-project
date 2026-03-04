package com.project.uber.dto;

import com.project.uber.entities.enums.TransectionMethod;
import com.project.uber.entities.enums.TransectionType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WalletTransactionDTO {
    private Long id;
    private Double amount;
    private TransectionType transectionType;
    private TransectionMethod transectionMethod;
    private RideDTO ride;
    private String transactionId;
    private WalletDTO wallet;
    private LocalDateTime timeStamp;
}
