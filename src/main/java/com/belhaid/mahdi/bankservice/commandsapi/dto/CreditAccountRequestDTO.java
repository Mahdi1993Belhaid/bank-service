package com.belhaid.mahdi.bankservice.commandsapi.dto;

import jakarta.validation.constraints.NotBlank;

public record CreditAccountRequestDTO(
        @NotBlank  String accountId,

        @NotBlank  String currency,
        @NotBlank  Double amount
) {
}
