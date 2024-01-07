package com.mercadona.product.controller;

import com.mercadona.product.dto.Product;
import com.mercadona.product.dto.ProductDto;
import com.mercadona.product.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    public static final String EAN = "8437008097711";
    @InjectMocks
    public ProductController productController;
    @Mock
    public ProductService productService;
    @Mock
    public ModelMapper modelMapper;
    public Product product;
    public ProductDto productDto;

    @BeforeEach
    void setUp() {
        productController = new ProductController();
        MockitoAnnotations.openMocks(this);
        productDto = new ProductDto();
        productDto.setSupplierCode("8437008");
        productDto.setProductCode("09771");
        productDto.setDestinationCode("1");
        productDto.setEan(EAN);
        product = new Product();
        product.setSupplierCode("8437008");
        product.setProductCode("09771");
        product.setDestinationCode("1");
        product.setEan(EAN);
    }

    @Test
    void saveProduct() {
        when(modelMapper.map(productDto, Product.class)).thenReturn(product);
        when(modelMapper.map(product, ProductDto.class)).thenReturn(productDto);
        when(productService.saveProduct(product)).thenReturn(product);
        String productCreated = productController.saveProduct(productDto);
        Assertions.assertEquals(productDto.toString(), productCreated);
    }

    @Test
    void getProductByEan() {
        when(productService.getProductByEan(EAN)).thenReturn(Optional.ofNullable(product));
        when(modelMapper.map(product, ProductDto.class)).thenReturn(productDto);
        Assertions.assertEquals(productController.getProductByEan(EAN), productDto);
    }

    @Test
    void getProductByEanStringFormat() {
        when(productService.getProductByEan(EAN)).thenReturn(Optional.ofNullable(product));
        when(modelMapper.map(product, ProductDto.class)).thenReturn(productDto);
        Assertions.assertEquals(productController.getProductByEanStringFormat(EAN), productDto.toString());
    }

    @Test
    void deleteProductByEan() {
        when(productService.getProductByEan(EAN)).thenReturn(Optional.ofNullable(product));
        productController.deleteProductByEan(EAN);
    }

    @Test
    void getProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productService.getProducts()).thenReturn(productList);
        when(modelMapper.map(product, ProductDto.class)).thenReturn(productDto);
        Assertions.assertEquals(productController.getProducts().get(0), productDto);
    }

    @Test
    void deleteAllProducts() {
        productController.deleteAllProducts();
    }
}