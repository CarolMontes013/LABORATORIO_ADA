/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ackermanniterativo;
import java.util.Scanner;


/**
 *
 * @author ASUS
 */
public class AckermannIterativo {

    public static int ackermann(int m, int n) {
           int[] stack = new int[10000]; // Pila 
        int top = 0;
        stack[top++] = m;

        int paso = 0;

        while (top > 0) {
            m = stack[--top];

            System.out.println("\n===============================");
            System.out.println(" Paso " + paso++);
            System.out.println("===============================");
            System.out.println("Estado actual:");
            System.out.println("  m = " + m + ", n = " + n);
            System.out.print("  Pila : [ ");
            for (int i = 0; i < top; i++) {
                System.out.print(stack[i] + " ");
            }
            System.out.println("]");

            if (m == 0) {
                System.out.println("| Caso base: m == 0 | n = n + 1 | n = " + (n + 1));
                n = n + 1;
            } else if (n == 0) {
                System.out.println("| Caso: n == 0 | llamamos A(" + (m - 1) + ", 1)");
                stack[top++] = m - 1;
                n = 1;
            } else {
                System.out.println("| Caso recursivo: llamamos A(" + (m - 1) + ", A(" + m + ", " + (n - 1) + "))");
                stack[top++] = m - 1; // Esta se resolverá después
                stack[top++] = m;     // Resolviendo primero A(m, n - 1)
                n = n - 1;
            }

            // Prevención de desbordamiento o que colapse
            if (top >= stack.length) {
                throw new StackOverflowError("La pila simulada se desbordó");
            }
        }

        System.out.println("\n===================================");
        System.out.println(" Resultado final: Ackermann = " + n);
        System.out.println("===================================");
        return n;
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingresar el primer valor (m): ");
        int m = entrada.nextInt();

        System.out.print("Ingresar el segundo valor (n): ");
        int n = entrada.nextInt();

        try {
            int resultado = ackermann(m, n);
            System.out.println("El resultado de Ackermann(" + m + ", " + n + ") es: " + resultado);
        } catch (StackOverflowError e) {
            System.out.println("Error: la pila excedió su límite.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }

        entrada.close();
    }
}
