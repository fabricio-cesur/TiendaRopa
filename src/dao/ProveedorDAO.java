package dao;

import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
    private List<String[]> proveedor = new ArrayList<>();
    private int ultimoId = 0;

    public boolean crearProveedor(String[] datosProveedor) {
        try {
            String[] proveedorConId = new String[7];
            proveedorConId[0] = String.valueOf(++ultimoId);
            System.arraycopy(datosProveedor, 0, proveedorConId, 1, datosProveedor.length);
            proveedor.add(proveedorConId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String[] buscarPorNombre(String nombre) {
        for (String[] prov : proveedor) {
            if (prov[1].equalsIgnoreCase(nombre)) { 
                return prov;
            }
        }
        return null;
    }

    public List<String[]> obtenerTodos() {
        return new ArrayList<>(proveedor);
    }

    public boolean actualizarProveedor(String nombre, String[] nuevosDatos) {
        for (String[] prov : proveedor) {
            if (prov[1].equalsIgnoreCase(nombre)) {
                System.arraycopy(nuevosDatos, 0, prov, 1, nuevosDatos.length);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarProveedor(String nombre) {
        return proveedor.removeIf(prov -> prov[1].equalsIgnoreCase(nombre));
    }

    public String[] buscarPorId(int id) {
        for (String[] prov : proveedor) {
            if (prov[0].equals(String.valueOf(id))) {
                return prov;
            }
        }
        return null;
    }
}