package com.example.ecommerce.controller;

import com.example.ecommerce.model.CreditCard;
import com.example.ecommerce.request.CreditCardRequest;
import com.example.ecommerce.response.MessageResponse;
import com.example.ecommerce.service.CreditCardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;


@RestController
@RequestMapping("/v1/credit-card")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping("/validate")
    public ResponseEntity<MessageResponse> validateCreditCard(@Valid @RequestBody CreditCardRequest creditCardRequest) {
        try {
            CreditCard creditCard = new CreditCard();
            creditCard.setHolderName(creditCardRequest.getHolderName());
            creditCard.setCardNumber(creditCardRequest.getCardNumber());
            creditCard.setExpiryDate(YearMonth.parse(creditCardRequest.getExpiryYear() + "-" + creditCardRequest.getExpiryMonth()));
            creditCard.setCvv(creditCardRequest.getCvv());

            if (!creditCardService.isValidCreditCard(creditCard)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Invalid credit card information"));
            }
//            creditCardService.save(creditCard); //TODO save only if user requests
            return ResponseEntity.ok(new MessageResponse("Success!"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Something went wrong!"));
        }
    }


}

