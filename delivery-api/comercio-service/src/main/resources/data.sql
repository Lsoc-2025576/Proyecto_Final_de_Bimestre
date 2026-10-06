INSERT INTO comercios (nombre, categoria, direccion, abierto) VALUES
('Pizza Hub', 'RESTAURANTE', 'Zona 4, Ciudad de Guatemala', true),
('Super Selectos', 'SUPERMERCADO', 'Zona 9, Ciudad de Guatemala', true);

INSERT INTO productos (nombre, precio, stock, disponible, comercio_id) VALUES
('Pizza Margarita Grande', 85.00, 10, true, 1),
('Pizza Pepperoni Grande', 95.00, 8, true, 1),
('Refresco Cola 2L', 18.00, 20, true, 1),
('Leche Entera 1L', 12.50, 15, true, 2),
('Pan Integral', 9.75, 5, true, 2),
('Producto Sin Stock', 50.00, 0, true, 2);
