package com.example.warehouseapi.util;

import com.example.warehouseapi.dto.ProductDto;
import com.example.warehouseapi.entity.Product;
import com.example.warehouseapi.entity.Shop;
import com.example.warehouseapi.entity.Warehouse;
import com.example.warehouseapi.repository.ShopRepository;
import com.example.warehouseapi.repository.WarehouseRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DtoMapper {

    private final ShopRepository shopRepository;
    private final WarehouseRepository warehouseRepository;

    public DtoMapper(ShopRepository shopRepository, WarehouseRepository warehouseRepository) {
        this.shopRepository = shopRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public ProductDto toProductDto(Product product) {
        Optional<Shop> shop = shopRepository.findById(product.getShopId());
        Optional<Warehouse> warehouse = warehouseRepository.findById(product.getWarehouseId());

        String shopName = shop.map(Shop::getName).orElse("Unknown Shop");
        String warehouseName = warehouse.map(Warehouse::getName).orElse("Unknown Warehouse");

        return new ProductDto(product, shopName, warehouseName);
    }

    public List<ProductDto> toProductDtoList(List<Product> products) {
        return products.stream()
                .map(this::toProductDto)
                .collect(Collectors.toList());
    }
}