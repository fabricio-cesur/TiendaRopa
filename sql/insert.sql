INSERT INTO Productos (tipo, nombre, marca, talla, precio, descripcion, fecha_creacion) VALUES
('Camiseta', 'Oversize', 'Nike', 'M', 24.99, 'Camiseta básica de lino blanco', '2023-01-15'),
('Pantalón', 'Slim Fit', 'Adidas', '35', 59.99, 'Pantalón vaquero slim fit azul', '2023-02-10'),
('Zapatillas', 'Retro 4', 'Jordan', '42', 89.99, 'Zapatillas deportivas', '2023-03-05'),
('Chaqueta', 'Ancha', 'North Face', 'L', 129.99, 'Chaqueta ancha impermeable', '2023-01-20'),
('Vestido', 'Largo', 'Zara', 'S', 39.99, 'Vestido floral para verano', '2023-04-12');

INSERT INTO Clientes (nombre, apellidos, email, telefono, direccion, ciudad, codigo_postal, fecha_registro) VALUES
('Carlos', 'Morlans López', 'carlosmorlans@email.com', '612345678', 'Avenida de la Ilustración 10', 'Madrid', '28013', '2023-01-10'),
('Marcos', 'Blanquez Ruiz', 'marcosblanquez@email.com', '623456789', 'Calle los Pajaros 5', 'Barcelona', '08028', '2023-02-15'),
('Daniel', 'Saz Sánchez', 'danielsaz@email.com', '634567890', 'Plaza España 3', 'Sevilla', '41013', '2023-03-20'),
('Yago', 'Celma Gómez', 'yagocelma@email.com', '645678901', 'Gran Vía 22', 'Valencia', '46005', '2023-04-05'),
('Eder', 'Moros Díaz', 'edermoros@email.com', '656789012', 'Calle Canelón 8', 'Sevilla', '41004', '2023-05-12');

INSERT INTO Empleados (nombre, apellidos, email, telefono, puesto, salario) VALUES
('Fabricio', 'Oliva', 'fabricioliva@tienda.com', '611223344', 'Programador', 2800.00),
('Nicolás', 'Blasco', 'nicolasblasco@tienda.com', '622334455', 'Programador', 1800.00),
('José', 'Rubén Gallego', 'joseruben@tienda.com', '633445566', 'Programador', 1600.00),
('Manuel', 'Egea', 'manuelegea@tienda.com', '644556677', 'CEO', 1700.00),
('Álvaro', 'Burgos', 'alvaroburgos@tienda.com', '655667788', 'Programador', 2100.00);

INSERT INTO Descuentos (codigo, porcentaje, fecha_inicio, fecha_fin, max_usos, usos_actuales) VALUES
('Verano', 20.00, '2025-06-01', '2025-08-31', 100, 45),
('Navidad', 15.00, '2025-25-12', '2026-01-06', 200, 132),
('Seamana Santa', 10.00, '2025-13-04', '2025-20-04', 500, 287),
('Black Friday', 30.00, '2025-28-11', '2025-28-11', 300, 0),
('Halloween', 25.00, '2025-31-10', '2025-01-11', 50, 12);

INSERT INTO Proveedores (nombre, contacto, email, telefono, direccion, ciudad, codigo_postal) VALUES
('Textiles del Norte', 'Marcos José Alegre', 'marcosjose@textilesnorte.com', '912345678', 'Polígono Industrial Norte 12', 'Burgos', '09007'),
('Moda Sur', 'Samuel Doval', 'samueldoval@modasur.es', '954321987', 'Calle Industrial 45', 'Sevilla', '41015'),
('Distribuciones Este', 'Carlos Delgado', 'carlosdelgado@disteste.com', '963852741', 'Avenida del Puerto 78', 'Valencia', '46024'),
('Fabricantes Oeste', 'Pablo Gargallo', 'pablogargallo@fabricantesoeste.com', '987654321', 'Calle Mayor 56', 'La Coruña', '15003'),
('Importaciones Moda', 'Javier Beltrán', 'javierbeltran@importmoda.com', '936985214', 'Polígono Industrial Este 33', 'Barcelona', '08040');

INSERT INTO Inventarios (nombre, ubicacion) VALUES
('Almacén Central', 'Madrid'),
('Almacén Norte', 'Burgos'),
('Almacén Sur', 'Sevilla'),
('Exposición Tienda', 'Madrid'),
('Outlet Online', 'Barcelona');

