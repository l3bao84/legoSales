package com.LeBao.sales.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "wishlist_products")
public class WishlistProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_product_id")
    private Long wistlistProductId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "wishlist-id")
    private Wishlist wishlist;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
