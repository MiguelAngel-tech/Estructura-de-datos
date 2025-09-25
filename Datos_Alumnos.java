import java.util.Scanner;
class Alumno{
    String nombre;
    String NoControl;
    byte semestre;
    double promedio;
    byte edad;
    String carrera;

}
public class Datos_Alumnos {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner ingresaDato = new Scanner(System.in);
        System.out.println("Dame el tamaño del arreglo: ");
        byte tamaño = ingresaDato.nextByte();
        Alumno arregloAlumnos[]=new Alumno[tamaño];
        System.out.println("\n \n \t \tLlenado del arreglo...");
        for (int i = 0; i < tamaño; i++) {
            arregloAlumnos[i]=new Alumno();
            System.out.println("\nDatos del alumno ["+(i+1)+"]");
            System.out.println("Dame el nombre del alumno: ");
            arregloAlumnos[i].nombre=ingresaDato.next();
            System.out.println("Dame el No. de control del alumno: ");
            arregloAlumnos[i].NoControl=ingresaDato.next();
            System.out.println("Dame el semestre del alumno: ");
            arregloAlumnos[i].semestre=ingresaDato.nextByte();
            System.out.println("Dame el promedio del alumno: ");
            arregloAlumnos[i].promedio=ingresaDato.nextDouble();
            System.out.println("Dame la edad del alumno: ");
            arregloAlumnos[i].edad=ingresaDato.nextByte();
            System.out.println("Dame la carrera del alumno: ");
            arregloAlumnos[i].carrera=ingresaDato.next();
        }
        System.out.println("----------------------------------------------------------------------------------------");
        byte opcion=0;
        do{
        System.out.println("\n \n \t \t menu");
            System.out.println();
            System.out.println("\n1. Mostrar todos los datos");
            System.out.println("2. Mostrar solo los nombres");
            System.out.println("3. Buscar por nombre");
            System.out.println("4. Buscra por carrera");
            System.out.println("5. Buscar por numero de control");
            System.out.println("6. Buscar el mejor promedio");
            System.out.println("7. Buscra el peor promedio");
            System.out.println("8. Mostrar los promedios de edades");
            System.out.println("9. Buscar el semestre mas alto");
            System.out.println("10. Mostrar el semestre mas bajo");
            System.out.println("11. Buscar por semestre");
            System.out.println("12. Salir");
            System.out.println("\nOpcion: ");
            opcion = ingresaDato.nextByte();
            //opciones
            switch (opcion) {
                case 1:
                    System.out.print("\nMostrar los datos");
                    //mostrar datos
                    for(int i=0; i<tamaño; i++){
                        System.out.println("\nDatos de alumno ["+(i+1)+"]");
                        System.out.println("Nombre: "+arregloAlumnos[i].nombre);
                        System.out.println("NoControl: "+arregloAlumnos[i].NoControl);
                        System.out.println("Semestre: "+arregloAlumnos[i].semestre);
                        System.out.println("Promedio: "+arregloAlumnos[i].promedio);
                        System.out.println("Edad: "+arregloAlumnos[i].edad);
                        System.out.println("Carrera: "+arregloAlumnos[i].carrera);
                    }
                break;
                case 2:
                    //mostrar solo los nombres
                    System.out.print("\nMostrar los nombres");
                    //mostrar datos
                    for(int i=0; i<tamaño; i++){
                        System.out.println("\nNombre de alumno ["+(i+1)+"]: " +arregloAlumnos[i].nombre);
                    }
                break;
                case 3: 
                    //buscar por nombre
                    System.out.println("Escribe el nombre a buscar: ");
                    String nombreBuscado = ingresaDato.next();
                    for(int i=0; i<tamaño; i++){
                        if(arregloAlumnos[i].nombre.equals(nombreBuscado)){
                            System.out.println("\nDatos de alumno ["+(i+1)+"]");
                            System.out.println("Nombre: "+arregloAlumnos[i].nombre);
                            System.out.println("NoControl: "+arregloAlumnos[i].NoControl);
                            System.out.println("Semestre: "+arregloAlumnos[i].semestre);
                            System.out.println("Promedio: "+arregloAlumnos[i].promedio);
                            System.out.println("Edad: "+arregloAlumnos[i].edad);
                            System.out.println("Carrera: "+arregloAlumnos[i].carrera);
                        }
                    }
                break;
                case 4:
                    //buscar por carrera
                    System.out.println("Escribe la carrera a buscar");
                    String carreraBuscada = ingresaDato.next();
                    for(int i=0; i<tamaño; i++){
                        if (arregloAlumnos[i].carrera.equals(carreraBuscada)) {
                            System.out.println("\nDatos de alumno ["+(i+1)+"]");
                            System.out.println("Nombre: "+arregloAlumnos[i].nombre);
                            System.out.println("NoControl: "+arregloAlumnos[i].NoControl);
                            System.out.println("Semestre: "+arregloAlumnos[i].semestre);
                            System.out.println("Promedio: "+arregloAlumnos[i].promedio);
                            System.out.println("Edad: "+arregloAlumnos[i].edad);
                            System.out.println("Carrera: "+arregloAlumnos[i].carrera);
                        }
                    }
                break;
                case 5: 
                    //buscar por numero de control
                    System.out.println("Escriba el numero de control: ");
                    String numeroControl = ingresaDato.next();
                    for(int i=0; i<tamaño; i++){
                        if(arregloAlumnos[i].NoControl.equals(numeroControl)){
                            System.out.println("\nDatos de alumno ["+(i+1)+"]");
                            System.out.println("Nombre: "+arregloAlumnos[i].nombre);
                            System.out.println("NoControl: "+arregloAlumnos[i].NoControl);
                            System.out.println("Semestre: "+arregloAlumnos[i].semestre);
                            System.out.println("Promedio: "+arregloAlumnos[i].promedio);
                            System.out.println("Edad: "+arregloAlumnos[i].edad);
                            System.out.println("Carrera: "+arregloAlumnos[i].carrera);
                            i = tamaño + 1;
                        }
                    }
                break;
            }
        }while (opcion!=12);
    }
}