package com.Chiranjibi.JavaTrading.models;

import com.Chiranjibi.JavaTrading.domain.WalletTransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Data
public class WalletTranscation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Wallet wallet;

    private WalletTransactionType type;

    private LocalDate date;

    private String transferId;

    private String purpose;

    private Long amount;

}

