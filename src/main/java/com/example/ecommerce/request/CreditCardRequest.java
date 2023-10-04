package com.example.ecommerce.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditCardRequest {

     public String holderName;
     public String cardNumber;
     public String expiryYear;
     public String ExpiryMonth;
     public String cvv;
}
