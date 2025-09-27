import java.util.Scanner;

public class MetodoSimplex {
    private double[][] tabla; // Tabla simplex
    private int numVariables; // Número de variables de decisión
    private int numRestricciones; // Número de restricciones
    private int numVariablesTotales; // Variables + holguras + artificiales
    private boolean esMaximizacion;
    private int[] variablesBasicas;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== MÉTODO SIMPLEX ===");
        System.out.print("¿Es maximización (1) o minimización (2)?: ");
        int tipo = scanner.nextInt();
        boolean esMaximizacion = (tipo == 1);
        System.out.print("Número de variables de decisión (x1, x2, ...): ");
        int numVariables = scanner.nextInt();
        System.out.print("Número de restricciones: ");
        int numRestricciones = scanner.nextInt();
        MetodoSimplex simplex = new MetodoSimplex(numVariables, numRestricciones, esMaximizacion);
        simplex.ingresarDatos(scanner);
        simplex.resolver();
        scanner.close();
    }
    public MetodoSimplex(int numVariables, int numRestricciones, boolean esMaximizacion) {
        this.numVariables = numVariables;
        this.numRestricciones = numRestricciones;
        this.esMaximizacion = esMaximizacion;
        this.numVariablesTotales = numVariables + numRestricciones;
        this.variablesBasicas = new int[numRestricciones];
        // Inicializar tabla simplex: filas = restricciones + 1, columnas = variables + holguras + 1 (LD)
        tabla = new double[numRestricciones + 1][numVariablesTotales + 1];
    }
    public void ingresarDatos(Scanner scanner) {
        System.out.println("\n=== INGRESO DE LA FUNCIÓN OBJETIVO ===");
        System.out.println("Ingrese los coeficientes de la función objetivo:");
        // Ingresar coeficientes de la función objetivo
        for (int j = 0; j < numVariables; j++) {
            System.out.printf("Coeficiente de x%d: ", j + 1);
            double coeficiente = scanner.nextDouble();
            tabla[numRestricciones][j] = esMaximizacion ? -coeficiente : coeficiente;
        }
        System.out.println("\n=== INGRESO DE RESTRICCIONES ===");
        for (int i = 0; i < numRestricciones; i++) {
            System.out.printf("\n--- Restricción %d ---\n", i + 1);
            // Coeficientes de las variables
            for (int j = 0; j < numVariables; j++) {
                System.out.printf("Coeficiente de x%d: ", j + 1);
                tabla[i][j] = scanner.nextDouble();
            }
            // Lado derecho (LD)
            System.out.print("Lado derecho (LD) de la restricción: ");
            tabla[i][numVariablesTotales] = scanner.nextDouble();
            // Variable de holgura (automática)
            tabla[i][numVariables + i] = 1.0;
            variablesBasicas[i] = numVariables + i;
            System.out.printf("Variable de holgura agregada: s%d\n", i + 1);
        }
        // Mostrar tabla inicial
        System.out.println("\n=== TABLA INICIAL SIMPLEX ===");
        imprimirTabla();
    }
    public void resolver() {
        System.out.println("\n=== INICIO DEL MÉTODO SIMPLEX ===");
        int iteracion = 0;
        while (!esOptimo()) {
            iteracion++;
            System.out.printf("\n--- Iteración %d ---\n", iteracion);
            int columnaPivote = encontrarColumnaPivote();
            if (columnaPivote == -1) {
                System.out.println("Solución óptima encontrada!");
                break;
            }
            int filaPivote = encontrarFilaPivote(columnaPivote);
            if (filaPivote == -1) {
                System.out.println("El problema es no acotado!");
                return;
            }
            System.out.printf("Elemento pivote: tabla[%d][%d] = %.4f\n", 
            filaPivote, columnaPivote, tabla[filaPivote][columnaPivote]);
            realizarPivoteo(filaPivote, columnaPivote);
            variablesBasicas[filaPivote] = columnaPivote;
            imprimirTabla();
        }
        mostrarSolucion();
    }
    private boolean esOptimo() {
        // Para maximización: todos los coeficientes en la fila Z deben ser >= 0
        // Para minimización: todos los coeficientes en la fila Z deben ser <= 0
        for (int j = 0; j < numVariablesTotales; j++) {
            if (esMaximizacion) {
                if (tabla[numRestricciones][j] < -0.0001) return false;
            } else {
                if (tabla[numRestricciones][j] > 0.0001) return false;
            }
        }
        return true;
    }
    private int encontrarColumnaPivote() {
        int columnaPivote = -1;
        double valorMasNegativo = 0;
        if (esMaximizacion) {
            // Para maximización: buscar el valor más negativo
            for (int j = 0; j < numVariablesTotales; j++) {
                if (tabla[numRestricciones][j] < valorMasNegativo) {
                    valorMasNegativo = tabla[numRestricciones][j];
                    columnaPivote = j;
                }
            }
        } else {
            // Para minimización: buscar el valor más positivo
            for (int j = 0; j < numVariablesTotales; j++) {
                if (tabla[numRestricciones][j] > valorMasNegativo) {
                    valorMasNegativo = tabla[numRestricciones][j];
                    columnaPivote = j;
                }
            }
        }  
        return columnaPivote;
    }
    private int encontrarFilaPivote(int columnaPivote) {
        int filaPivote = -1;
        double minRatio = Double.MAX_VALUE;
        for (int i = 0; i < numRestricciones; i++) {
            if (tabla[i][columnaPivote] > 0.0001) { // Evitar división por cero
                double ratio = tabla[i][numVariablesTotales] / tabla[i][columnaPivote];
                if (ratio >= 0 && ratio < minRatio) {
                    minRatio = ratio;
                    filaPivote = i;
                }
            }
        }
        return filaPivote;
    }
    private void realizarPivoteo(int filaPivote, int columnaPivote) {
        double elementoPivote = tabla[filaPivote][columnaPivote];
        // Normalizar la fila pivote
        for (int j = 0; j <= numVariablesTotales; j++) {
            tabla[filaPivote][j] /= elementoPivote;
        }
        // Actualizar las demás filas
        for (int i = 0; i <= numRestricciones; i++) {
            if (i != filaPivote) {
                double factor = tabla[i][columnaPivote];
                for (int j = 0; j <= numVariablesTotales; j++) {
                    tabla[i][j] -= factor * tabla[filaPivote][j];
                }
            }
        }
    }
    private void imprimirTabla() {
        // Encabezado
        System.out.print("\nBase\t");
        for (int j = 0; j < numVariables; j++) {
            System.out.printf("x%d\t", j + 1);
        }
        for (int j = 0; j < numRestricciones; j++) {
            System.out.printf("s%d\t", j + 1);
        }
        System.out.println("LD");
        // Filas de restricciones
        for (int i = 0; i < numRestricciones; i++) {
            if (variablesBasicas[i] < numVariables) {
                System.out.printf("x%d\t", variablesBasicas[i] + 1);
            } else {
                System.out.printf("s%d\t", variablesBasicas[i] - numVariables + 1);
            }  
            for (int j = 0; j <= numVariablesTotales; j++) {
                System.out.printf("%.2f\t", tabla[i][j]);
            }
            System.out.println();
        }
        // Fila Z
        System.out.print("Z\t");
        for (int j = 0; j <= numVariablesTotales; j++) {
            System.out.printf("%.2f\t", tabla[numRestricciones][j]);
        }
        System.out.println();
    }
    private void mostrarSolucion() {
        System.out.println("\n=== SOLUCIÓN ÓPTIMA ===");
        // Valores de las variables de decisión
        double[] solucion = new double[numVariables];
        for (int i = 0; i < numRestricciones; i++) {
            if (variablesBasicas[i] < numVariables) {
                solucion[variablesBasicas[i]] = tabla[i][numVariablesTotales];
            }
        }
        System.out.println("Valores de las variables:");
        for (int i = 0; i < numVariables; i++) {
            System.out.printf("x%d = %.4f\n", i + 1, solucion[i]);
        }
        // Valor óptimo de Z
        double valorOptimo = tabla[numRestricciones][numVariablesTotales];
        if (!esMaximizacion) {
            valorOptimo = -valorOptimo; // Ajustar para minimización
        }
        System.out.printf("Valor óptimo de Z = %.4f\n", valorOptimo);
        // Variables básicas
        System.out.println("\nVariables básicas en la solución:");
        for (int i = 0; i < numRestricciones; i++) {
            if (variablesBasicas[i] < numVariables) {
                System.out.printf("x%d = %.4f\n", variablesBasicas[i] + 1, tabla[i][numVariablesTotales]);
            } else {
                System.out.printf("s%d = %.4f\n", variablesBasicas[i] - numVariables + 1, tabla[i][numVariablesTotales]);
            }
        }
    }
}
