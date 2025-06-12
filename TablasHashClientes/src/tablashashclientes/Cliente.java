/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablashashclientes;

/**
 *
 * @author ASUS
 */
public class Cliente {
    private int codigo;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
    private String direccion;
    private String codigoPostal;

    public Cliente(int codigo, String nombres, String apellidos, String telefono, 
                  String correo, String direccion, String codigoPostal) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
    }

    // Getters y Setters
    public int getCodigo() { return codigo; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }
    public String getDireccion() { return direccion; }
    public String getCodigoPostal() { return codigoPostal; }

    @Override
    public String toString() {
        return "Código: " + codigo + 
               "\nNombres: " + nombres + 
               "\nApellidos: " + apellidos + 
               "\nTeléfono: " + telefono + 
               "\nCorreo: " + correo + 
               "\nDirección: " + direccion + 
               "\nCódigo Postal: " + codigoPostal;
    }
}