package com.example.warehouseapi.repository;

import com.example.warehouseapi.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    Optional<Warehouse> findByName(String name);

    @Query("SELECT w FROM Warehouse w JOIN w.shops s WHERE s.id = :shopId")
    List<Warehouse> findByShopId(@Param("shopId") Long shopId);

    boolean existsByName(String name);
}
