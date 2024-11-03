package presentacion;

import Dominio.Pelicula;
import Servicio.IServicioPeliculas;
import Servicio.ServicioPeliculasArchivo;
import Servicio.ServicioPeliculasLista;

import java.util.Scanner;

public class CatalogoPeliculas {
    public static void main(String[] args) {
        var salir = false;
        var console = new Scanner(System.in);
        //Agregamos la implementación del servicio
        //IServicioPeliculas servicioPeliculas = new ServicioPeliculasLista();
        IServicioPeliculas servicioPeliculas = new ServicioPeliculasArchivo();
        while (!salir){
            try{
              mostrarMenu();
              salir = ejecutarOpciones(console, servicioPeliculas);
            }catch (Exception e){
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
            System.out.println();
        } //Fin while
    }

    private static void mostrarMenu(){
        System.out.print("""
                *** Cat\u00E1logo de Pel\u00EDculas ***
                1. Agregar pel\u00EDcula
                2. Listar pel\u00EDculas
                3. Buscar pel\u00EDcula
                4. Salir
                Elige una opci\u00F3n:
                """);
    }

    private static boolean ejecutarOpciones(Scanner consola, IServicioPeliculas servicioPeliculas){
            var opcion = Integer.parseInt(consola.nextLine());
            var salir = false;
            switch (opcion){
                case 1 -> {
                    System.out.println("Introduce el nombre de la pel\u00EDcula: ");
                    var nombrePelicula = consola.nextLine();
                    servicioPeliculas.agregarPelicula(new Pelicula(nombrePelicula));//creando un nuevo objeto
                }
                case 2 -> servicioPeliculas.listarPeliculas();
                case 3 -> {
                    System.out.println("Introduce la pel\u00EDcula a buscar: ");
                    var buscar = consola.nextLine();
                    servicioPeliculas.buscarPelicula(new Pelicula(buscar));//buscamos película
                }
                case 4 -> {
                    System.out.println("Hasta pronto!");
                    System.out.println("Gracias por venir");
                    salir = true;
                }
                default -> System.out.println("Opci\u00F3n no reconocida: " + opcion);
            }
            return salir;
    }
}
