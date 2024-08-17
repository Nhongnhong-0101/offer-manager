package com.example.offer_management_be.controller;

import com.example.offer_management_be.models.Offer;
import com.example.offer_management_be.service.OfferService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OfferController {
    private OfferService offerService;

    public OfferController(OfferService offerService){
        this.offerService = offerService;
    }

    @GetMapping("/offers")
    public List<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

    @PostMapping("/offers")
    public Offer createOffer(@RequestBody Offer offer){
        return offerService.saveOffer(offer);
    }

    @GetMapping("/offers/{id}")
    public Offer getById(@PathVariable Long id){
        return offerService.getById(id);
    }

    @PutMapping("/offers/{id}")
    public Offer repalceOffer (@RequestBody Offer offer, @PathVariable Long id){
        Offer unchangedOffer = offerService.getById(id);
        unchangedOffer.setTitle(offer.getTitle());
        unchangedOffer.setDescription(offer.getDescription());
        unchangedOffer.setOriginalPrice(offer.getOriginalPrice());
        unchangedOffer.setDiscountPercentage(offer.getDiscountPercentage());
        unchangedOffer.setDiscountedPrice(offer.getDiscountedPrice());

        return offerService.saveOffer(offer);

    }

    @DeleteMapping("/offers/{id}")
    public void deleteOffer(@PathVariable Long id){
        offerService.deleteOffer(id);
    }

}
