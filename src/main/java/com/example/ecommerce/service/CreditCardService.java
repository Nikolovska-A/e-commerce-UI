package com.example.ecommerce.service;

import com.example.ecommerce.model.CreditCard;
import com.example.ecommerce.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    private final String AMERICAN_EXPRESS_PAN1 = "34";
    private final String AMERICAN_EXPRESS_PAN2 = "37";
    private final Integer CREDIT_CARD_MIN = 16;
    private final Integer CREDIT_CARD_MAX = 19;
    private final Integer AMERICAN_EXPRESS_CVV = 4;
    private final Integer CVV = 3;


    public boolean isValidCreditCard(CreditCard creditCard) {
        YearMonth currentYearMonth = YearMonth.now();
        if (creditCard.getExpiryDate().isBefore(currentYearMonth)) {
            return false;
        }
        if (!isValidCVV(creditCard)) {
            return false;
        }
        if (!luhnCheck(creditCard.getCardNumber())) {
            return false;
        }
        return true;
    }

    private boolean isValidCVV(CreditCard creditCard) {
        String cvv = creditCard.getCvv();
        if (creditCard.getCardNumber().startsWith(AMERICAN_EXPRESS_PAN1) || creditCard.getCardNumber().startsWith(AMERICAN_EXPRESS_PAN2)) {
            return cvv.length() == AMERICAN_EXPRESS_CVV;
        } else {
            return cvv.length() == CVV;
        }
    }


    public boolean luhnCheck(String cardNumber) {
        if (!(cardNumber.length() >= CREDIT_CARD_MIN && cardNumber.length() <= CREDIT_CARD_MAX)) {
            return false;
        }
        char checkDigit = cardNumber.charAt(cardNumber.length() - 1);
        String digit = calculateCheckDigit(cardNumber.substring(0, cardNumber.length() - 1));
        return checkDigit == digit.charAt(0);
    }


    public String calculateCheckDigit(String card) {
        String digit;
        int[] digits = new int[card.length()];
        for (int i = 0; i < card.length(); i++) {
            digits[i] = Character.getNumericValue(card.charAt(i));
        }

        for (int i = digits.length - 1; i >= 0; i -= 2)	{
            digits[i] += digits[i];

            if (digits[i] >= 10) {
                digits[i] = (digits[i] / 10) + (digits[i] % 10);
            }
        }
        int sum = 0;
        for (int i = 0; i < digits.length; i++) {
            sum += digits[i];
        }

        sum = sum * 9;
        digit = String.valueOf(sum);
        return digit.substring(digit.length() - 1);
    }
}
