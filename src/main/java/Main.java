package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

		System.out.println("--------------------FlatMap--------------------");
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

		System.out.println("--------------------Peek--------------------");
		//peek
		inicializarUsuarios();
		List<Usuario> usuarios2 = usuarios.stream()
			.peek(u -> u.setNombre(u.getNombre() + " Apellido2")).collect(
				Collectors.toList());

		usuarios2.stream().forEach(u -> System.out.println(u.getNombre()));

		System.out.println("--------------------Count--------------------");
		//count
		inicializarUsuarios();
		long numeroDeItemsFiltrados = usuarios.stream().filter(usu -> usu.getId() < 3).count();
		System.out.println(numeroDeItemsFiltrados);

		System.out.println("--------------------Skip y Limit--------------------");
		//skip y limit
		String[] abc = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		List<String> abcFiltrada = Arrays.stream(abc).skip(2).limit(4).collect(Collectors.toList());
		abcFiltrada.stream().forEach(System.out::println);

		System.out.println("--------------------Sorted--------------------");
		//sorted
		inicializarUsuarios();
		usuarios.stream().sorted(Comparator.comparing(Usuario::getNombre)).collect(
			Collectors.toList()).stream().forEach(nombre -> System.out.println(nombre.getNombre()));

		System.out.println("--------------------Min y max--------------------");
		//min y max
		inicializarUsuarios();
		Usuario usuarioMin = usuarios.stream().min(Comparator.comparing(Usuario::getId))
			.orElse(null);
		System.out.println("usuario con id min: " + usuarioMin.getId());
		Usuario usuarioMax = usuarios.stream().max(Comparator.comparing(Usuario::getId))
			.orElse(null);
		System.out.println("usuario con id max: " + usuarioMax.getId());

		System.out.println("--------------------Distinct--------------------");
		//distinct
		String[] abcRepetidos = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "j", "a"};
		List<String> abcSinRepetidos = Arrays.stream(abcRepetidos).distinct()
			.collect(Collectors.toList());
		abcSinRepetidos.stream().forEach(elemento -> System.out.println(elemento));

		System.out.println("--------------------AllMatch, AnyMatch, NoneMatch--------------------");
		//allMatch, anyMatch, noneMatch
		List<Integer> listaNumeros = Arrays.asList(100, 300, 900, 4000);
		boolean allMatch = listaNumeros.stream().allMatch(numero -> numero > 301);
		System.out.println("AllMatch: " + allMatch);
		boolean anyMatch = listaNumeros.stream().anyMatch(numero -> numero > 301);
		System.out.println("AnyMatch: " + anyMatch);
		boolean noneMatch = listaNumeros.stream().noneMatch(numero -> numero > 10000);
		System.out.println("NoneMatch: " + noneMatch);

		System.out.println("--------------------Sum, Average, Range--------------------");
		//sum, average, range
		inicializarUsuarios();
		double result = usuarios.stream().mapToInt(Usuario::getId).average().orElse(0);
		System.out.println("average: " + result);
		result = usuarios.stream().mapToInt(Usuario::getId).sum();
		System.out.println("sum: " + result);
		System.out.println("range: " + IntStream.range(0, 3).sum()); //startInclusive, endExclusive

		System.out.println("--------------------Reduce--------------------");
		//reduce
		inicializarUsuarios();
		int resultSum = usuarios.stream().map(Usuario::getId).reduce(100, Integer::sum);
		System.out.println(resultSum);

		System.out.println("--------------------Joining--------------------");
		//joining
		inicializarUsuarios();
		String nombres = usuarios.stream().map(Usuario::getNombre)
			.collect(Collectors.joining(" - "));
		System.out.println("Nombres concatenados con delimitador: " + nombres);

		System.out.println("--------------------toSet--------------------");
		//toSet
		inicializarUsuarios();
		Set<String> nombresSet = usuarios.stream().map(Usuario::getNombre)
			.collect(Collectors.toSet());
		nombresSet.forEach(System.out::println);

		System.out.println("--------------------summarizingDouble--------------------");
		//summarizingDouble
		inicializarUsuarios();
		DoubleSummaryStatistics estadisticas = usuarios.stream()
			.collect(Collectors.summarizingDouble(Usuario::getId));
		System.out.println(
			"Max: " + estadisticas.getMax() + "\nMin: " + estadisticas.getMin() + "\nMedia: "
				+ estadisticas.getAverage() + "\nSuma: " + estadisticas.getSum()
				+ "\nTotal elementos: " + estadisticas.getCount());

		DoubleSummaryStatistics estadisticas1 = usuarios.stream().mapToDouble(Usuario::getId)
			.summaryStatistics();
		System.out.println(
			"Max: " + estadisticas1.getMax() + "\nMin: " + estadisticas1.getMin() + "\nMedia: "
				+ estadisticas1.getAverage() + "\nSuma: " + estadisticas1.getSum()
				+ "\nTotal elementos: " + estadisticas1.getCount());

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
