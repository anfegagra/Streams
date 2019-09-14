package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static List<Usuario> usuarios;

    public static void main(String args[]) {
        inicializarUsuarios();
        usuarios.stream().forEach(usuario -> {
            usuario.setNombre(usuario.getNombre() + " apellido");
            System.out.println(usuario.getId() + " " + usuario.getNombre());
        });

        List<String> lista = usuarios.stream().map(usuario -> usuario.getNombre()).collect(
            Collectors.toList());
        lista.forEach(System.out::println);
    }

    public static void inicializarUsuarios() {
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1, "pipe"));
        usuarios.add(new Usuario(1, "david"));
        usuarios.add(new Usuario(1, "dary"));
        usuarios.add(new Usuario(1, "jorge"));
        usuarios.add(new Usuario(1, "pao"));
        usuarios.add(new Usuario(1, "laura"));
    }
}
