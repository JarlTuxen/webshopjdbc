CREATE DATABASE IF NOT EXISTS webshop;
USE webshop;
DROP TABLE IF EXISTS product;
CREATE TABLE `product` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `name` varchar(255) DEFAULT NULL,
                           `price` int NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO product (name, price) VALUES
                                      ("Kaffe", 40),
                                      ("Mælk", 50),
                                      ("Te", 30);
