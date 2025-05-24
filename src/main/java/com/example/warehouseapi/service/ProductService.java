package com.example.warehouseapi.service;

import com.example.warehouseapi.dto.ProductDto;
import com.example.warehouseapi.entity.Product;
import com.example.warehouseapi.repository.ProductRepository;
import com.example.warehouseapi.repository.ShopRepository;
import com.example.warehouseapi.repository.WarehouseRepository;
import com.example.warehouseapi.util.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;
    private final WarehouseRepository warehouseRepository;
    private final DtoMapper dtoMapper;

    public ProductService(ProductRepository productRepository,
                          ShopRepository shopRepository,
                          WarehouseRepository warehouseRepository,
                          DtoMapper dtoMapper) {
        this.productRepository = productRepository;
        this.shopRepository = shopRepository;
        this.warehouseRepository = warehouseRepository;
        this.dtoMapper = dtoMapper;
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getAllProductsWithDetails() {
        List<Product> products = productRepository.findAll();
        return dtoMapper.toProductDtoList(products);
    }

    @Transactional(readOnly = true)
    public Optional<ProductDto> getProductByIdWithDetails(Long id) {
        return productRepository.findById(id)
                .map(dtoMapper::toProductDto);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsByShop(Long shopId) {
        return productRepository.findByShopId(shopId);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsByWarehouse(Long warehouseId) {
        return productRepository.findByWarehouseId(warehouseId);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsByShopAndWarehouse(Long shopId, Long warehouseId) {
        return productRepository.findByShopIdAndWarehouseId(shopId, warehouseId);
    }

    @Transactional(readOnly = true)
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    public Product createProduct(ProductDto productDto) {
        if (!shopRepository.existsById(productDto.getShopId())) {
            throw new RuntimeException("Shop not found with id: " + productDto.getShopId());
        }

        if (!warehouseRepository.existsById(productDto.getWarehouseId())) {
            throw new RuntimeException("Warehouse not found with id: " + productDto.getWarehouseId());
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setShopId(productDto.getShopId());
        product.setWarehouseId(productDto.getWarehouseId());

        return productRepository.save(product);
    }

    public ProductDto createProductWithDetails(ProductDto productDto) {
        Product savedProduct = createProduct(productDto);
        return dtoMapper.toProductDto(savedProduct);
    }

    public Product updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));


        if (!shopRepository.existsById(productDto.getShopId())) {
            throw new RuntimeException("Shop not found with id: " + productDto.getShopId());
        }

        if (!warehouseRepository.existsById(productDto.getWarehouseId())) {
            throw new RuntimeException("Warehouse not found with id: " + productDto.getWarehouseId());
        }

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setShopId(productDto.getShopId());
        product.setWarehouseId(productDto.getWarehouseId());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}