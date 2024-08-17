package com.example.offer_management_be.service;

import com.example.offer_management_be.exception.OfferNotFoundException;
import com.example.offer_management_be.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.offer_management_be.models.Offer;


import java.util.List;

@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;

    public List<Offer> getAllOffers(){
        return offerRepository.findAll();
    }

    public Offer getById(Long id){
        return offerRepository.findById(id).orElseThrow(() -> new OfferNotFoundException(id));
    }

    public Offer saveOffer(Offer offer){
        if(checkInfor(offer)){
            modifyInforOfOffer(offer);
            return offerRepository.save(offer);
        }
        return null;
    }


    public Offer modifyInforOfOffer(Offer offer){
        double discountedPrice = offer.getOriginalPrice()*( 1 - offer.getDiscountPercentage()*0.01);
        offer.setDiscountedPrice(discountedPrice);

        return offer;
    }

    public void deleteOffer (Long id){
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
