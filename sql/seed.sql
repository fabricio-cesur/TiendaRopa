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
CREATE TABLE Empleados (
    idEmpleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    dni VARCHAR(20) UNIQUE,
    cargo VARCHAR(100),
    salario DECIMAL(10, 2),
    telefono VARCHAR(20),
    email VARCHAR(100)
); 
CREATE TABLE Descuentos (
    idDescuento INT AUTO_INCREMENT PRIMARY KEY,
    descripcion TEXT,
    porcentajeFija BOOLEAN NOT NULL,
    porcentaje DECIMAL(5, 2),
    cantidadFija DECIMAL(10, 2)
);
CREATE TABLE Inventarios (
    idInventario INT AUTO_INCREMENT PRIMARY KEY
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
CREATE TABLE InventarioProducto (
    idInventarioProducto INT AUTO_INCREMENT PRIMARY KEY,
    idInventario INT,
    idProducto INT,
    ubicacion VARCHAR(100) NULL,
    cantidad INT UNSIGNED NOT NULL,
    fechaUltimaActualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (idInventario) REFERENCES Inventarios(idInventario),
    FOREIGN KEY (idProducto) REFERENCES Productos(idProducto)
);
CREATE TABLE Carrito (
    idCarrito INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT,
    idProducto INT,
    cantidad INT,
    FOREIGN KEY (idCliente) REFERENCES Clientes(idCliente),
    FOREIGN KEY (idProducto) REFERENCES Productos(idProducto)
)