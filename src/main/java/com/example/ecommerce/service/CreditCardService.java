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

    public CreditCard save(CreditCard creditCard) {
        creditCardRepository.save(creditCard);
        return creditCard;
    }

    public boolean isValidCreditCard(CreditCard creditCard) {
        YearMonth currentYearMonth = YearMonth.now();
        if (creditCard.getExpiryDate().isBefore(currentYearMonth) || creditCard.getExpiryDate().equals(currentYearMonth)) {
            return false;
        }
        if (!isValidCVV(creditCard)) {
            return false;
        }
        if (!isValidPAN(creditCard)) {
            return false;
        }
        return true;
    }

    private boolean isValidCVV(CreditCard creditCard) {
        String cvv = creditCard.getCvv();
        if (creditCard.getCardNumber().startsWith("34") || creditCard.getCardNumber().startsWith("37")) {
            return cvv.length() == 4;
        } else {
            return cvv.length() == 3;
        }
    }

    private boolean isValidPAN(CreditCard creditCard) {
        String cardNumber = creditCard.getCardNumber();
        int length = cardNumber.length();
        return length >= 16 && length <= 19;
    }
}
