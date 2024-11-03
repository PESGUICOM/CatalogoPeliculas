package Servicio;

import Dominio.Pelicula;

public interface IServicioPeliculas {
    //Agregamos los métodos
    public void listarPeliculas();

    public void agregarPelicula(Pelicula pelicula);

    public void buscarPelicula(Pelicula pelicula);
}
