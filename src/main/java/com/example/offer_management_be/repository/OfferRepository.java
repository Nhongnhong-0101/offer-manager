package com.example.offer_management_be.repository;

import com.example.offer_management_be.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface OfferRepository extends JpaRepository<Offer, Long> {
    Optional<Offer> findByTitle(String title);

}
