package com.example.offer_management_be.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "offers")
public class Offer {
    @Id
    private UUID id;
    private String title;
    private String description;
    private double discountPercentage;
    private double originalPrice;
    private double discountedPrice;

    public Offer(){
        this.id = UUID.randomUUID();
    }

    public Offer(String title, String description, double discountPercentage, double originalPrice, double discountedPrice) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.discountPercentage = discountPercentage;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
    }


}
