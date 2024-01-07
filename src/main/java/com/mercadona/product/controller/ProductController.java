package com.mercadona.product.controller;

import com.mercadona.product.dto.Product;
import com.mercadona.product.dto.ProductDto;
import com.mercadona.product.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {
    private static final String ERROR_DUPLICATE_EAN_CODE = "Error. Duplicate EAN code.";
    private static final String THERE_IS_NO_PRODUCT_WITH_EAN_CODE = "There is no product with EAN code = ";
    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/saveProduct")
    public String saveProduct(@RequestBody ProductDto productDto) throws IllegalArgumentException, DataIntegrityViolationException {
        Product product = convertToEntity(productDto);
        try {
            ProductDto productDtoCreated = convertToDto(productService.saveProduct(product));
            return productDtoCreated.toString();
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new DataIntegrityViolationException(ERROR_DUPLICATE_EAN_CODE);
        }
    }

    @GetMapping("/getProductByEan/{ean}")
    public ProductDto getProductByEan(@PathVariable("ean") String ean) {
        return productService.getProductByEan(ean).isPresent() ?
                convertToDto(productService.getProductByEan(ean).get()) : null;
    }

    @GetMapping("/getProductByEan/stringFormat/{ean}")
    public String getProductByEanStringFormat(@PathVariable("ean") String ean) {
        return productService.getProductByEan(ean).isPresent() ?
                convertToDto(productService.getProductByEan(ean).get()).toString() : THERE_IS_NO_PRODUCT_WITH_EAN_CODE + ean;
    }

    @GetMapping("/deleteProductByEan/{ean}")
    public void deleteProductByEan(@PathVariable("ean") String ean) {
        Optional<Product> product = productService.getProductByEan(ean);
        productService.deleteProduct(product.orElse(null));
    }

    @GetMapping("/getProducts")
    public List<ProductDto> getProducts() {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : productService.getProducts()) {
            productDtoList.add(convertToDto(product));
        }
        return productDtoList;
    }

    @GetMapping("/deleteAllProducts")
    public void deleteAllProducts() {
        productService.deleteAllProducts();
    }

    @ExceptionHandler({IllegalArgumentException.class, DataIntegrityViolationException.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private String handleExceptions(Exception ex) {
        return ex.getMessage();
    }

    private Product convertToEntity(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }

    private ProductDto convertToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }
}