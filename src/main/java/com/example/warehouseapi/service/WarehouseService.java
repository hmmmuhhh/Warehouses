package com.example.warehouseapi.service;

import com.example.warehouseapi.dto.WarehouseDto;
import com.example.warehouseapi.entity.Warehouse;
import com.example.warehouseapi.repository.WarehouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Transactional(readOnly = true)
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Warehouse> getWarehouseByName(String name) {
        return warehouseRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Warehouse> getWarehousesByShop(Long shopId) {
        return warehouseRepository.findByShopId(shopId);
    }

    public Warehouse createWarehouse(WarehouseDto warehouseDto) {
        if (warehouseRepository.existsByName(warehouseDto.getName())) {
            throw new RuntimeException("Warehouse with name '" + warehouseDto.getName() + "' already exists");
        }

        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseDto.getName());

        return warehouseRepository.save(warehouse);
    }

    public Warehouse updateWarehouse(Long id, WarehouseDto warehouseDto) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + id));

        if (!warehouse.getName().equals(warehouseDto.getName()) && warehouseRepository.existsByName(warehouseDto.getName())) {
            throw new RuntimeException("Warehouse with name '" + warehouseDto.getName() + "' already exists");
        }

        warehouse.setName(warehouseDto.getName());
        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(Long id) {
        if (!warehouseRepository.existsById(id)) {
            throw new RuntimeException("Warehouse not found with id: " + id);
        }
        warehouseRepository.deleteById(id);
    }
}