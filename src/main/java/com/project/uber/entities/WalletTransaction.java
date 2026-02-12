package com.project.uber.entities;

import com.project.uber.entities.enums.TransectionMethod;
import com.project.uber.entities.enums.TransectionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
