package Servicio;

import Dominio.Pelicula;
import java.util.ArrayList;
import java.util.List;

public class ServicioPeliculasLista implements IServicioPeliculas {
    //List es una interface
    private final List<Pelicula> peliculas;

    //ArrayList acá es una clase ya concreta
    public ServicioPeliculasLista() {
        this.peliculas = new ArrayList<>();
    }

    @Override
    public void listarPeliculas() {
        System.out.println("Listado de Pel\u00EDculas...");
        peliculas.forEach(System.out::println);
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
        System.out.println("Se agreg\u00F3 la pel\u00EDcula: " + pelicula);
    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        //Regresamos el índice de la película encontrada en la lista
        var indice = peliculas.indexOf(pelicula);
        if (indice == -1)
            System.out.println("No se encontr\u00F3 la pel\u00EDcula: " + pelicula);
        else
            System.out.println("Pel\u00EDcula encontrada en el \u00EDndice: " + indice);
    }

    public static void main(String[] args) {
        //Creamos algunos objetos de tipo película
        var pelicula1 = new Pelicula("Batman");
        var pelicula2 = new Pelicula("Superman");
        //Creamos el servicio (patrón de diseño service)
        IServicioPeliculas servicioPeliculas = new ServicioPeliculasLista();
        //Agregamos las peliculas a la lista
        servicioPeliculas.agregarPelicula(pelicula1);
        servicioPeliculas.agregarPelicula(pelicula2);
        //Listamos las películas
        servicioPeliculas.listarPeliculas();
        //Buscamos una pelicula
        //Se debe implementar el método equals y hashCode
        servicioPeliculas.buscarPelicula(pelicula1);
        servicioPeliculas.buscarPelicula(pelicula2);
        servicioPeliculas.buscarPelicula(new Pelicula("Oldboy"));
    }
}
