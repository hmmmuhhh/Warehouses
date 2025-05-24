package com.example.warehouseapi.repository;

import com.example.warehouseapi.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    Optional<Shop> findByName(String name);

    @Query("SELECT s FROM Shop s JOIN s.warehouses w WHERE w.id = :warehouseId")
    List<Shop> findByWarehouseId(@Param("warehouseId") Long warehouseId);

    boolean existsByName(String name);
}