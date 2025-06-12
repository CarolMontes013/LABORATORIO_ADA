/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablashashclientes;

/**
 *
 * @author ASUS
 */
import java.util.TreeMap;

public class TablaHashArbol implements TablaHashInterfaz {
    private TreeMap<Integer, Cliente>[] tabla;
    private int tamaño;
    private long tiempoInsercion;
    private long tiempoBusqueda;

    public TablaHashArbol(int tamaño) {
        this.tamaño = tamaño;
        tabla = new TreeMap[tamaño];
        for (int i = 0; i < tamaño; i++) {
            tabla[i] = new TreeMap<>();
        }
        tiempoInsercion = 0;
        tiempoBusqueda = 0;
    }

    private int funcionHash(String nombres, String apellidos) {
        String clave = nombres + apellidos;
        int hash = 0;
        for (int i = 0; i < clave.length(); i++) {
            hash = (31 * hash + clave.charAt(i)) % tamaño;
        }
        return hash;
    }

    @Override
    public void insertar(Cliente cliente) {
        long inicio = System.nanoTime();
        int indice = funcionHash(cliente.getNombres(), cliente.getApellidos());
        int codigoHash = cliente.getCodigo();
        
        tabla[indice].put(codigoHash, cliente);
        tiempoInsercion = System.nanoTime() - inicio;
    }

    @Override
    public Cliente buscar(String nombres, String apellidos) {
        long inicio = System.nanoTime();
        int indice = funcionHash(nombres, apellidos);
        
        for (Cliente cliente : tabla[indice].values()) {
            if (cliente.getNombres().equals(nombres) && 
                cliente.getApellidos().equals(apellidos)) {
                tiempoBusqueda = System.nanoTime() - inicio;
                return cliente;
            }
        }
        
        tiempoBusqueda = System.nanoTime() - inicio;
        return null;
    }

    @Override
    public void eliminar(String nombres, String apellidos) {
        int indice = funcionHash(nombres, apellidos);
        
        for (Integer codigo : tabla[indice].keySet()) {
            Cliente cliente = tabla[indice].get(codigo);
            if (cliente.getNombres().equals(nombres) && 
                cliente.getApellidos().equals(apellidos)) {
                tabla[indice].remove(codigo);
                return;
            }
        }
    }

    @Override
    public void mostrarTabla() {
        for (int i = 0; i < tamaño; i++) {
            System.out.print("Posición " + i + ": ");
            if (!tabla[i].isEmpty()) {
                System.out.println("Árbol con " + tabla[i].size() + " elementos");
            } else {
                System.out.println("Vacío");
            }
        }
    }

    @Override
    public long getTiempoInsercion() {
        return tiempoInsercion;
    }

    @Override
    public long getTiempoBusqueda() {
        return tiempoBusqueda;
    }
}