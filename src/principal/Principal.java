package principal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import funciones.Conectar;
import funciones.CrearTablas;

public class Principal {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int opcionMenu;
		int opcionSubmenuA;
		int opcionSubmenuB;

		Connection conexion = null;
		// Para que esté conectada siempre, borrala despues
		conexion = Conectar.conectar("jdbc:mysql://dns11036.phdns11.es:3306/ad2425_jgarcia", "jgarcia", "12345");

		String nombreTabla = "";

		boolean tablaCreada = false;

		opcionMenu = -1;
		while (opcionMenu != 0) {
			menuPrincipal();
			opcionMenu = leeInt(sc);
			System.out.println();

			switch (opcionMenu) {
			case 1:
				conexion = peticionesOpcion1(sc);
				break;
			case 2:
				opcionSubmenuA = -1;
				while (opcionSubmenuA != 0) {
					subMenuCrearTablas();
					opcionSubmenuA = leeInt(sc);
					System.out.println();

					switch (opcionSubmenuA) {
					case 1:
						if (conexion != null) {
							nombreTabla = "Profesores";
							tablaCreada = CrearTablas.crearTabla(conexion, nombreTabla);
							System.out.print((tablaCreada) ? "Tabla '" + nombreTabla + "' creada con éxito\n" : "");

							nombreTabla = "Alumnos";
							tablaCreada = CrearTablas.crearTabla(conexion, nombreTabla);
							System.out.print((tablaCreada) ? "Tabla '" + nombreTabla + "' creada con éxito\n" : "");

							nombreTabla = "Matriculas";
							tablaCreada = CrearTablas.crearTabla(conexion, nombreTabla);
							System.out.print((tablaCreada) ? "Tabla '" + nombreTabla + "' creada con éxito\n" : "");
						} else {
							System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
							System.out.println("No estás conectado a la Base de Datos");
							System.out.println(
									"Sugerencia: Primero, siga los pasos de la opción 1 (Conectar con la Base de Datos) e intente crear las Tablas de nuevo");
						}
						System.out.println();

						opcionSubmenuA = 0;
						tablaCreada = false;
						nombreTabla = "";
						break;
					case 2:
						opcionSubmenuB = -1;

						while (opcionSubmenuB != 0) {
							if (conexion != null) {
								subMenuCrearUnaTablaConcreta();
								opcionSubmenuB = leeInt(sc);
								System.out.println();

								switch (opcionSubmenuB) {
								case 1:
									nombreTabla = "Profesores";
									break;
								case 2:
									nombreTabla = "Alumnos";
									break;
								case 3:
									nombreTabla = "Matriculas";
									break;

								case 0:
									break;

								default:
									System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el
																					// "error"
									System.out.println("Opción no disponible, elija del 0 al 3");
									System.out.println();
									break;
								} // Cierre del switch terciario

								if (!nombreTabla.equals("")) {
									tablaCreada = CrearTablas.crearTabla(conexion, nombreTabla);
									System.out.print(
											(tablaCreada) ? "Tabla '" + nombreTabla + "' creada con éxito\n" : "");
									opcionSubmenuB = 0;
									opcionSubmenuA = 0;
									tablaCreada = false;
									nombreTabla = "";
								}
							} else {
								System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el
																				// "error"
								System.out.println("No estás conectado a la Base de Datos");
								System.out.println(
										"Sugerencia: Primero, siga los pasos de la opción 1 (Conectar con la Base de Datos) e intente crear las Tablas de nuevo");
								opcionSubmenuB = 0;
								opcionSubmenuA = 0;
							}

						} // Cierre del while terciario
						System.out.println();
						break;
					case 0:
						break;

					default:
						System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
						System.out.println("Opción no disponible, elija del 0 al 2");
						System.out.println();
						break;
					} // Cierre del switch secundario
				} // Cierre del while secundario
				break;
			case 0:
				break;

			default:
				System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
				System.out.println("Opción no disponible, elija del 0 al 7");
				System.out.println();
				break;
			}// Cierre del switch principal
		} // Cierre del while principal

		System.out.println("Saliendo del programa...");

		try

