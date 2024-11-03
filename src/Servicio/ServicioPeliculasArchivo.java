package Servicio;

import Dominio.Pelicula;

import java.io.*;

public class ServicioPeliculasArchivo implements IServicioPeliculas{

    private final String NOMBRE_ARCHIVO = "peliculas.txt";

    public ServicioPeliculasArchivo(){
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            //Si ya existe el archivo, NO se vuelve a crear
            if (archivo.exists()){
                System.out.println("El archivo ya existe!");
            } else {
                //Si no existe se crea vacío
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close(); //Cerramos nuestro archivo
                System.out.println("Se ha creado el archivo");
            }
        } catch (Exception e) {
            System.out.println("Ocurri\u00F3 un error al abrir el archivo: " + e.getMessage());
        }
    }

    @Override
    public void listarPeliculas() {
        //Volvemos a abrir el archivo
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            System.out.println("Listado de películas");
            //Abrimos el archivo para lectura
            var entrada = new BufferedReader(new FileReader(archivo));//para poder leer líneas del archivo
            //Leémos línea a línea el archivo
            String linea = entrada.readLine();
            //Leemos todas las líneas
            while (linea != null){
                var pelicula = new Pelicula(linea);
                System.out.println(pelicula);
                //Antes de terminar el ciclo volvemos a leer la siguiente línea
                linea = entrada.readLine();//preguntamos la siguiente película
            }
            //Cerrar el archivo
            entrada.close();
        } catch (Exception e){
            System.out.println("Ocurri\u00F3 un error al leer el archivo: " + e.getMessage());
        }
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        boolean anexar = false;
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
          //Revisamos si existe el archivo
          anexar = archivo.exists();
          var salida = new PrintWriter(new FileWriter(archivo, anexar));
          //Agregamos la película (toString)
            salida.println(pelicula);//se agrega información al archivo
            salida.close(); //se cierra el archivo
            System.out.println("Se agregó al archivo: " + pelicula);
        } catch (Exception e){
            System.out.println("Ocurri\u00F3 un error al agregar pel\u00EDcula: " + e.getMessage());
        }
    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
       var archivo = new File(NOMBRE_ARCHIVO);
       try {
         //Abrimos el archivo para lectura línea a línea
           var entrada = new BufferedReader(new FileReader(archivo));
           String lineaTexto = entrada.readLine();
           var indice = 1; //Arranca en uno
           var encontrada = false;
           var peliculaBuscar = pelicula.getNombre();
           while (lineaTexto != null){
               //Buscamos sin importar mayúsculas/minúsculas
               if(peliculaBuscar != null && peliculaBuscar.equalsIgnoreCase(lineaTexto)){
                   encontrada = true;
                   break;
               }
               //Leemos la siguiente línea antes de la siguiente iteración
               lineaTexto = entrada.readLine();
               indice++;
           } //Fin while
           //Imprimos los resultados de la búsqueda
           if (encontrada)
               System.out.println("Película: " + lineaTexto + " encontrada - l\u00EDnea: " + indice);
           else
               System.out.println("No se ha encontrado la pel\u00EDcula: " + pelicula.getNombre());
           entrada.close(); //Cerramos el archivo
       } catch (Exception e){
           System.out.println("Ocurri\u00F3 un error al buscar en el archivo: " + e.getMessage());
       }
    }
}
