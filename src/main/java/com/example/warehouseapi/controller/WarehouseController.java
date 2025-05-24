package com.example.warehouseapi.controller;

import com.example.warehouseapi.dto.WarehouseDto;
import com.example.warehouseapi.entity.Warehouse;
import com.example.warehouseapi.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        Optional<Warehouse> warehouse = warehouseService.getWarehouseById(id);
        return warehouse.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createWarehouse(@Valid @RequestBody WarehouseDto warehouseDto) {
        try {
            Warehouse savedWarehouse = warehouseService.createWarehouse(warehouseDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedWarehouse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWarehouse(@PathVariable Long id, @Valid @RequestBody WarehouseDto warehouseDto) {
        try {
            Warehouse updatedWarehouse = warehouseService.updateWarehouse(id, warehouseDto);
            return ResponseEntity.ok(updatedWarehouse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWarehouse(@PathVariable Long id) {
        try {
            warehouseService.deleteWarehouse(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<Warehouse>> getWarehousesByShop(@PathVariable Long shopId) {
        List<Warehouse> warehouses = warehouseService.getWarehousesByShop(shopId);
        return ResponseEntity.ok(warehouses);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Warehouse> getWarehouseByName(@PathVariable String name) {
        Optional<Warehouse> warehouse = warehouseService.getWarehouseByName(name);
        return warehouse.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}