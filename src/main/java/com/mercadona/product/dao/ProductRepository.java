package com.mercadona.product.dao;

import com.mercadona.product.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long id);
    Optional<Product> findByEan(String ean);
}
