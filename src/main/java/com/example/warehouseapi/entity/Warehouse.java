package com.example.warehouseapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Warehouse name is required")
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "warehouses")
    private List<Shop> shops = new ArrayList<>();

    @OneToMany(mappedBy = "warehouse")
    private List<Product> products = new ArrayList<>();

    public Warehouse() {
    }

    public Warehouse(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}