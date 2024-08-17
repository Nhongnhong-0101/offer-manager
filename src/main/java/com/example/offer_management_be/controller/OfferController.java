package com.example.offer_management_be.controller;

import com.example.offer_management_be.exception.OfferNotFoundException;
import com.example.offer_management_be.models.Offer;
import com.example.offer_management_be.response.ExceptionResponse;
import com.example.offer_management_be.service.OfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class OfferController {
    private OfferService offerService;

    Logger logger = LoggerFactory.getLogger(OfferController.class);

    public OfferController(OfferService offerService){
        this.offerService = offerService;
    }

    @GetMapping("/offers")
    public ResponseEntity<List<Offer>> getAllOffers() {
        return ResponseEntity.ok().body(offerService.getAllOffers());
    }

    @PostMapping("/offers")
    public ResponseEntity createOffer(@RequestBody Offer offer){
        try {
            Offer newOffer = offerService.createOffer(offer);
            return ResponseEntity.ok(newOffer);
        }
        catch (Exception ex){
            ExceptionResponse exceptionResponse = new ExceptionResponse();
            exceptionResponse.setStatusCode(HttpStatus.CONFLICT.value());
            exceptionResponse.setMessage(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
        }
    }

    @GetMapping("/offers/{id}")
    public ResponseEntity getById(@PathVariable UUID id){
        try {
            Offer offer = offerService.getById(id);
            return ResponseEntity.ok(offer);
        } catch (Exception e){
            ExceptionResponse response = new ExceptionResponse();
            response.setMessage(e.toString());
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(404).body(response);
        }
    }

    @PutMapping("/offers/{id}")
    public ResponseEntity updateOffer (@RequestBody Offer offer, @PathVariable UUID id){
        try {
            Offer updatedOffer = offerService.updateOffer(id, offer);
            return ResponseEntity.ok(updatedOffer);
        } catch (Exception e) {
            ExceptionResponse response = new ExceptionResponse();
            response.setMessage(e.toString());
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/offers/{id}")
    public ResponseEntity deleteOffer(@PathVariable UUID id){
        offerService.deleteOffer(id);
        return ResponseEntity.ok(null);
    }

}
