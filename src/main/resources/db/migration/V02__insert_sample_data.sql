INSERT INTO warehouse (name) VALUES ('Main Warehouse'), ('Secondary Warehouse'), ('Distribution Center');
INSERT INTO shop (name) VALUES ('Downtown Store'), ('Mall Branch'), ('Online Store');
INSERT INTO shop_warehouse (shop_id, warehouse_id) VALUES
    (1, 1), (1, 2),
    (2, 1), (2, 3),
    (3, 1), (3, 2), (3, 3);
INSERT INTO product (name, price, quantity, warehouse_id, shop_id) VALUES
    ('Laptop', 999.99, 10, 1, 1),
    ('Mouse', 29.99, 50, 1, 2),
    ('Keyboard', 79.99, 25, 2, 3);