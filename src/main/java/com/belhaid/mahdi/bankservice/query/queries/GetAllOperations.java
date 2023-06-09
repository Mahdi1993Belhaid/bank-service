package com.belhaid.mahdi.bankservice.query.queries;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllOperations{
    @NotBlank String accountId;
}
