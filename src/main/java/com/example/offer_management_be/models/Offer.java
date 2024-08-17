package com.example.offer_management_be.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private double discountPercentage;
    private double originalPrice;
    private double discountedPrice;

    public Offer(String title, String description, double discountPercentage, double originalPrice, double discountedPrice) {
        this.title = title;
        this.description = description;
        this.discountPercentage = discountPercentage;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
    }


}
