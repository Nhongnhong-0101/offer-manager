package com.example.offer_management_be.service;

import com.example.offer_management_be.exception.OfferNotFoundException;
import com.example.offer_management_be.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.offer_management_be.models.Offer;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;

    public List<Offer> getAllOffers(){
        return offerRepository.findAll();
    }

    public Offer getById(UUID id){
        return offerRepository.findById(id).orElseThrow(() -> new OfferNotFoundException(id));
    }

    public Offer createOffer(Offer offer){
        if(checkInfor(offer)){
            if(offerRepository.findByTitle(offer.getTitle()).isPresent()){
                throw new IllegalArgumentException("Offer with this title already exists");
            }
        }
        calculateInforOfOffer(offer);
        return offerRepository.save(offer);
    }
    public Offer updateOffer(UUID id, Offer offer){
        Optional<Offer> existedOffer = offerRepository.findById(id);
        if(existedOffer.isEmpty()){
            throw new OfferNotFoundException(id);
        }
        else {
            Offer offerToUpdate = existedOffer.get();

            offerToUpdate.setTitle(offer.getTitle());
            offerToUpdate.setDescription(offer.getDescription());
            offerToUpdate.setDiscountPercentage(offer.getDiscountPercentage());
            offerToUpdate.setOriginalPrice(offer.getOriginalPrice());
            offerToUpdate.setDiscountedPrice(offer.getDiscountedPrice());

            offerToUpdate = calculateInforOfOffer(offerToUpdate);

            return offerRepository.save(offerToUpdate);

        }
    }


    public Offer calculateInforOfOffer(Offer offer){
        double discountedPrice = offer.getOriginalPrice()*( 1 - offer.getDiscountPercentage()*0.01);
        offer.setDiscountedPrice(discountedPrice);

        return offer;
    }

    public void deleteOffer (UUID id){
        offerRepository.deleteById(id);
    }

    public boolean checkInfor (Offer offer){
        if(!offer.getTitle().isEmpty() && !offer.getTitle().isBlank()
                && !offer.getDescription().isEmpty() && !offer.getDescription().isBlank()
                && offer.getOriginalPrice() >=0
                && offer.getDiscountPercentage()>=0  && offer.getDiscountPercentage()<= 100){
            return true;
        }
        return false;

    }

}
