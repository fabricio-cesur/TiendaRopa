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

CREATE TABLE Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    admin BOOLEAN NOT NULL
);

CREATE TABLE DetallePedido (
    idDetallePedido INT AUTO_INCREMENT PRIMARY KEY,
    idPedido INT,
    idProducto INT,
    cantidad INT UNSIGNED NOT NULL,
    precioUnitario DECIMAL(10, 2) NOT NULL,
    descuentoAplicado DECIMAL(10, 2) NULL,  -- Cambio aquí si se requiere un descuento fijo en cantidad
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (idPedido) REFERENCES Pedidos(idPedido),  -- Corregido "Pedido" a "Pedidos"
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
CREATE TABLE ComprasInventario (
    idCompra INT AUTO_INCREMENT PRIMARY KEY,
    idProducto INT,
    cantidad INT NOT NULL,
    precioCompra DECIMAL(10, 2) NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idProducto) REFERENCES Productos(idProducto)
);
 CREATE TABLE Cuentas (
    idCuenta INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('ingreso', 'gasto') NOT NULL,
    descripcion VARCHAR(255),
    cantidad DECIMAL(10,2) NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    idPedido INT NULL,
    idCompra INT NULL,
    FOREIGN KEY (idPedido) REFERENCES Pedidos(idPedido),
    FOREIGN KEY (idCompra) REFERENCES ComprasInventario(idCompra)
);





/*Vsita para obtener los productos mas rentables*/
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `tienda`@`%` 
    SQL SECURITY DEFINER
VIEW `ProductoMasRentable` AS
    SELECT 
        `p`.`idProducto` AS `idProducto`,
        `p`.`nombre` AS `nombre`,
        SUM(`dp`.`cantidad` * `dp`.`precioUnitario`) AS `total_revenue`
    FROM
        (`Productos` `p`
        JOIN `DetallePedido` `dp` ON (`p`.`idProducto` = `dp`.`idProducto`))
    GROUP BY `p`.`idProducto` , `p`.`nombre`
    ORDER BY SUM(`dp`.`cantidad` * `dp`.`precioUnitario`) DESC
    


/*Procedimiento para redicir stock*/
DELIMITER $$

CREATE DEFINER=`tienda`@`%` PROCEDURE `ReducirStockVenta`(
    IN p_idProducto INT,
    IN p_cantidadVendida INT
)
BEGIN
    DECLARE stockActual INT;

    -- Iniciar la transacción
    START TRANSACTION;

    -- Verificar si la cantidad vendida es válida
    IF p_cantidadVendida <= 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La cantidad vendida debe ser mayor que 0';
    END IF;

    -- Obtener el stock actual del producto
    SELECT stock INTO stockActual
    FROM Productos
    WHERE idProducto = p_idProducto;

    -- Verificar si el producto existe
    IF stockActual IS NULL THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45001'
            SET MESSAGE_TEXT = 'El producto no existe';
    END IF;

    -- Verificar si el stock disponible es suficiente
    IF stockActual < p_cantidadVendida THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Cantidad vendida excede el stock disponible';
    ELSE
        -- Reducir el stock del producto
        UPDATE Productos
        SET stock = stock - p_cantidadVendida
        WHERE idProducto = p_idProducto;
    END IF;

    -- Si todo salió bien, confirmar la transacción
    COMMIT;

END

END$$

DELIMITER ;



/*Trigger para guaradar los productos eliminados*/
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

/*Trigger para guaradar los pedidos eliminados*/
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

/*Trigger para guaradar los clientes eliminados*/
CREATE TABLE ClientesLog (
    idCliente INT,
    nombre VARCHAR(255),
    apellido VARCHAR(255),
    email VARCHAR(255),
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    fechaEliminacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
DROP TRIGGER IF EXISTS tienda.Clientes_BEFORE_DELETE;

DELIMITER $$
USE tienda$$
CREATE DEFINER=`tienda`@`%` TRIGGER `tienda`.`Clientes_BEFORE_DELETE` BEFORE DELETE ON `Clientes` FOR EACH ROW
BEGIN
INSERT INTO ClientesLog (
        idCliente, nombre, apellido, email,
        direccion, telefono
    ) VALUES (
        OLD.idCliente, OLD.nombre, OLD.apellido, OLD.email,
        OLD.direccion, OLD.telefono
    );
END$$
DELIMITER ;
/*Trigger para guaradar los usuarios eliminados*/
CREATE TABLE UsuariosLog (
    id INT,
    username VARCHAR(255),
    password VARCHAR(255),
    admin BOOLEAN,
    fechaEliminacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
DROP TRIGGER IF EXISTS `tienda`.`Usuarios_BEFORE_DELETE`;

DELIMITER $$
USE `tienda`$$
CREATE DEFINER = CURRENT_USER TRIGGER `tienda`.`Usuarios_BEFORE_DELETE` BEFORE DELETE ON `Usuarios` FOR EACH ROW
BEGIN
 INSERT INTO UsuariosLog (id, username, password, admin)
    VALUES (OLD.id, OLD.username, OLD.password, OLD.admin);
END$$
DELIMITER 

CREATE TABLE CuentasLog (
    idCuenta INT,
    descripcion VARCHAR(255),
    cantidad DECIMAL(10,2),
    idPedido INT,
    fechaEliminacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
DROP TRIGGER IF EXISTS tienda.Cuentas_BEFORE_DELETE;

DELIMITER $$
USE tienda$$
CREATE DEFINER=`tienda`@`%` TRIGGER `tienda`.`Cuentas_BEFORE_DELETE` BEFORE DELETE ON `Cuentas` FOR EACH ROW
BEGIN
    INSERT INTO CuentasLog (
        idCuenta, descripcion, cantidad, idPedido
    )
    VALUES (
        OLD.idCuenta, OLD.descripcion, OLD.cantidad, OLD.idPedido
    );
END$$
DELIMITER 