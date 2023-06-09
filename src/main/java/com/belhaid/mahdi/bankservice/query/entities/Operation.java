package com.belhaid.mahdi.bankservice.query.entities;

import com.belhaid.mahdi.bankservice.commandsapi.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant transactionDate;
    private String initiatorId;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @JsonIgnore
    @ManyToOne
    Account account;
}
