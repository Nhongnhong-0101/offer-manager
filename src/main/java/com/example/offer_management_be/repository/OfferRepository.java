package com.example.offer_management_be.repository;

import com.example.offer_management_be.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    Optional<Offer> findByTitle(String title);

}
