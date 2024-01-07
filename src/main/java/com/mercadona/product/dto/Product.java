package com.mercadona.product.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = -1205089065435340444L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String supplierCode;
    private String productCode;
    private String destinationCode;
    @Column(unique = true)
    private String ean;
}