INSERT INTO InventarioProducto (inventario_id, producto_id, cantidad) VALUES
('1', '8578', 50),
('2', '4596', 30),
('3', '1245', 20),
('4', '7452', 15),
('5', '9658', 40),
('6', '4563', 25),
('7', '3214', 20),
('8', '5896', 15),
('9', '5874', 10),
('10', '4125', 8),
('11', '5236', 5),
('12', '2365', 3);

INSERT INTO User (username, password, rol) VALUES
('admin', 'admin123', 'admin'),
('vendedor1', 'vendedor123', 'vendedor'),
('almacen', 'almacen123', 'almacen',NULL),
('atencion', 'atencion123', 'atencion_cliente'),
('maria_g', 'maria123', 'cliente'),
('carlos_m', 'carlos123', 'cliente'),
('ana_r', 'ana123', 'cliente'),
('david_f', 'david123', 'cliente'),
('laura_p', 'laura123', 'cliente');

INSERT INTO Pedidos (cliente_id, fecha_pedido, estado, direccion_envio, ciudad_envio, codigo_postal_envio, metodo_pago, descuento_id) VALUES
('CLI001', '2025-02-15 10:30:00', 'Entregado', 'Calle Mayor 10', 'Madrid', '28013', 'Visa', 'DESC003'),
('CLI002', '2025-03-20 14:45:00', 'Enviado', 'Avenida Diagonal 45', 'Barcelona', '08028', 'PayPal', NULL),
('CLI003', '2025-04-05 09:15:00', 'Procesando', 'Plaza España 3', 'Sevilla', '41013', 'Transferencia', 'DESC001'),
('CLI004', '2025-05-10 16:20:00', 'Pendiente', 'Calle Mayor 10', 'Madrid', '28013', 'Tarjeta', NULL),
('CLI005', '2025-06-15 11:30:00', 'Enviado', 'Gran Vía 22', 'Valencia', '46005', 'Bizum', 'DESC002');

INSERT INTO DetallePedido (pedido_id, producto_id, cantidad, precio_unitario) VALUES
('PROD001', 2, 24.99),
('PROD002', 1, 59.99),
('PROD003', 1, 89.99),
('PROD001', 3, 24.99),
('PROD005', 1, 39.99),
('PROD004', 1, 129.99),
('PROD002', 2, 59.99),
('PROD003', 1, 89.99);

INSERT INTO Carrito (id, cliente_id, fecha_creacion, fecha_actualizacion) VALUES
('CART001', 'CLI002', '2023-06-20 09:00:00', '2023-06-20 09:00:00'),
('CART002', 'CLI003', '2023-06-21 14:30:00', '2023-06-22 10:15:00'),
('CART003', 'CLI005', '2023-06-22 16:45:00', '2023-06-22 16:45:00');

INSERT INTO Carrito_Producto (carrito_id, producto_id, cantidad) VALUES
('CART001', 'PROD001', 1),
('CART001', 'PROD003', 1),
('CART002', 'PROD002', 2),
('CART003', 'PROD005', 1),
('CART003', 'PROD001', 1),
('CART003', 'PROD004', 1);

INSERT INTO Cajas (id, nombre, ubicacion, responsable_id, estado) VALUES
('CAJA001', 'Caja Principal', 'Tienda Madrid', 'EMP002', 'Activa'),
('CAJA002', 'Caja Secundaria', 'Tienda Madrid', 'EMP004', 'Activa'),
('CAJA003', 'Caja Online', 'Web', NULL, 'Activa'),
('CAJA004', 'Caja Outlet', 'Tienda Barcelona', NULL, 'Inactiva');

INSERT INTO LogProductos (id, producto_id, usuario_id, accion, fecha, detalles) VALUES
('LOG001', 'PROD001', 'USER002', 'Actualización', '2023-02-01 11:20:00', 'Cambio de precio de 22.99 a 24.99'),
('LOG002', 'PROD003', 'USER001', 'Creación', '2023-03-05 09:00:00', 'Nuevo producto creado'),
('LOG003', 'PROD002', 'USER003', 'Stock', '2023-04-10 16:30:00', 'Añadidas 10 unidades al inventario'),
('LOG004', 'PROD005', 'USER002', 'Venta', '2023-05-15 12:45:00', 'Vendida 1 unidad'),
('LOG005', 'PROD004', 'USER001', 'Descuento', '2023-06-01 10:15:00', 'Aplicado descuento especial del 15%');