ORM -> hibernate
JPA -> java persistance api
Hibernate -> database independence

**cuando aparece que la base de datos ya está cerrada, puede que sea porque a alguna entidad le falta el @Entity

**Cuando se pone @ManyToMany en dos entidades, solo una puede tener @JoinTable

**Formato Función Lambda
(parámetros) -> expresión
Cuando hay más de una operación en la expresión deben poner llaves {}

Cuando se trata de opereciones que se van a llamar en varios lados dentro de la aplicación, lo más conveniente es realizar un función para referenciarla desde varios lados, pero cuando es algo que solo se va a hacer una vez, lo más conveniente es utilizar una función lambda

** ls -ltra para ver en consola todos los archivos incluyendo los ocultos

**método abstracto -> que no tiene implementación
**Interfaz funcional es aquella que sólo tiene un método abstracto -> es bueno utilizar la anotación @FunctionalInterface para que cuando se implemente el método lambda, se evite el error de poner más de un método en esa interface

**Para implementar una lambda tiene que ser con una interfaz funcional

**se usan expresiones lambda para crear métodos anónimos que devuelven un resultado deseado

**se puede tener la implementación de un método dentro de una interface pero anteponiendo la palabra reservada default

***REFERENCIA A MÉTODOS CON LAMBDAS
- Hay 4 tipos

Tipo										Sintax							Method Reference						Lambda expression
-------------------------------------------------------------------------------------------------------------------------------------------------
Referencia a static method					Class::staticMethod				Math::abstracto							n -> Math.abs(n)
-------------------------------------------------------------------------------------------------------------------------------------------------
Referencia a un método de instancia			instancia::metodoInstancia		s::toString								() -> "".toString()	
de un objeto particular
-------------------------------------------------------------------------------------------------------------------------------------------------
Referencia a un método de instancia			Class::metodoInstancia			String::toString						s -> s.toString()
de un objeto arbitrario de un tipo
particular
-------------------------------------------------------------------------------------------------------------------------------------------------
Referencia a un constructor					Class::new						String::new								() -> new String()
-------------------------------------------------------------------------------------------------------------------------------------------------


**OPTIONAL
El objetivo de los optional es que desaparezcan de nuestro código los NullPointerException. Es un envoltorio del tipo de dato. Puede ocurrir cuando se le pasa a un método un objeto que no ha sido instanciado/inicializado

*Operador diamante <>

*Optional.ofNullable() -> acepta null como parámetro
Optional.of() -> no acepta null como parámetro

*** Cuando inyecte con mockBean un objeto, y no sea capaz de ir hasta esa clase y ejecutar un método, lo que se debe hacer es inicializar a través del @Before ese objeto

***STREAMS
Un stream reprensenta una secuencia de elementos (ej: lista)
Un stream es un conjunto de funciones que se ejecutan de forma anidada

filter/map/reduce

