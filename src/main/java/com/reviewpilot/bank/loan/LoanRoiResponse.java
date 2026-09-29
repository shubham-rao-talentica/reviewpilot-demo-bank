package com.reviewpilot.bank.loan;

import java.math.BigDecimal;

public record LoanRoiResponse(
        BigDecimal principal,
        BigDecimal annualRatePercent,
        int tenureMonths,
        BigDecimal monthlyEmi,
        BigDecimal totalInterest,
        BigDecimal totalPayment) {
}
