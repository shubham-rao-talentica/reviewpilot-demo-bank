package com.reviewpilot.bank.loan;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

@Service
public class LoanService {

    private static final BigDecimal MONTHS_PER_YEAR_PERCENT = BigDecimal.valueOf(1200);

    public LoanRoiResponse calculate(LoanRequest request) {
        BigDecimal principal = request.principal();
        int months = request.tenureMonths();
        BigDecimal monthlyRate = request.annualRatePercent().divide(MONTHS_PER_YEAR_PERCENT, MathContext.DECIMAL128);

        BigDecimal emi;
        if (monthlyRate.signum() == 0) {
            emi = principal.divide(BigDecimal.valueOf(months), 2, RoundingMode.HALF_UP);
        } else {
            // EMI = P * r * (1+r)^n / ((1+r)^n - 1)
            BigDecimal growth = BigDecimal.ONE.add(monthlyRate).pow(months, MathContext.DECIMAL128);
            emi = principal.multiply(monthlyRate).multiply(growth)
                    .divide(growth.subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);
        }

        BigDecimal totalPayment = emi.multiply(BigDecimal.valueOf(months));
        BigDecimal totalInterest = totalPayment.subtract(principal);
        return new LoanRoiResponse(principal, request.annualRatePercent(), months, emi, totalInterest, totalPayment);
    }
}
