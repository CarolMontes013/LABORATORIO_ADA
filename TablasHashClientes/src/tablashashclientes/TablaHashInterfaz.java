/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablashashclientes;

/**
 *
 * @author ASUS
 */
public interface TablaHashInterfaz {
    void insertar(Cliente cliente);
    Cliente buscar(String nombres, String apellidos);
    void eliminar(String nombres, String apellidos);
    void mostrarTabla();
    long getTiempoInsercion();
    long getTiempoBusqueda();
}