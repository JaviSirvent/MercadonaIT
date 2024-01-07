package com.mercadona.product.dto;

import lombok.Data;

@Data
public class ProductDto {
    private static final String ALMACENES = "Almacenes";
    private static final String COLMENAS = "Colmenas";
    private static final String DESTINATION = "Destination: ";
    private static final String DESTINATION_CODE_IS_NOT_A_VALID_NUMBER = "Destination code is not a valid number";
    private static final String EXTERNAL_SUPPLIER = " (External supplier)";
    private static final String HACENDADO = " (Hacendado)";
    private static final int HACENDADO_CODE = 8437008;
    private static final String LINE_BREAK = "\n";
    private static final String MERCADONA_ESP = "Mercadona España";
    private static final String MERCADONA_PRT = "Mercadona Portugal";
    private static final String OFICINAS_MERCADONA = "Oficinas Mercadona";
    private static final String PRODUCT_CODE = "Product Code: ";
    private static final String PRODUCT_EAN = "Product EAN: ";
    private static final String SUPPLIER = "Supplier: ";

    private String supplierCode;
    private String productCode;
    private String destinationCode;
    private String ean;

    @Override
    public String toString() {
        String supplier = supplierInfo(supplierCode);
        String destination = destinationInfo(destinationCode);
        return PRODUCT_EAN + ean + LINE_BREAK + supplier + LINE_BREAK +
                PRODUCT_CODE + productCode + LINE_BREAK + DESTINATION + destination;
    }

    private static String supplierInfo(String supplierCode) {
        String supplier = SUPPLIER + supplierCode;
        if (HACENDADO_CODE == Integer.parseInt(supplierCode)) {
            supplier = supplier.concat(HACENDADO);
        }
        else {
            supplier = supplier.concat(EXTERNAL_SUPPLIER);
        }
        return supplier;
    }

    private static String destinationInfo(String destinationCode) {
        return switch (Integer.parseInt(destinationCode)) {
            case 0 -> COLMENAS;
            case 1, 2, 3, 4, 5 -> MERCADONA_ESP;
            case 6 -> MERCADONA_PRT;
            case 8 -> ALMACENES;
            case 9 -> OFICINAS_MERCADONA;
            default -> DESTINATION_CODE_IS_NOT_A_VALID_NUMBER;
        };
    }
}
