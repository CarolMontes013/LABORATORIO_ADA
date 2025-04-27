
package sopadeletras;

//CAROL MONTES RAMOS 23200334
import java.util.*;

public class SopaDeLetras {
    
    private char[][] tablero; //matriz de letras

    private final int[][] direcciones = {
        {-1, -1}, //1
        {-1, 0}, //2
        {-1, 1}, //3
        { 0, -1}, //4         
        { 0, 1}, //5
        { 1, -1}, //6
        { 1, 0}, //7
        { 1, 1} //8
    }; //8 direcciones 

    private final String[] nombresDirecciones = {
        "Diagonal Arriba Izquierda",//1
        "Arriba",//2
        "Diagonal Arriba Derecha",//3
        "Izquierda",//4
        "Derecha",//5
        "Diagonal Abajo Izquierda",//6
        "Abajo",//7
        "Diagonal Abajo Derecha"//8
    }; //8 direcciones usadas para mostrar el resultado al usuario

    public SopaDeLetras(char[][] tablero) {
        this.tablero = tablero;
    }

    public void buscarPalabra(String palabra) {
        boolean encontrada = false;

        for (int fila = 0; fila < tablero.length; fila++) {
            for (int col = 0; col < tablero[0].length; col++) {
                int[] resultado = buscarDesde(fila, col, palabra.toUpperCase());
                if (resultado != null) {
                    int finFila = resultado[0], finCol = resultado[1], dirIndex = resultado[2];
                    System.out.println(palabra + " ENCONTRADA:");
                    System.out.println("  ° Comienza en (" + (fila + 1) + "," + (col + 1) + ")");
                    System.out.println("  ° Termina en (" + (finFila + 1) + "," + (finCol + 1) + ")");
                    System.out.println("  ° Direccion: " + nombresDirecciones[dirIndex]);
                    encontrada = true;
                    break;
                }
            }
            if (encontrada) break;
        }

        if (!encontrada) {
            System.out.println(palabra + " NO ENCONTRADA ");
        }
    }

    private int[] buscarDesde(int fila, int col, String palabra) {
        int n = tablero.length;
        int m = tablero[0].length;

        for (int dir = 0; dir < direcciones.length; dir++) {
            int f = fila, c = col;
            int i;

            for (i = 0; i < palabra.length(); i++) {
                if (f < 0 || f >= n || c < 0 || c >= m || tablero[f][c] != palabra.charAt(i)) {
                    break;
                }
                f += direcciones[dir][0];
                c += direcciones[dir][1];
            }

            if (i == palabra.length()) {
                f -= direcciones[dir][0];
                c -= direcciones[dir][1];
                return new int[]{f, c, dir}; // Retorna posición final + dirección
            }
        }

        return null;
    }

    public static void main(String[] args) {
        char[][] tablero = {
            
            {'E', 'S', 'T', 'O'},
            {'S', 'T', 'T', 'M'},
            {'E', 'A', 'S', 'A'},
            {'P', 'R', 'N', 'E'}
        };

        SopaDeLetras sopa = new SopaDeLetras(tablero);
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la palabra hallada en la sopa de letras (escriba 'fin' para terminar):");

        while (true) {
            System.out.print("Palabra: ");
            String palabra = sc.nextLine().trim();
            if (palabra.equalsIgnoreCase("fin")) {
                System.out.println("Busqueda finalizada.");
                break;
            }
            sopa.buscarPalabra(palabra);
        }
    }
}
