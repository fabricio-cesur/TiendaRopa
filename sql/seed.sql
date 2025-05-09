CREATE TABLE Productos (
    idProducto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT UNSIGNED NOT NULL,
    talla VARCHAR(50),
    color VARCHAR(50),
    marca VARCHAR(100) NOT NULL,
    categoria VARCHAR(100) NOT NULL
);
CREATE TABLE Clientes (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    direccion VARCHAR(255),
    telefono VARCHAR(20)
);

CREATE TABLE Descuentos (
    idDescuento INT AUTO_INCREMENT PRIMARY KEY,
    descripcion TEXT,
    porcentajeFija BOOLEAN NOT NULL,
    porcentaje DECIMAL(5, 2),
    cantidadFija DECIMAL(10, 2)
);

CREATE TABLE Pedidos (
    idPedido INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT,
    fechaPedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fechaEntrega DATE,
    estado BOOLEAN NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    direccion VARCHAR(255),
    FOREIGN KEY (idCliente) REFERENCES Clientes(idCliente)
);
CREATE TABLE User (
    idUser INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    admin BOOLEAN NOT NULL
);
CREATE TABLE Cajas (
    idCaja INT AUTO_INCREMENT PRIMARY KEY,
    fechaCierre DATE UNIQUE,
    saldoInicial DECIMAL(10, 2),
    gastos DECIMAL(10, 2),
    saldoTotal DECIMAL(10, 2)
);
CREATE TABLE Proveedores (
    idProveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre_empresa VARCHAR(255) NOT NULL,
    cif VARCHAR(20) UNIQUE,
    direccion VARCHAR(255),
    telefonoContacto VARCHAR(20),
    emailContacto VARCHAR(100)
);
CREATE TABLE DetallePedido (
    idDetallePedido INT AUTO_INCREMENT PRIMARY KEY,
    idPedido INT,
    idProducto INT,
    cantidad INT UNSIGNED NOT NULL,
    precioUnitario DECIMAL(10, 2) NOT NULL,
    descuentoAplicado DECIMAL(5, 2) NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (idPedido) REFERENCES Pedido(idPedido),
    FOREIGN KEY (idProducto) REFERENCES Productos(idProducto)
);

CREATE TABLE Carrito (
    idCarrito INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT,
    idProducto INT,
    cantidad INT,
    FOREIGN KEY (idCliente) REFERENCES Clientes(idCliente),
    FOREIGN KEY (idProducto) REFERENCES Productos(idProducto)
);
    CREATE TABLE Cuentas (
    idCuenta INT AUTO_INCREMENT PRIMARY KEY,
    tipoTransaccion ENUM('INGRESO', 'GASTO') NOT NULL,
    Cantidad DECIMAL(10, 2) NOT NULL,
    fechaTransaccion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    descripcion VARCHAR(255),
    idPedido INT, 
    FOREIGN KEY (idPedido) REFERENCES Pedidos(idPedido)
);

/**/
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `tienda`@`%` 
    SQL SECURITY DEFINER
VIEW `ProductosMasRentables` AS
    SELECT 
        `p`.`idProducto` AS `idProducto`,
        `p`.`nombre` AS `nombre`,
        SUM(`dp`.`cantidad` * `dp`.`precioUnitario`) AS `total_revenue`
    FROM
        (`Productos` `p`
        JOIN `DetallePedido` `dp` ON (`p`.`idProducto` = `dp`.`idProducto`))
    GROUP BY `p`.`idProducto` , `p`.`nombre`
    ORDER BY SUM(`dp`.`cantidad` * `dp`.`precioUnitario`) DESC





CREATE TABLE LogProductos (
    idProducto INT,
    nombre VARCHAR(255),
    descripcion TEXT,
    precio DECIMAL(10, 2),
    stock INT UNSIGNED,
    talla VARCHAR(50),
    color VARCHAR(50),
    marca VARCHAR(100),
    categoria VARCHAR(100),
    fechaEliminacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE DEFINER=`tienda`@`%` TRIGGER `tienda`.`logProductos` BEFORE DELETE ON `Productos` FOR EACH ROW
BEGIN
INSERT INTO LogProductos (idProducto, nombre, descripcion, precio, stock, talla, color, marca, categoria)
    VALUES (OLD.idProducto, OLD.nombre, OLD.descripcion, OLD.precio, OLD.stock, OLD.talla, OLD.color, OLD.marca, OLD.categoria);
END


CREATE TABLE PedidosLog (
    idPedido INT,
    idCliente INT,
    fechaPedido TIMESTAMP,
    fechaEntrega DATE,
    estado BOOLEAN,
    total DECIMAL(10, 2),
    direccion VARCHAR(255),
    fechaEliminacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE DEFINER=`tienda`@`%` TRIGGER `tienda`.`Pedidos_BEFORE_DELETE` BEFORE DELETE ON `Pedidos` FOR EACH ROW
BEGIN
INSERT INTO PedidosLog (idPedido, idCliente, fechaPedido, fechaEntrega, estado, total, direccion)
VALUES (OLD.idPedido, OLD.idCliente, OLD.fechaPedido, OLD.fechaEntrega, OLD.estado, OLD.total, OLD.direccion);
END