package com.example.warehouseapi.controller;

import com.example.warehouseapi.dto.ShopDto;
import com.example.warehouseapi.entity.Shop;
import com.example.warehouseapi.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public ResponseEntity<List<Shop>> getAllShops() {
        List<Shop> shops = shopService.getAllShops();
        return ResponseEntity.ok(shops);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable Long id) {
        Optional<Shop> shop = shopService.getShopById(id);
        return shop.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createShop(@Valid @RequestBody ShopDto shopDto) {
        try {
            Shop savedShop = shopService.createShop(shopDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedShop);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateShop(@PathVariable Long id, @Valid @RequestBody ShopDto shopDto) {
        try {
            Shop updatedShop = shopService.updateShop(id, shopDto);
            return ResponseEntity.ok(updatedShop);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShop(@PathVariable Long id) {
        try {
            shopService.deleteShop(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<Shop>> getShopsByWarehouse(@PathVariable Long warehouseId) {
        List<Shop> shops = shopService.getShopsByWarehouse(warehouseId);
        return ResponseEntity.ok(shops);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Shop> getShopByName(@PathVariable String name) {
        Optional<Shop> shop = shopService.getShopByName(name);
        return shop.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
