package com.ragul.ProoductService.repository;

import com.ragul.ProoductService.model.Product;
import com.ragul.ProoductService.model.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsBySku(String sku);
    Optional<Product> findBySku(String sku);
    List<Product> findAllByStatus(ProductStatus productStatus);
}
