package com.example.warehouseapi.service;

import com.example.warehouseapi.dto.ShopDto;
import com.example.warehouseapi.entity.Shop;
import com.example.warehouseapi.entity.Warehouse;
import com.example.warehouseapi.repository.ShopRepository;
import com.example.warehouseapi.repository.WarehouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShopService {

    private final ShopRepository shopRepository;
    private final WarehouseRepository warehouseRepository;

    public ShopService(ShopRepository shopRepository, WarehouseRepository warehouseRepository) {
        this.shopRepository = shopRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Transactional(readOnly = true)
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Shop> getShopById(Long id) {
        return shopRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Shop> getShopByName(String name) {
        return shopRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Shop> getShopsByWarehouse(Long warehouseId) {
        return shopRepository.findByWarehouseId(warehouseId);
    }

    public Shop createShop(ShopDto shopDto) {
        if (shopRepository.existsByName(shopDto.getName())) {
            throw new RuntimeException("Shop with name '" + shopDto.getName() + "' already exists");
        }

        Shop shop = new Shop();
        shop.setName(shopDto.getName());

        if (shopDto.getWarehouseIds() != null && !shopDto.getWarehouseIds().isEmpty()) {
            List<Warehouse> warehouses = warehouseRepository.findAllById(shopDto.getWarehouseIds());
            if (warehouses.size() != shopDto.getWarehouseIds().size()) {
                throw new RuntimeException("One or more warehouse IDs are invalid");
            }
            shop.setWarehouses(warehouses);
        }

        return shopRepository.save(shop);
    }

    public Shop updateShop(Long id, ShopDto shopDto) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shop not found with id: " + id));

        if (!shop.getName().equals(shopDto.getName()) && shopRepository.existsByName(shopDto.getName())) {
            throw new RuntimeException("Shop with name '" + shopDto.getName() + "' already exists");
        }

        shop.setName(shopDto.getName());

        if (shopDto.getWarehouseIds() != null) {
            List<Warehouse> warehouses = warehouseRepository.findAllById(shopDto.getWarehouseIds());
            if (warehouses.size() != shopDto.getWarehouseIds().size()) {
                throw new RuntimeException("One or more warehouse IDs are invalid");
            }
            shop.setWarehouses(warehouses);
        }

        return shopRepository.save(shop);
    }

    public void deleteShop(Long id) {
        if (!shopRepository.existsById(id)) {
            throw new RuntimeException("Shop not found with id: " + id);
        }
        shopRepository.deleteById(id);
    }
}