		{
			// Se asegura de que la conexion no sea null para cerrarla
			if (conexion != null) {
				// Cierra la conexion para liberar recursos del sistema.
				conexion.close();
			}
			// Manejo de excepciones al intentar la conexion.
		} catch (SQLException se) {
			System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
			System.out.println("No se ha podido cerrar la conexion.");
		}

		sc.close();

	}

	private static Connection peticionesOpcion1(Scanner sc) {
		String url;
		String usuario;
		String contrasennia;
		Connection conexion;
		System.out.println(
				"Introduzca la dirección de la Base de Datos en formato JDBC (Tal que así: jdbc:mysql://dns11036.phdns11.es:3306/ad2425_jgarcia)");
		url = sc.nextLine();
		System.out.println("Introduzca el nombre de usuario (Tal que así: jgarcia)");
		usuario = sc.nextLine();
		System.out.println("Introduzca la contraseña (Tal que así: 12345)");
		contrasennia = sc.nextLine();

		System.out.println();

		conexion = Conectar.conectar(url, usuario, contrasennia);

		if (conexion == null) {
			System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
			System.out.println("No se ha podido llevar a cabo la conexión");

		} else {
			System.out.println("Conexión exitosa con la Base de Datos");
		}

		System.out.println();
		return conexion;
	}

	private static void subMenuCrearTablas() {
		System.out.println("Crear Tablas:");
		System.out.println("=============");
		System.out.println("1. Crear todas las Tablas.");
		System.out.println("2. Crear una Tabla en concreto.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	private static void subMenuCrearUnaTablaConcreta() {
		System.out.println("Crear una Tabla en concreto:");
		System.out.println("===========================");

		System.out.println("1. Profesores.");
		System.out.println("2. Alumnos.");
		System.out.println("3. Matriculas.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	// Pendiente
	/*
	 * private static void peticionesOpcion1(Scanner sc) { String url; String
	 * usuario; String contrasennia; Connection conexion; System.out.
	 * println("Introduzca la dirección de la Base de Datos en formato JDBC (Tal que así: jdbc:mysql://dns11036.phdns11.es:3306)"
	 * ); url = sc.nextLine();
	 * System.out.println("Introduzca el nombre de usuario (Tal que así: jgarcia)");
	 * usuario = sc.nextLine();
	 * System.out.println("Introduzca la contraseña (Tal que así: 12345)");
	 * contrasennia = sc.nextLine();
	 * 
	 * conexion = Conectar.conectar(url, usuario, contrasennia);
	 * 
	 * if (conexion == null) {
	 * System.err.println("Error: No se ha podido llevar a cabo la conexion"); }
	 * else { System.out.println("Conexión exitosa con la Base de Datos"); }
	 * 
	 * System.out.println(); }
	 */

	/**
	 * Menu principal del programa que muestra las diferentes opciones
	 */
	private static void menuPrincipal() {
		System.out.println("Menú Principal:");
		System.out.println("===============");
		System.out.println("1. Conectar con la Base de Datos.");
		System.out.println("2. Crear Tablas.");
		System.out.println("3. Eliminar Tablas.");
		System.out.println("4. Listar Tablas.");
		System.out.println("5. Insertar Datos.");
		System.out.println("6. Modificar Datos.");
		System.out.println("7. Borrar Datos.");
		System.out.println("0. Salir del Programa");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	/**
	 * Funcion que devuelve el numero entero escrito por teclado
	 * 
	 * @param sc Objeto de tipo Scanner que leera
	 * @return Valor del numero entero escrito
	 */
	private static int leeInt(Scanner sc) {
		// Numero decimal que devolverá la funcion, inicializado en -1
		int intDevuelto = -1;

		try {
			// Le asignamos el int escrito por teclado
			intDevuelto = sc.nextInt();

			// Si se produce un InputMismatchException
		} catch (InputMismatchException e) {
			// Mostrará este mensaje
			System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
			System.out.println("El valor introducido no es un número entero");
		} finally {
			// Siempre limpiamos el buffer
			sc.nextLine();
		}

		// Devolverá el valor escrito por teclado, o -1 si no es del tipo correcto
		return intDevuelto;
	}
}
