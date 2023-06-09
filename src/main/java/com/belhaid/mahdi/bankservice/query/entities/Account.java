package com.belhaid.mahdi.bankservice.query.entities;

import com.belhaid.mahdi.bankservice.commandsapi.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Id
    private String accountId;
    private Instant createdAt;
    private String clientId;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "account")
    private List<Operation> transactions;
}
