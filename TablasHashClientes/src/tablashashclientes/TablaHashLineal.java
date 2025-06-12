/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablashashclientes;

/**
 *
 * @author ASUS
 */
public class TablaHashLineal implements TablaHashInterfaz {
    private Cliente[] tabla;
    private int tamaño;
    private long tiempoInsercion;
    private long tiempoBusqueda;

    public TablaHashLineal(int tamaño) {
        this.tamaño = tamaño;
        tabla = new Cliente[tamaño];
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
        
        while (tabla[indice] != null) {
            indice = (indice + 1) % tamaño; // Reasignación lineal
        }
        
        tabla[indice] = cliente;
        tiempoInsercion = System.nanoTime() - inicio;
    }

    @Override
    public Cliente buscar(String nombres, String apellidos) {
        long inicio = System.nanoTime();
        int indice = funcionHash(nombres, apellidos);
        int intentos = 0;
        
        while (tabla[indice] != null && intentos < tamaño) {
            if (tabla[indice].getNombres().equals(nombres) && 
                tabla[indice].getApellidos().equals(apellidos)) {
                tiempoBusqueda = System.nanoTime() - inicio;
                return tabla[indice];
            }
            indice = (indice + 1) % tamaño;
            intentos++;
        }
        
        tiempoBusqueda = System.nanoTime() - inicio;
        return null;
    }

    @Override
    public void eliminar(String nombres, String apellidos) {
        int indice = funcionHash(nombres, apellidos);
        int intentos = 0;
        
        while (tabla[indice] != null && intentos < tamaño) {
            if (tabla[indice].getNombres().equals(nombres) && 
                tabla[indice].getApellidos().equals(apellidos)) {
                tabla[indice] = null;
                return;
            }
            indice = (indice + 1) % tamaño;
            intentos++;
        }
    }

    @Override
    public void mostrarTabla() {
        for (int i = 0; i < tamaño; i++) {
            System.out.print("Posición " + i + ": ");
            if (tabla[i] != null) {
                System.out.println(tabla[i].getNombres() + " " + tabla[i].getApellidos());
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