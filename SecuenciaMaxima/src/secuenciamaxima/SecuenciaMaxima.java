/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package secuenciamaxima;


import java.util.Random;
import java.util.Scanner;

public class SecuenciaMaxima {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("========== SUMA MAXIMA CONSECUTIVA ==========");
        
        int n = 0;
        while (n <=0){
            System.out.print("Ingresa la cantidad de numeros que deseas que sea tu secuencia: ");
            n=scanner.nextInt();
            if (n<=0){
                System.out.println("Debes ingresar un numero mayor que 0.");
            }
        }

        int[] vector = new int[n];

        System.out.println("Secuencia generada:");
        for (int i = 0; i < n; i++) {
            vector[i] = random.nextInt(41) - 20; // Aleatorio entre -20 y +20
            System.out.print(vector[i] + " ");
        }

        // Llamamos al método para encontrar la suma máxima
        SecuenciaMaxima(vector);
    }

    public static void SecuenciaMaxima(int[] vector) {
        int suma = 0;
        int sumaParcial = 0;
        int inicio = 0;
        int fin = 0;
        int tempInicio = 0;

        for (int k = 0; k < vector.length; k++) {
            sumaParcial = sumaParcial + vector[k];

            if (sumaParcial < 0) {
                sumaParcial = 0;
                tempInicio = k + 1;
            } else {
                if (sumaParcial > suma) {
                    suma = sumaParcial;
                    inicio = tempInicio;
                    fin = k;
                }
            }
        }

        // Mostrar resultado
        System.out.println("\nResultado:");
        if (suma == 0) {
            System.out.println("Todos los numeros son negativos o cero.");
        } else {
            System.out.println("La suma maxima consecutiva es de la posicion " + (inicio + 1) +
                               " hasta la posicion " + (fin + 1) + " con un valor de " + suma + ".");
        }
    }
}