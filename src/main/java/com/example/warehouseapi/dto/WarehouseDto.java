package com.example.warehouseapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;

@Getter
public class WarehouseDto {

    private Long id;

    @NotBlank(message = "Warehouse name is required")
    private String name;

    private List<Long> shopIds;
    private List<String> shopNames;

    public WarehouseDto() {
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "WarehouseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shopIds=" + shopIds +
                ", shopNames=" + shopNames +
                '}';
    }
}