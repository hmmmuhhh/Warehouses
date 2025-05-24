package com.example.warehouseapi.repository;

import com.example.warehouseapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByShopId(Long shopId);

    List<Product> findByWarehouseId(Long warehouseId);

    @Query("SELECT p FROM Product p WHERE p.shopId = :shopId AND p.warehouseId = :warehouseId")
    List<Product> findByShopIdAndWarehouseId(@Param("shopId") Long shopId, @Param("warehouseId") Long warehouseId);
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> findByNameContaining(@Param("name") String name);
}