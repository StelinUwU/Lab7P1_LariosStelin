/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab7p1_primerapellido;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author stelinlarios Fila 1 - Silla 14
 */
public class Lab7P1_LariosStelin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int selectedOption = 0;
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        while (selectedOption != 3) {
            showMenu();
            selectedOption = sc.nextInt();
            switch (selectedOption) {
                case 1:
                    boolean wantsToKeepPlaying = true;
                    while (wantsToKeepPlaying) {
                        char currentTurn = 'X';
                        char[][] matriz = new char[3][3];
                        boolean isThereAWinner = false;
                        boolean isThereADraw = false;

                        System.out.println("-------Bienvenido a 3 en raya----------");

                        char[][] tablero = generarTablero(matriz);

                        while (!isThereAWinner && !isThereADraw) {

                            imprimirTablero(tablero);
                            System.out.println("Turno de:" + currentTurn);

                            int fila = 0;
                            int columna = 0;
                            boolean isSelectedPositionValid = false;

                            while (!isSelectedPositionValid) {

                                if (currentTurn == '0') {
                                    fila = rand.nextInt(3);
                                    columna = rand.nextInt(3);
                                } else {
                                    System.out.println("Ingrese la fila (0,1,2)");
                                    fila = sc.nextInt();
                                    System.out.println("Ingrese la columna (0,1,2)");
                                    columna = sc.nextInt();
                                }

                                if (verificarPosicionValida(tablero, fila, columna)) {
                                    isSelectedPositionValid = true;

                                } else {

                                    if (currentTurn == 'X') {
                                        System.out.println("Posicion no vàlida o ya ocupada. Inténtelo de nuevo.");
                                    }
                                }
                            }

                            System.out.println("El usuario ha elegido la posición: (" + fila + ", " + columna + ")");
                            tablero[fila][columna] = currentTurn;

                            if (verificarVictoria(tablero, currentTurn)) {
                                isThereAWinner = true;
                                System.out.println(currentTurn + "ha ganado!");
                            } else if (verificarEmpate(tablero)) {
                                System.out.println("Es un empate!");

                                isThereADraw = true;
                            } else {
                                if (currentTurn == 'X') {
                                    currentTurn = '0';
                                } else {
                                    currentTurn = 'X';
                                }
                            }
                        }
                        imprimirTablero(tablero);

                        char continuePlaying;

                        System.out.println("Quieres jugar otra vez? s/n");
                        continuePlaying = sc.next().charAt(0);

                        if (continuePlaying != 's') {
                            wantsToKeepPlaying = false;
                        }
                    }
                    break;
                case 2:
                    int filas;
                    int columnas;
                    int[][] matriz;
                    System.out.println("Bienvenido a Puntos de silla");
                    System.out.println("Ingrese el numero de filas");
                    filas = sc.nextInt();
                    System.out.println("Ingrese el numero de columnas");
                    columnas = sc.nextInt();

                    matriz = generarMatrizAleatoria(filas, columnas);

                    printMatrizAleatoria(matriz);
                    encontrarPuntosSilla(matriz);

                    break;
                case 3:
                    System.out.println("Adios <3");
                    break;
                default:
                    throw new AssertionError();
            }
        }

    }

    public static void showMenu() {
        System.out.println("Seleccione una opción");
        System.out.println("1. Tres en raya");
        System.out.println("2. Puntos en silla");
        System.out.println("3. Salir");
    }

    public static char[][] generarTablero(char[][] matriz) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matriz[i][j] = ' ';
            }
        }
        return matriz;
    }

    public static void imprimirTablero(char[][] tablero) {
        for (int i = 0; i < 3; i++) {
            System.out.print("[");
            for (int j = 0; j < 3; j++) {

                if (j == 1 || j == 0) {
                    System.out.print(tablero[i][j] + ",");
                } else {
                    System.out.print(tablero[i][j]);
                }

            }
            System.out.println("]");
        }
    }

    public static boolean verificarPosicionValida(char[][] tablero, int fila, int columna) {

        if (fila > 2 || columna > 2) {
            return false;
        }

        char currentPositionValue = tablero[fila][columna];

        if (currentPositionValue == ' ') {
            return true;
        }
        return false;
    }

    public static boolean verificarVictoria(char[][] tablero, char player) {

        boolean diagonal1Win = true;
        boolean diagonal2Win = true;

        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == player && tablero[i][1] == player && tablero[i][2] == player) {
                return true;
            }

            if (tablero[0][i] == player && tablero[1][i] == player && tablero[2][i] == player) {
                return true;
            }

            if (tablero[i][i] != player) {
                diagonal1Win = false;
            }

            if (tablero[i][2 - i] != player) {
                diagonal2Win = false;
            }
        }

        return diagonal1Win || diagonal2Win;
    }

    public static boolean verificarEmpate(char[][] tablero) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    public static int[][] generarMatrizAleatoria(int filas, int columnas) {
        int[][] matriz = new int[filas][columnas];
        Random rand = new Random();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = rand.nextInt(99);
            }
        }
        return matriz;
    }

    public static void printMatrizAleatoria(int[][] matriz) {

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + "        ");
            }
            System.out.println(' ');
        }
    }

    public static void encontrarPuntosSilla(int[][] matriz) {

        boolean puntoSillaEncontrado = false;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (esSilla(matriz, i, j)) {
                    System.out.println("Punto de silla encontrado en Matriz[" + i + ", " + j + "]: " + matriz[i][j]);
                    puntoSillaEncontrado = true;
                }

            }
        }
        if (!puntoSillaEncontrado) {
            System.out.println("No se encontraron puntos de silla en la matriz.");
        }
    }

    public static boolean esSilla(int[][] matriz, int fila, int columna) {

        int valor = matriz[fila][columna];
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][columna] > valor) {
                return false;
            }
        }
        for (int j = 0; j < matriz[fila].length; j++) {
            if (matriz[fila][j] < valor) {
                return false;
            }
        }
        return true;
    }
}
