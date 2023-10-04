package com.example.ecommerce.repository;

import com.example.ecommerce.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    CreditCard save(CreditCard creditCard);

}
