package com.belhaid.mahdi.bankservice.commandsapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public record CreateAccountRequest(
        @NotBlank  String currency,
         @NotBlank String clientId,
         @NotBlank String initiatorId,
        @NotBlank Double initialBalance
) {
}
