package com.example.offer_management_be.repository;

import com.example.offer_management_be.models.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfferRepository extends MongoRepository<Offer, UUID> {
    Optional<Offer> findByTitle(String title);

}
