# Estructura-de-datos
Algoritmo para almacenar datos en un array


package main;
import java.util.Scanner;
//creaciion de clases
class persona{
    String nombre;
    byte edad;
    String direccion;
    String telefono;    
}
public class Test {
    public static void main(String[] args) {
        //creacion del onjeto Scanner
        Scanner ingresaDato=new Scanner(System.in);
        //creacion el objeto persona
        persona arregloPersona []=new persona[5];
        //recorrido del array persona
        for (int i = 0; i < 5; i++) {
            arregloPersona[i]= new persona();   
        }
        System.out.println("llenado del arreglo...");
        //recorrido del arreglo para pedir los datos de la persona
        for (int i = 0; i < 5; i++) {
            System.out.println("datos de la persona"+"["+(i+1)+"]");
            
            System.out.println("Nombre: ");
            arregloPersona[i].nombre = ingresaDato.next(); 
            
            System.out.println("Edad: ");
            arregloPersona[i].edad = ingresaDato.nextByte();
            
            System.out.println("Dirección: ");
            arregloPersona[i].direccion = ingresaDato.next();
            
            System.out.println("Telefono: ");
            arregloPersona[i].telefono = ingresaDato.next();
        }
    }
}
