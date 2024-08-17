package com.example.offer_management_be;

import com.example.offer_management_be.models.Offer;
import com.example.offer_management_be.service.OfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDb {
    private static final Logger log = LoggerFactory.getLogger(InitDb.class);

//    @Bean
//    CommandLineRunner initDatabase(OfferService offerService) {
//        log.info("Init data");
//        Offer offer1 = new Offer();
//        offer1.setTitle("Summer Sale");
//        offer1.setDescription("Get ready for summer with 50% off on all items!");
//        offer1.setDiscountPercentage(50.0);
//        offer1.setOriginalPrice(200.00);
//        offer1.setDiscountedPrice(0);
//
//        // Offer 2
//        Offer offer2 = new Offer();
//        offer2.setTitle("Winter Clearance");
//        offer2.setDescription("Huge clearance sale with 70% off on winter collection.");
//        offer2.setDiscountPercentage(70.0);
//        offer2.setOriginalPrice(300.00);
//        offer2.setDiscountedPrice(0);
//
//        // Offer 3
//        Offer offer3 = new Offer();
//        offer3.setTitle("Back to School");
//        offer3.setDescription("Save 30% on all school supplies.");
//        offer3.setDiscountPercentage(30.0);
//        offer3.setOriginalPrice(100.00);
//        offer3.setDiscountedPrice(0);
//
//        // Offer 4
//        Offer offer4 = new Offer();
//        offer4.setTitle("Black Friday Deals");
//        offer4.setDescription("Don't miss out! Up to 80% off on selected electronics.");
//        offer4.setDiscountPercentage(80.0);
//        offer4.setOriginalPrice(500.00);
//        offer4.setDiscountedPrice(0);
//
//        return args -> {
//            log.info("Preloading " + offerService.saveOffer(offer1));
//            log.info("Preloading " + offerService.saveOffer(offer2));
//            log.info("Preloading " + offerService.saveOffer(offer3));
//            log.info("Preloading " + offerService.saveOffer(offer4));
//        };
//    }
}
