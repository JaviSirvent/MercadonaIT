package com.mercadona.product.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {
    public static final String EAN = "8437008097711";
    public Product product;
    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1);
        product.setSupplierCode("8437008");
        product.setProductCode("09771");
        product.setDestinationCode("1");
        product.setEan(EAN);
    }

    @Test
    void getId() {
        Assertions.assertEquals(product.getId(), 1);
    }

    @Test
    void getSupplierCode() {
        Assertions.assertEquals(product.getSupplierCode(), "8437008");
    }

    @Test
    void getProductCode() {
        Assertions.assertEquals(product.getProductCode(), "09771");
    }

    @Test
    void getDestinationCode() {
        Assertions.assertEquals(product.getDestinationCode(), "1");
    }

    @Test
    void getEan() {
        Assertions.assertEquals(product.getEan(), EAN);
    }

    @Test
    void setId() {
        product.setId(2);
        Assertions.assertEquals(product.getId(), 2);
    }

    @Test
    void setSupplierCode() {
        product.setSupplierCode("2775119");
        Assertions.assertEquals(product.getSupplierCode(), "2775119");
    }

    @Test
    void setProductCode() {
        product.setProductCode("85736");
        Assertions.assertEquals(product.getProductCode(), "85736");
    }

    @Test
    void setDestinationCode() {
        product.setDestinationCode("5");
        Assertions.assertEquals(product.getDestinationCode(), "5");
    }

    @Test
    void setEan() {
        product.setEan("2775119857365");
        Assertions.assertEquals(product.getEan(), "2775119857365");
    }
}