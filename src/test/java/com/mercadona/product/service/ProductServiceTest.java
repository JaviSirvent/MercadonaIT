package com.mercadona.product.service;

import com.mercadona.product.dao.ProductRepository;
import com.mercadona.product.dto.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    public static final String EAN = "8437008097711";
    @InjectMocks
    public ProductService productService;
    @Mock
    public ProductRepository dao;
    public Product product;
    @BeforeEach
    void setUp() {
        productService = new ProductService();
        MockitoAnnotations.openMocks(this);
        product = new Product();
        product.setSupplierCode("8437008");
        product.setProductCode("09771");
        product.setDestinationCode("1");
        product.setEan(EAN);
    }

    @Test
    void saveProduct() {
        when(dao.save(product)).thenReturn(product);
        Assertions.assertEquals(productService.saveProduct(product), product);
    }

    @Test
    void getProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(dao.findAll()).thenReturn(productList);
        Assertions.assertEquals(productService.getProducts().get(0), product);
    }

    @Test
    void getProductByEan() {
        Optional<Product> productOptional = Optional.ofNullable(product);
        when(dao.findByEan(EAN)).thenReturn(productOptional);
        Assertions.assertEquals(productService.getProductByEan(EAN), productOptional);
    }

    @Test
    void deleteProduct() {
        productService.deleteProduct(product);
    }

    @Test
    void deleteAllProducts() {
        productService.deleteAllProducts();
    }
}