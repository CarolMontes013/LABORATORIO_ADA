/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tablashashclientes;

/**
 *
 * @author ASUS
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazGrafica extends JFrame {
    private TablaHashInterfaz tablaLineal;
    private TablaHashInterfaz tablaArbol;
    private JTextArea resultadoArea;
    private JTextField codigoField, nombresField, apellidosField, telefonoField, 
                      correoField, direccionField, codigoPostalField;
    private JButton insertarBtn, buscarBtn, compararBtn;

    public InterfazGrafica() {
        setTitle("Gestión de Clientes con Tablas Hash");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inicializar tablas hash
        tablaLineal = new TablaHashLineal(100);
        tablaArbol = new TablaHashArbol(100);

        // Panel de entrada de datos
        JPanel entradaPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        entradaPanel.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));

        entradaPanel.add(new JLabel("Código:"));
        codigoField = new JTextField();
        entradaPanel.add(codigoField);

        entradaPanel.add(new JLabel("Nombres:"));
        nombresField = new JTextField();
        entradaPanel.add(nombresField);

        entradaPanel.add(new JLabel("Apellidos:"));
        apellidosField = new JTextField();
        entradaPanel.add(apellidosField);

        entradaPanel.add(new JLabel("Teléfono:"));
        telefonoField = new JTextField();
        entradaPanel.add(telefonoField);

        entradaPanel.add(new JLabel("Correo:"));
        correoField = new JTextField();
        entradaPanel.add(correoField);

        entradaPanel.add(new JLabel("Dirección:"));
        direccionField = new JTextField();
        entradaPanel.add(direccionField);

        entradaPanel.add(new JLabel("Código Postal:"));
        codigoPostalField = new JTextField();
        entradaPanel.add(codigoPostalField);

        // Panel de botones
        JPanel botonesPanel = new JPanel(new FlowLayout());
        insertarBtn = new JButton("Insertar");
        buscarBtn = new JButton("Buscar");
        compararBtn = new JButton("Comparar Métodos");

        botonesPanel.add(insertarBtn);
        botonesPanel.add(buscarBtn);
        botonesPanel.add(compararBtn);

        // Área de resultados
        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        // Agregar componentes al frame
        add(entradaPanel, BorderLayout.NORTH);
        add(botonesPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Manejadores de eventos
        insertarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarCliente();
            }
        });

        buscarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCliente();
            }
        });

        compararBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compararMetodos();
            }
        });
    }

    private void insertarCliente() {
        try {
            int codigo = Integer.parseInt(codigoField.getText());
            String nombres = nombresField.getText();
            String apellidos = apellidosField.getText();
            String telefono = telefonoField.getText();
            String correo = correoField.getText();
            String direccion = direccionField.getText();
            String codigoPostal = codigoPostalField.getText();

            Cliente cliente = new Cliente(codigo, nombres, apellidos, telefono, correo, direccion, codigoPostal);
            
            tablaLineal.insertar(cliente);
            tablaArbol.insertar(cliente);
            
            resultadoArea.setText("Cliente insertado exitosamente en ambas tablas hash.\n");
            resultadoArea.append("Tiempo de inserción (Lineal): " + tablaLineal.getTiempoInsercion() + " ns\n");
            resultadoArea.append("Tiempo de inserción (Árbol): " + tablaArbol.getTiempoInsercion() + " ns");
            
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El código debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al insertar cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarCliente() {
        String nombres = nombresField.getText();
        String apellidos = apellidosField.getText();
        
        if (nombres.isEmpty() || apellidos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar nombres y apellidos para buscar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Cliente clienteLineal = tablaLineal.buscar(nombres, apellidos);
        Cliente clienteArbol = tablaArbol.buscar(nombres, apellidos);
        
        resultadoArea.setText("=== Resultados de Búsqueda ===\n");
        resultadoArea.append("Tiempo de búsqueda (Lineal): " + tablaLineal.getTiempoBusqueda() + " ns\n");
        resultadoArea.append("Tiempo de búsqueda (Árbol): " + tablaArbol.getTiempoBusqueda() + " ns\n\n");
        
        resultadoArea.append("=== Tabla Hash con Reasignación Lineal ===\n");
        if (clienteLineal != null) {
            resultadoArea.append(clienteLineal.toString());
        } else {
            resultadoArea.append("Cliente no encontrado");
        }
        
        resultadoArea.append("\n\n=== Tabla Hash con Árboles Binarios ===\n");
        if (clienteArbol != null) {
            resultadoArea.append(clienteArbol.toString());
        } else {
            resultadoArea.append("Cliente no encontrado");
        }
    }

    private void compararMetodos() {
        resultadoArea.setText("=== Comparación de Métodos de Resolución de Colisiones ===\n");
        resultadoArea.append("1. Reasignación Lineal:\n");
        resultadoArea.append("   - Complejidad promedio de inserción: O(1) en caso ideal, O(n) en peor caso\n");
        resultadoArea.append("   - Complejidad promedio de búsqueda: O(1) en caso ideal, O(n) en peor caso\n");
        resultadoArea.append("   - Ventajas: Simple implementación, buen rendimiento con factor de carga bajo\n");
        resultadoArea.append("   - Desventajas: Puede sufrir de agrupamiento primario, rendimiento degrada con alta ocupación\n\n");
        
        resultadoArea.append("2. Encadenamiento con Árboles Binarios:\n");
        resultadoArea.append("   - Complejidad promedio de inserción: O(1) para hash, O(log k) para inserción en árbol (k = elementos en cubo)\n");
        resultadoArea.append("   - Complejidad promedio de búsqueda: O(1) para hash, O(log k) para búsqueda en árbol\n");
        resultadoArea.append("   - Ventajas: Mejor rendimiento con alta ocupación, no sufre de agrupamiento\n");
        resultadoArea.append("   - Desventajas: Mayor sobrecarga de memoria, implementación más compleja\n");
    }

    private void limpiarCampos() {
        codigoField.setText("");
        nombresField.setText("");
        apellidosField.setText("");
        telefonoField.setText("");
        correoField.setText("");
        direccionField.setText("");
        codigoPostalField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazGrafica().setVisible(true);
            }
        });
    }
}