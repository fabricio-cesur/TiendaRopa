# TiendaRopa

**TiendaRopa** es un sistema de gestión para una tienda de ropa. Este proyecto permite gestionar usuarios, productos, pedidos y descuentos, proporcionando funcionalidades tanto para usuarios como para administradores.

## Características

- **Gestión de usuarios**:
  - Registro de nuevos usuarios.
  - Inicio de sesión con roles (administrador y cliente).
  - Modificación de datos personales.
  - Eliminación de cuentas.

- **Gestión de productos**:
  - Listado de productos por categoría, marca, precio, talla y color.
  - Modificación de productos (nombre, descripción, precio, stock, etc.).
  - Eliminación de productos.
  - Restock de inventario.

- **Gestión de pedidos**:
  - Creación de nuevos pedidos.
  - Cancelación de pedidos.
  - Listado de pedidos realizados.

- **Gestión de descuentos**:
  - Visualización de descuentos disponibles.
  - Creación, modificación y eliminación de descuentos (solo para administradores).
  - Activación y desactivación de descuentos.

## Requisitos

- **Java**: JDK 17 o superior.
- **MySQL**: Servidor de base de datos.
- **Conector MySQL**: `mysql-connector-j-9.2.0.jar` (incluido en la carpeta `lib`).
- **Sistema operativo**: Windows (compatible con el archivo `run.bat`).

## Estructura del proyecto

```
TiendaRopa/
├── .gitignore
├── README.md
├── run.bat
├── variables.env
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

## Configuración

### 1. Base de datos
1. Crea una base de datos en MySQL llamada `tienda`.
2. Ejecuta el script `sql/seed.sql` para crear las tablas necesarias.
3. (Opcional) Usa `sql/insert.sql` para insertar datos iniciales.

### 2. Variables de entorno
Configura las credenciales de la base de datos y la aplicación en el archivo `variables.env`:

```env
# Credenciales de la base de datos
DB_URL=jdbc:mysql://localhost:3306/tienda
DB_USER=tu_usuario
DB_PASSWORD=tu_contraseña

# Credenciales de la aplicación
APP_USER=admin
APP_PASSWORD=admin123
```

### 3. Dependencias
Asegúrate de que el archivo `mysql-connector-j-9.2.0.jar` esté en la carpeta `lib`.

### 4. Compilación y ejecución
Usa el archivo `run.bat` para compilar y ejecutar el proyecto:

```bat
REM Compilar el proyecto
javac -d bin -cp lib/mysql-connector-j-9.2.0.jar ./src/*.java ./src/dao/*.java ./src/view/*.java ./src/model/*.java

REM Ejecutar la aplicación
java -cp "bin;lib/mysql-connector-j-9.2.0.jar" App
```

## Uso

### Inicio de sesión
1. Ejecuta la aplicación.
2. Selecciona la opción "Iniciar sesión".
3. Ingresa tus credenciales.

### Registro
1. Selecciona la opción "Registrarse".
2. Completa los datos solicitados.

### Funcionalidades principales
- **Usuarios**: Gestiona tus datos personales o accede al menú de administración si tienes permisos.
- **Productos**: Visualiza, modifica o elimina productos.
- **Pedidos**: Realiza nuevos pedidos o cancela los existentes.
- **Descuentos**: Visualiza descuentos disponibles o gestiona descuentos (administradores).

## Contribución

- **cnywu0** - [GitHub](https://github.com/cnywu0)
- **Joss33** - [GitHub](https://github.com/Josegallego33)
- **ManuelEgea** - [GitHub](https://github.com/ManuelEgea)
- **fabricio-cesur** - [GitHub](https://github.com/fabricio-cesur)
- **aburgos11** - [GitHub](https://github.com/aburgos11)

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.

