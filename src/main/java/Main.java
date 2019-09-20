package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static List<Usuario> usuarios;

    public static void main(String args[]) {
        inicializarUsuarios();
        //forEach
        System.out.println("--------------------ForEach--------------------");
        usuarios.stream().forEach(usuario -> {
            usuario.setNombre(usuario.getNombre() + " apellido");
            System.out.println(usuario.getId() + " " + usuario.getNombre());
        });

        System.out.println("--------------------Map--------------------");
        //map, collect y referencia a método estático
        List<String> listaMap = usuarios.stream().map(usuario -> usuario.getNombre()).collect(
            Collectors.toList());
        listaMap.forEach(System.out::println);

        inicializarUsuarios();
        System.out.println("--------------------Filter--------------------");
        //filter
        List<Usuario> listaFilter = usuarios.stream()
            .filter(usuario -> usuario.getNombre() != "pipe")
            .filter(usuario -> usuario.getId() < 3)
            .collect(Collectors.toList());
        listaFilter
            .forEach(usuario -> System.out.println(usuario.getId() + " " + usuario.getNombre()));

        inicializarUsuarios();
        System.out.println("--------------------FindFirst--------------------");
        //findFirst
        Usuario usuario = usuarios.stream().filter(u -> u.getNombre().equals("pipe")).findFirst()
            .orElse(new Usuario(7, "otro"));
        System.out.println(usuario.getId() + " " + usuario.getNombre());

        //flatMap
        List<List<String>> nombresVariasListas = new ArrayList<List<String>>(
            Arrays.asList(
                new ArrayList<String>(Arrays.asList("nombre1", "nombre2", "nombre3")),
                new ArrayList<String>(Arrays.asList("nombre4", "nombre5"))
            )
        );

        List<String> nombresUnicaLista = nombresVariasListas.stream().flatMap(l -> l.stream())
            .collect(
                Collectors.toList());

        nombresUnicaLista.stream().forEach(System.out::println);
    }

    public static void inicializarUsuarios() {
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1, "pipe"));
        usuarios.add(new Usuario(2, "david"));
        usuarios.add(new Usuario(3, "dary"));
        usuarios.add(new Usuario(4, "jorge"));
        usuarios.add(new Usuario(5, "pao"));
        usuarios.add(new Usuario(6, "laura"));
        usuarios.add(new Usuario(7, "pipe"));
    }
}
