package com.reviewpilot.bank.credit;

import org.springframework.stereotype.Service;

@Service
public class CreditScoreService {

    public static final int MIN_SCORE = 300;
    public static final int MAX_SCORE = 900;

    public enum Rating { POOR, FAIR, GOOD, EXCELLENT }

    /**
     * Classifies a credit score (CIBIL-style 300-900 scale) into a rating band.
     */
    public Rating checkCreditScore(int score) {
        if (score < MIN_SCORE || score > MAX_SCORE) {
            throw new IllegalArgumentException("Credit score must be between " + MIN_SCORE + " and " + MAX_SCORE);
        }
        if (score >= 750) {
            return Rating.EXCELLENT;
        }
        if (score >= 700) {
            return Rating.GOOD;
        }
        if (score >= 650) {
            return Rating.FAIR;
        }
        return Rating.POOR;
    }
}