.map -> ayuda a hacer una transformación rápida de los datos del stream
.filter -> ayuda a filtrar los datos
.findFirst -> devuelve un optional dependiendo del filtro que se haga
.flatmap -> datos de varios array los concatena en un único string
.peek -> similar a forEach pero sin ser una acción final, se puede seguir haciendo cosas sobre el stream con otros métodos
.count -> devuelve la cantidad de elementos del stream
.skip -> salta los primeros x elementos que le indiquemos
.limit -> limita el número de elementos que queremos obtener
.sorted -> ordenar listas de acuerdo a un criterio dado, por ejemplo por orden alfabético
.min -> obtener el valor mínimo de la lista de acuerdo a un criterio
.max -> obtener el valor máximo de una listo de acuerdo a un criterio
.distinct -> eliminar elementos duplicados
.allMatch -> verifica si el predicado es verdadero, si lo es devuelve true, de lo contrario devuelve false
.anyMatch -> verifica si al menos un valor del predicado es verdadero
.noneMatch -> verifica que ningún elemento pasa el predicado
.sum -> permite sumar
.average -> permite buscar la media/promedio
.range -> hacer una integración de n elementos como si fuera un for
.reduce -> toma un stream, y lo combina en un resultado mediante la operación repetida de una acción
.joining -> sirve para concatenar una lista de strings en uno solo
.toSet -> sirve para pasar una lista de T a Set<T> --- Set no garantiza el orden de los elementos pero si garantiza que no hayan repetidos
.summarizingDouble -> devuelve en una variable DoubleSummaryStatistics estadísticas como el máximo, mínimo, media, etc.
.partitioningBy -> devuelve en un mapa un lista con los que cumplan una condición, y otra lista con los que no cumplan esa condición
.groupingBy -> sirve para agrupar listas en un mapa según sea la condición, ej: letras que empiecen por las palabras a, b, c, etc.
.mapping -> convierte una lista de objetos en otra lista de objetos que queramos. Ej: convertir una lista de usuarios en una lista de strings
.parallelStream -> forma de ejecutar las operaciones de stream() por medio de varios hilos

**hay métodos intermedios -> puede haber/aceptar otros métodos sobre los cuales operar
hay métodos finales -> una vez se llamen ya no se puede utilizar más métodos



**FUNCIONES DE ALTO ORDEN o FUNCIONES DE ORDEN SUPERIOR (HIGH ORDER FUNCTIONS) --> funciones que (1) pueden recibir una función como parámetro de entrada o funciones que (2) pueden devolver una función en el return de la misma

**Interfaz funcional Fuction<T, R> -> interface Function<T t, R r> { R apply(T t)} donde T es el parámetro de entrada y R es el valor de retorno

**
// Interfaz funciona BiFunction<T, U, R>
		/*
		interface BiFunction<T, U, R> {
			R apply(t, u)
		 */

// Interfaz funcional Predicate<T> -> función que evalúa una expresión y devuelve y booleano
		/*
		interface Predicate<T t> {
			Boolean test(t)
		 */
		 
// Consumer<T> -> acepta un único parámetro de entrada y no devuelve nada


**Qué pasa si uno tiene varias implementaciones de una interface? Para eso se utiliza la anotación @Qualifier para decirle a spring cuál es el bean que quiere/cuál es el bean al que se le quiere hacer autowired
--> se poner @Qualifier("nombreDelBean") en donde lo está inyectando
@Qualifier("nombreDelBean") puede ser usado afuera del método si es inyección por setter, si es inyección por constructor, debe ser por medio de los parámetros

@Primary -> técnica que se puede usar cuando se tienen varios beans del mismo tipo y se quiere uno por defecto

@Profile
Perfiles de spring -> sirve para levantar ciertos bean en específico en tiempo de ejecución

**cuando no hay ningún perfil activo, se tiene la opción del perfil defaul, entonces, si hay varios beans con su respectivo perfil y no hay un perfil activo, se puede agrega una lista a alguno de ellos y agregar el default
	Ej: @Profile({"es", "default"})
	
Tipos Inyección de dependencias:
- Por propiedad
- Por constructor
- Por setter

No es buena práctica utilizar implementaciones concretas para hacer inyección de dependencias; se debe usar interfaces lo que permitirá inyectar en tiempo de ejecución la dependencia que se necesite

IoC - Inversion of Control - ambiente en tiempo de ejecución o framework que inyecta las dependencias

La anotación utilizada en Spring para indicar que se quiere inyectar una dependencia es @Autowired

Si se tiene 2 beans del mismo tipo, para especificar la preferencia de uno sobre el otro se utiliza la anotación @Primary

Para especificar el nombre de un bean que se quiere inyectar se utiliza la anotación @Qualifier("nombreDelBean")

Inversión de dependencias vs Inyección de dependencias -> Inversión de dependencias se utilizan abstracciones (interfaces), Inyección de dependencias se refiere a la inyección de una dependencia en una clase
