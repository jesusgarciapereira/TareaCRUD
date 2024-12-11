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
		int opcionSubmenu;

		String url = "";
		String usuario = "";
		String contrasennia = "";
		Connection conexion = null;

		String nombreTabla = "";

		boolean hecho = false;

		opcionMenu = -1;
		while (opcionMenu != 0) {
			menuPrincipal();
			opcionMenu = leeInt(sc);
			System.out.println();

			switch (opcionMenu) {
			case 1:
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
					System.err.println("Error: No se ha podido llevar a cabo la conexion");
				} else {
					System.out.println("Conexión exitosa con la Base de Datos");
				}

				System.out.println();
				break;
			case 2:
				opcionSubmenu = -1;
				while (opcionSubmenu != 0) {
					subMenu2();
					opcionSubmenu = leeInt(sc);
					System.out.println();

					switch (opcionSubmenu) {
					case 1:
						if (conexion != null) {
							nombreTabla = "Profesores";
							hecho = CrearTablas.crearTabla(conexion, nombreTabla);
							if (hecho) {
								System.out.println("Tabla " + nombreTabla + " creada con éxito");
							}
							
							nombreTabla = "Alumnos";
							hecho = CrearTablas.crearTabla(conexion, nombreTabla);
							if (hecho) {
								System.out.println("Tabla " + nombreTabla + " creada con éxito");
							}
							
							nombreTabla = "Matriculas";
							hecho = CrearTablas.crearTabla(conexion, nombreTabla);
							if (hecho) {
								System.out.println("Tabla " + nombreTabla + " creada con éxito");
							}
							
						} else {
							System.err.println("Error: No estás conectado a la Base de Datos");
							System.err.flush(); // Asegura que el mensaje de error se vacíe inmediatamente
							System.out.println(
									"Sugerencia: Primero, siga los pasos de la opción 1 (Conectar con la Base de Datos) e intente crear las Tablas de nuevo");
							System.out.flush(); // Asegura que el mensaje sugerencia se vacíe inmediatamente
						}
						System.out.println();
						break;
					case 0:
						break;

					default:
						System.err.println("Error: Opción no disponible");
						break;
					} // Cierre del switch secundario
					break;
				} // Cierre del while secundario
			case 0:
				break;

			default:
				System.err.println("Error: Opción no disponible");
				break;
			}// Cierre del switch principal

		} // Cierre del while principal

		System.out.println("Saliendo del programa...");

		try {
			// Se asegura de que la conexion no sea null para cerrarla
			if (conexion != null) {
				// Cierra la conexion para liberar recursos del sistema.
				conexion.close();
			}
			// Manejo de excepciones al intentar la conexion.
		} catch (SQLException se) {
			System.out.println("No se ha podido cerrar la conexion.");
		}

		sc.close();

	}

	private static void subMenu2() {
		System.out.println("Crear Tablas:");
		System.out.println("1. Crear todas las Tablas.");
		System.out.println("2. Crear una Tabla en concreto.");
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
			System.err.println("Error: El valor introducido no es un número entero");
		} finally {
			// Siempre limpiamos el buffer
			sc.nextLine();
		}

		// Devolverá el valor escrito por teclado, o -1 si no es del tipo correcto
		return intDevuelto;
	}
}
