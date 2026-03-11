package com.ragul.ProoductService.service;

import com.ragul.ProoductService.dto.ProductRequest;
import com.ragul.ProoductService.dto.ProductResponse;
import com.ragul.ProoductService.exception.ProductAlreadyExistsException;
import com.ragul.ProoductService.exception.ProductNotFoundException;
import com.ragul.ProoductService.mapper.ProductMapper;
import com.ragul.ProoductService.model.Product;
import com.ragul.ProoductService.model.ProductStatus;
import com.ragul.ProoductService.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ragul.ProoductService.mapper.ProductMapper.mapToResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        if (productRepository.existsBySku(request.sku())) {
            throw new ProductAlreadyExistsException(
                    "Product with SKU " + request.sku() + " already exists"
            );
        }

        Product product = Product.builder()
                .name(request.name())
                .sku(request.sku())
                .description(request.description())
                .category(request.category())
                .price(request.price())
                .status(ProductStatus.ACTIVE)
                .build();

        Product savedProduct = productRepository.save(product);

        return mapToResponse(savedProduct);
    }

    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(
                        "Product not found with id: " + id
                ));
        return mapToResponse(product);
    }

    @Transactional(readOnly = true)
    public ProductResponse getProductBySku(String sku) {
        Product product = productRepository.findBySku(sku).orElseThrow(() -> new ProductNotFoundException(
                "Product not found with sku: " + sku
        ));
        return mapToResponse(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getAllActiveProducts() {
        List<Product> activeProducts = productRepository.findAllByStatus(ProductStatus.ACTIVE);

        return activeProducts.stream()
                .map(ProductMapper::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public Boolean isProductAvailable(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(
                "Product not found with id: " + id
        ));
        return product.getStatus() == ProductStatus.ACTIVE;
    }

}
