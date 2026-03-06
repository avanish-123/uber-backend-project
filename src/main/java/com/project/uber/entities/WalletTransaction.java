package com.project.uber.entities;

import com.project.uber.entities.enums.TransectionMethod;
import com.project.uber.entities.enums.TransectionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(indexes = {
        @Index(name = "idx_wallet_transaction_wallet", columnList = "wallet_id"),
        @Index(name = "idx_wallet_transaction_wallet", columnList = "ride_id")
})
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private TransectionType transectionType;

    private TransectionMethod transectionMethod;

    @OneToOne
    private Ride ride;

    private String transactionId;

    @ManyToOne
    private  Wallet wallet;

    @CreationTimestamp
    private LocalDateTime timeStamp;
}
