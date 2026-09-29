package com.reviewpilot.bank.loan;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LoanRequest(
        @NotNull @DecimalMin(value = "0.01") BigDecimal principal,
        @NotNull @DecimalMin(value = "0.0") BigDecimal annualRatePercent,
        @Min(1) @Max(600) int tenureMonths) {
}
