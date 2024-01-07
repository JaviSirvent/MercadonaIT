package com.mercadona.product.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductDtoTest {
    public static final String EAN = "8437008097711";
    public ProductDto productDto;
    @BeforeEach
    void setUp() {
        productDto = new ProductDto();
        productDto.setSupplierCode("8437008");
        productDto.setProductCode("09771");
        productDto.setDestinationCode("1");
        productDto.setEan(EAN);
    }

    @Test
    void testToString() {
        productDto.setSupplierCode("4856239");
        productDto.setDestinationCode("8");
        productDto.setEan("4856239097718");
        Assertions.assertEquals(productDto.toString(), """
                Product EAN: 4856239097718
                Supplier: 4856239 (External supplier)
                Product Code: 09771
                Destination: Almacenes""");
    }

    @Test
    void testToStringSupplierHacendado() {
        Assertions.assertEquals(productDto.toString(), """
                Product EAN: 8437008097711
                Supplier: 8437008 (Hacendado)
                Product Code: 09771
                Destination: Mercadona España""");
    }

    @Test
    void getSupplierCode() {
        Assertions.assertEquals(productDto.getSupplierCode(), "8437008");
    }

    @Test
    void getProductCode() {
        Assertions.assertEquals(productDto.getProductCode(), "09771");
    }

    @Test
    void getDestinationCode() {
        Assertions.assertEquals(productDto.getDestinationCode(), "1");
    }

    @Test
    void getEan() {
        Assertions.assertEquals(productDto.getEan(), EAN);
    }

    @Test
    void setSupplierCode() {
        productDto.setSupplierCode("2775119");
        Assertions.assertEquals(productDto.getSupplierCode(), "2775119");
    }

    @Test
    void setProductCode() {
        productDto.setProductCode("85736");
        Assertions.assertEquals(productDto.getProductCode(), "85736");
    }

    @Test
    void setDestinationCode() {
        productDto.setDestinationCode("5");
        Assertions.assertEquals(productDto.getDestinationCode(), "5");
    }

    @Test
    void setEan() {
        productDto.setEan("2775119857365");
        Assertions.assertEquals(productDto.getEan(), "2775119857365");
    }
}