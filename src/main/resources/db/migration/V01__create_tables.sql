CREATE TABLE warehouse (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE shop (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE product (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price NUMERIC(10,2) NOT NULL CHECK (price >= 0),
    quantity INTEGER NOT NULL CHECK (quantity >= 0),
    warehouse_id BIGINT NOT NULL REFERENCES warehouse(id) ON DELETE CASCADE,
    shop_id BIGINT NOT NULL REFERENCES shop(id) ON DELETE CASCADE
);

CREATE TABLE shop_warehouse (
    shop_id BIGINT NOT NULL REFERENCES shop(id) ON DELETE CASCADE,
    warehouse_id BIGINT NOT NULL REFERENCES warehouse(id) ON DELETE CASCADE,
    PRIMARY KEY (shop_id, warehouse_id)
);

CREATE INDEX idx_product_warehouse_id ON product(warehouse_id);
CREATE INDEX idx_product_shop_id ON product(shop_id);
CREATE INDEX idx_shop_warehouse_shop_id ON shop_warehouse(shop_id);
CREATE INDEX idx_shop_warehouse_warehouse_id ON shop_warehouse(warehouse_id);