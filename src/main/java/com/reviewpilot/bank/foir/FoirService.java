package com.reviewpilot.bank.foir;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

@Service
public class FoirService {

    /**
     * FOIR (Fixed Obligation to Income Ratio) as a percentage:
     * (monthly fixed obligations / gross monthly income) * 100.
     */
    public BigDecimal calculateFoir(BigDecimal monthlyObligations, BigDecimal monthlyIncome) {
        if (monthlyIncome == null || monthlyIncome.signum() <= 0) {
            throw new IllegalArgumentException("Monthly income must be greater than zero");
        }
        if (monthlyObligations == null || monthlyObligations.signum() < 0) {
            throw new IllegalArgumentException("Monthly obligations must not be negative");
        }
        return monthlyObligations.multiply(BigDecimal.valueOf(100))
                .divide(monthlyIncome, 2, RoundingMode.HALF_UP);
    }
}
