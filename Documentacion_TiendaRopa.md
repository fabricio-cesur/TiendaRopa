
# 👚 TiendaRopa - Sistema de Gestión para Tienda de Ropa

**TiendaRopa** es un sistema de consola que permite gestionar usuarios, productos, pedidos, descuentos y el balance financiero de una tienda de ropa. Diseñado tanto para administradores como clientes.

---

## 📁 Estructura del Proyecto

```
TiendaRopa/
├── .gitignore
├── README.md
├── run.bat
├── Actas.pdf
├── .vscode/
│   └── settings.json
├── lib/
│   └── mysql-connector-j-9.2.0.jar
├── sql/
│   ├── insert.sql
│   └── seed.sql
├── src/
│   ├── App.java
│   ├── dao/
│   │   ├── ClienteDAO.java
│   │   ├── ConexionDB.java
│   │   ├── DescuentoDAO.java
│   │   ├── PedidoDAO.java
│   │   ├── ProductoDAO.java
│   │   └── UserDAO.java
│   ├── model/
│   │   ├── Cliente.java
│   │   ├── Descuento.java
│   │   ├── Pedido.java
│   │   ├── Producto.java
│   │   └── User.java
│   ├── utils/
│   │   └── EnvLoader.java
│   └── view/
│       ├── AdminView.java
│       ├── DescuentoView.java
│       ├── PedidoView.java
│       ├── ProductoView.java
│       └── UserView.java
```

---

## 🧩 Arquitectura del Sistema

- `model/`: Clases que representan los datos como Usuario, Producto, Pedido, etc.
- `dao/`: Acceso a datos y operaciones CRUD.
- `view/`: Interfaz de usuario en consola (menús).
- `utils/`: Utilidades para configuración y colores.
- `App.java`: Punto de entrada del programa.

---

## 🎯 Características Principales

### 👤 Gestión de Usuarios
- Registro con balance inicial de 500.
- Inicio de sesión con roles.
- Modificación y eliminación de cuenta.
- Visualización del balance.

### 👕 Gestión de Productos
- Filtros por categoría, marca, precio, talla y color.
- Modificación y eliminación de productos.
- Restock con descuento del 20% para administradores.
- Reposición automática de 40 unidades si el stock es menor a 5.

### 📦 Gestión de Pedidos
- Crear, modificar y cancelar pedidos.
- Visualización de historial de pedidos.

### 🎁 Gestión de Descuentos
- Ver descuentos activos.
- Crear, modificar, activar/desactivar descuentos (admin).

### 💰 Gestión Financiera (Banca)
- Balance inicial de la empresa: 45 millones.
- Registro de ingresos por ventas y egresos por restock.
- Transacciones automáticas actualizan balances.

---

## ⚙️ Requisitos

- Java JDK 17 o superior
- MySQL Server
- Conector MySQL: `lib/mysql-connector-j-9.2.0.jar`
- Windows (recomendado para ejecutar `run.bat`)

---

## 🛠️ Configuración

### 1️⃣ Base de Datos
1. Crear base de datos `tienda` en MySQL.
2. Ejecutar `sql/seed.sql`.
3. (Opcional) Ejecutar `sql/insert.sql`.

### 2️⃣ Variables de Entorno

Crear el archivo `variables.env`:

```env
DB_URL=jdbc:mysql://localhost:3306/tienda
DB_USER=tu_usuario
DB_PASSWORD=tu_contraseña

APP_USER=admin
APP_PASSWORD=admin123

COLOR_RESET=\u001B[0m
COLOR_SUCCESS=\u001B[32m
COLOR_WARNING=\u001B[33m
COLOR_ERROR=\u001B[31m
COLOR_INFO=\u001B[36m
```

Usado por la clase `EnvLoader` para conectar con la base de datos y mostrar colores en consola.

---

## 🧪 Flujo de Uso

### 🔐 Inicio de Sesión
Administrador accede con: `admin/admin123`.

### 🗃️ Inventario
Admin hace restock del producto ID 1 (50 unidades). El costo se descuenta del balance empresarial.

### 🛒 Compra
Usuario compra 2 unidades. Se descuenta de su saldo y se suma al balance de la empresa.

### 🎯 Descuento
Administrador crea un descuento del 10% para un grupo de productos.

---

## 🧵 Detalles Técnicos

- **Colores de consola**: Configurados vía `variables.env` y clase `ConsoleColors`.
- **Stock automático**: Se ejecuta `verificarStock()` si el stock es menor a 5.
- **Descuentos**: Tienen % y fechas de validez. Sólo el admin puede administrarlos.
- **Banca**: Clase que gestiona balance general y de usuarios.

---

## 🧠 Conceptos Reforzados

- Programación Orientada a Objetos
- JDBC y manejo de base de datos
- Gestión de consola interactiva
- Manejo de archivos de configuración (.env)

---

## 📜 Licencia

Este proyecto está bajo la **Licencia MIT**. Consulta el archivo `LICENSE` para más detalles.
