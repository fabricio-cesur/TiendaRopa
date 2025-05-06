package dao;

import java.util.ArrayList;
import java.util.List;
import model.Producto;

public class ProductoDAO {
    private final List<Producto> productos;
    private int nextId;

    public ProductoDAO() {
        this.productos = new ArrayList<>();
        this.nextId = 1;
    }

    public void agregarProducto(Producto producto) {
        producto.setId("PROD-" + nextId++);
        productos.add(producto);
    }

    public Producto obtenerProductoPorId(String id) {
        for (Producto p : productos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public List<Producto> obtenerTodosProductos() {
        return new ArrayList<>(productos);
    }

    public List<Producto> obtenerProductosPorTipo(String tipo) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getTipo().equalsIgnoreCase(tipo)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public boolean actualizarProducto(Producto productoActualizado) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId().equals(productoActualizado.getId())) {
                productos.set(i, productoActualizado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarProducto(String id) {
        return productos.removeIf(p -> p.getId().equals(id));
    }

    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public List<Producto> buscarPorMarca(String marca) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getMarca().equalsIgnoreCase(marca)) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}