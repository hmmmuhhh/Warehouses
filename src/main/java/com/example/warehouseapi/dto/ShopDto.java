package com.example.warehouseapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;

@Getter
public class ShopDto {

    private Long id;

    @NotBlank(message = "Shop name is required")
    private String name;

    private List<Long> warehouseIds;
    private List<String> warehouseNames;

    public ShopDto() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWarehouseIds(List<Long> warehouseIds) {
        this.warehouseIds = warehouseIds;
    }

    @Override
    public String toString() {
        return "ShopDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", warehouseIds=" + warehouseIds +
                ", warehouseNames=" + warehouseNames +
                '}';
    }
}