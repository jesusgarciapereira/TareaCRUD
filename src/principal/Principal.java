package principal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import funciones.BorrarTablas;
import funciones.Conectar;
import funciones.CrearTablas;
import funciones.ListarTablas;

public class Principal {

	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		int opcionMenu;
		int opcionSubmenuA;
		int opcionSubmenuB;
		int opcionSubmenuC;

		String url = "";
		String usuario = "";
		String contrasennia = "";
		Connection conexion = null;
		// Para que esté conectada siempre, borrala despues
		conexion = Conectar.conectar("jdbc:mysql://dns11036.phdns11.es:3306/ad2425_jgarcia", "jgarcia", "12345");

		String nombreTabla = "";
		String nombreColumnaFiltro = "";
		String opcionFiltro = "";
		String nombreDatoFiltro = "";

		boolean operacionRealizada = false;

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
					System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
					System.out.println("No se ha podido llevar a cabo la conexión");

				} else {
					System.out.println("Conexión exitosa con la Base de Datos");
				}

				System.out.println();
				break; // Cierre case 1 opcionMenu
			case 2:
				if (conexion != null) {
					opcionSubmenuA = -1;
					while (opcionSubmenuA != 0) {
						subMenuCrearTablas();
						opcionSubmenuA = leeInt(sc);
						System.out.println();

						switch (opcionSubmenuA) {
						case 1:
							nombreTabla = "Profesores";
							operacionRealizada = CrearTablas.crearTabla(conexion, nombreTabla);
							System.out.print(
									(operacionRealizada) ? "Tabla '" + nombreTabla + "' creada con éxito\n" : "");

							nombreTabla = "Alumnos";
							operacionRealizada = CrearTablas.crearTabla(conexion, nombreTabla);
							System.out.print(
									(operacionRealizada) ? "Tabla '" + nombreTabla + "' creada con éxito\n" : "");

							nombreTabla = "Matriculas";
							operacionRealizada = CrearTablas.crearTabla(conexion, nombreTabla);
							System.out.print(
									(operacionRealizada) ? "Tabla '" + nombreTabla + "' creada con éxito\n" : "");

							System.out.println();

							opcionSubmenuA = 0;
							operacionRealizada = false;
							nombreTabla = "";
							break; // Cierre case 1 opcionSubmenuA
						case 2:
							opcionSubmenuB = -1;

							while (opcionSubmenuB != 0) {

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
									operacionRealizada = CrearTablas.crearTabla(conexion, nombreTabla);
									System.out.print(
											(operacionRealizada) ? "Tabla '" + nombreTabla + "' creada con éxito\n"
													: "");
									opcionSubmenuB = 0;
									opcionSubmenuA = 0;
									operacionRealizada = false;
									nombreTabla = "";
								}

							} // Cierre del while terciario
							System.out.println();
							break; // Cierre case 2 opcionSubmenuA
						case 0:
							break;

						default:
							System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
							System.out.println("Opción no disponible, elija del 0 al 2");
							System.out.println();
							break;

						} // Cierre del switch secundario
					} // Cierre del while secundario
				} else {
					System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el
																	// "error"
					System.out.println("No estás conectado a la Base de Datos");
					System.out.println(
							"Sugerencia: Primero, siga los pasos de la opción 1 (Conectar con la Base de Datos) e intente crear las Tablas de nuevo");

					System.out.println();
					opcionSubmenuB = 0;
					opcionSubmenuA = 0;
				}
				break; // Cierre case 2 opcionMenu

			case 3:
				if (conexion != null) {
					opcionSubmenuA = -1;
					while (opcionSubmenuA != 0) {
						subMenuEliminarTablas();
						opcionSubmenuA = leeInt(sc);
						System.out.println();

						switch (opcionSubmenuA) {
						case 1:

							nombreTabla = "Matriculas";
							operacionRealizada = BorrarTablas.borrarTabla(conexion, nombreTabla);
							System.out.print(
									(operacionRealizada) ? "Tabla '" + nombreTabla + "' borrada con éxito\n" : "");

							nombreTabla = "Profesores";
							operacionRealizada = BorrarTablas.borrarTabla(conexion, nombreTabla);
							System.out.print(
									(operacionRealizada) ? "Tabla '" + nombreTabla + "' borrada con éxito\n" : "");

							nombreTabla = "Alumnos";
							operacionRealizada = BorrarTablas.borrarTabla(conexion, nombreTabla);
							System.out.print(
									(operacionRealizada) ? "Tabla '" + nombreTabla + "' borrada con éxito\n" : "");

							System.out.println();

							opcionSubmenuA = 0;
							operacionRealizada = false;
							nombreTabla = "";
							break; // Cierre case 1 opcionSubmenuA
						case 2:
							opcionSubmenuB = -1;

							while (opcionSubmenuB != 0) {

								subMenuEliminarUnaTablaConcreta();
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
									operacionRealizada = BorrarTablas.borrarTabla(conexion, nombreTabla);
									System.out.print(
											(operacionRealizada) ? "Tabla '" + nombreTabla + "' borrada con éxito\n"
													: "");
									opcionSubmenuB = 0;
									opcionSubmenuA = 0;
									operacionRealizada = false;
									nombreTabla = "";
								}

							} // Cierre del while terciario
							System.out.println();
							break;// Cierre case 2 opcionSubmenuA
						case 0:
							break;

						default:
							System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
							System.out.println("Opción no disponible, elija del 0 al 2");
							System.out.println();
							break;

						} // Cierre del switch secundario
					} // Cierre del while secundario
				} else {
					System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el
																	// "error"
					System.out.println("No estás conectado a la Base de Datos");
					System.out.println(
							"Sugerencia: Primero, siga los pasos de la opción 1 (Conectar con la Base de Datos) e intente crear las Tablas de nuevo");

					System.out.println();
					opcionSubmenuB = 0;
					opcionSubmenuA = 0;
				}
				break; // Cierre case 3 opcionMenu
			case 4:
				if (conexion != null) {
					opcionSubmenuA = -1;
					while (opcionSubmenuA != 0) {
						subMenuListarTablas();
						opcionSubmenuA = leeInt(sc);
						System.out.println();

						switch (opcionSubmenuA) {
						case 1:

							nombreTabla = "Profesores";
							ListarTablas.listarTodo(conexion, nombreTabla);

							nombreTabla = "Alumnos";
							ListarTablas.listarTodo(conexion, nombreTabla);

							nombreTabla = "Matriculas";
							ListarTablas.listarTodo(conexion, nombreTabla);

							System.out.println();

							opcionSubmenuA = 0;
							nombreTabla = "";
							break; // Cierre case 1 opcionSubmenuA
						case 2:
							opcionSubmenuB = -1;

							while (opcionSubmenuB != 0) {

								subMenuListarUnaTablaConcreta();
								opcionSubmenuB = leeInt(sc);
								System.out.println();

								switch (opcionSubmenuB) {
								case 1:
									nombreTabla = "Profesores";

									/*
									 * if (!nombreTabla.equals(""){ opcionSubmenuC = -1;
									 * 
									 * while (opcionSubmenuC != 0) { submenuCompletoOFiltrado(nombreTabla);
									 * opcionSubmenuC = leeInt(sc); System.out.println();
									 * 
									 * switch (opcionSubmenuC) { case 1: ListarTablas.listarTodo(conexion,
									 * nombreTabla);
									 * 
									 * opcionSubmenuC = 0; opcionSubmenuB = 0; opcionSubmenuA = 0; nombreTabla = "";
									 * 
									 * break; case 2: nombreTabla = "Alumnos"; break;
									 * 
									 * case 0: break;
									 * 
									 * default: System.out.print("\u001B[91mError: \u001B[0m"); // Color
									 * personalizado para // el // "error"
									 * System.out.println("Opción no disponible, elija del 0 al 2");
									 * System.out.println(); break; } // Cierre del switch cuaternario } // Cierre
									 * del while cuaternario´ }
									 */
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
									opcionSubmenuC = -1;

									while (opcionSubmenuC != 0) {
										submenuCompletoOFiltrado(nombreTabla);
										opcionSubmenuC = leeInt(sc);
										System.out.println();

										switch (opcionSubmenuC) {
										case 1:
											ListarTablas.listarTodo(conexion, nombreTabla);
											System.out.println("Ésta es la Tabla '" + nombreTabla + "' completa");

											opcionSubmenuC = 0;
											opcionSubmenuB = 0;
											opcionSubmenuA = 0;
											nombreTabla = "";

											break;
										case 2:
											switch (nombreTabla) {
											case "Profesores":
												submenuFiltrarColumnasProfesores();
												break;
											case "Alumnos":
												submenuFiltrarColumnasAlumnos();

												break;
											case "Matriculas":
												submenuFiltrarColumnasMatriculas();

												break;

											default:
												break;
											}
											// Preguntar columna de filtrado
											// Preguntar opcion de filtrado
											// Preguntar nombre dato de filtrado
											break;

										case 0:
											break;

										default:
											System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para
																							// el
																							// "error"
											System.out.println("Opción no disponible, elija del 0 al 2");
											System.out.println();
											break;
										} // Cierre del switch cuaternario
									} // Cierre del while cuaternario´
								}

							} // Cierre del while terciario
							System.out.println();
							break;// Cierre case 2 opcionSubmenuA
						case 0:
							break;

						default:
							System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
							System.out.println("Opción no disponible, elija del 0 al 2");
							System.out.println();
							break;

						} // Cierre del switch secundario
					} // Cierre del while secundario
				} else {
					System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el
																	// "error"
					System.out.println("No estás conectado a la Base de Datos");
					System.out.println(
							"Sugerencia: Primero, siga los pasos de la opción 1 (Conectar con la Base de Datos) e intente crear las Tablas de nuevo");

					System.out.println();
					opcionSubmenuB = 0;
					opcionSubmenuA = 0;
				}
				break; // Cierre case 4 opcionMenu

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

		try {
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

		// Cerramos el Scanner
		sc.close();

	}

//	private static Connection peticionesOpcion1(Scanner sc) {
//		String url;
//		String usuario;
//		String contrasennia;
//		Connection conexion;
//		System.out.println(
//				"Introduzca la dirección de la Base de Datos en formato JDBC (Tal que así: jdbc:mysql://dns11036.phdns11.es:3306/ad2425_jgarcia)");
//		url = sc.nextLine();
//		System.out.println("Introduzca el nombre de usuario (Tal que así: jgarcia)");
//		usuario = sc.nextLine();
//		System.out.println("Introduzca la contraseña (Tal que así: 12345)");
//		contrasennia = sc.nextLine();
//
//		System.out.println();
//
//		conexion = Conectar.conectar(url, usuario, contrasennia);
//
//		if (conexion == null) {
//			System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
//			System.out.println("No se ha podido llevar a cabo la conexión");
//
//		} else {
//			System.out.println("Conexión exitosa con la Base de Datos");
//		}
//
//		System.out.println();
//		return conexion;
//	}

	private static void submenuFiltrarColumnasProfesores() {
		System.out.println("Filtrado de Tabla 'Profesores:");
		System.out.println("==============================");
		System.out.println("1. idProfesor.");
		System.out.println("2. Nombre.");
		System.out.println("3. Apellidos.");
		System.out.println("4. FechaNacimiento");
		System.out.println("5. Antiguedad.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	private static void submenuFiltrarColumnasAlumnos() {
		System.out.println("Filtrado de Tabla 'Alumnos:");
		System.out.println("===========================");
		System.out.println("1. idAlumno.");
		System.out.println("2. Nombre.");
		System.out.println("3. Apellidos.");
		System.out.println("4. FechaNacimiento");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	private static void submenuFiltrarColumnasMatriculas() {
		System.out.println("Filtrado de Tabla 'Matriculas:");
		System.out.println("==============================");
		System.out.println("1. idMatricula.");
		System.out.println("2. idProfesor.");
		System.out.println("3. idAlumno.");
		System.out.println("4. Asignatura.");
		System.out.println("5. Codigo.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	private static void submenuCompletoOFiltrado(String nombreTabla) {
		System.out.println("Listar Tabla '" + nombreTabla + "':");
		System.out.println("=========================");
		System.out.println("1. Completa.");
		System.out.println("2. Filtrada.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	/**
	 * Submenu de Listar una Tabla en concreto
	 */
	private static void subMenuListarUnaTablaConcreta() {
		System.out.println("Listar una Tabla en concreto:");
		System.out.println("=============================");
		System.out.println("1. Profesores.");
		System.out.println("2. Alumnos.");
		System.out.println("3. Matriculas.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	/**
	 * Submenu de Crear Tablas
	 */
	private static void subMenuListarTablas() {
		System.out.println("Listar Tablas:");
		System.out.println("==============");
		System.out.println("1. Listar todas las Tablas.");
		System.out.println("2. Listar una Tabla en concreto.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	/**
	 * Submenu de Eliminar una Tabla en concreto
	 */
	private static void subMenuEliminarUnaTablaConcreta() {
		System.out.println("Eliminar una Tabla en concreto:");
		System.out.println("===============================");
		System.out.println("1. Profesores.");
		System.out.println("2. Alumnos.");
		System.out.println("3. Matriculas.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	/**
	 * Submenu de Crear Tablas
	 */
	private static void subMenuCrearTablas() {
		System.out.println("Crear Tablas:");
		System.out.println("=============");
		System.out.println("1. Crear todas las Tablas.");
		System.out.println("2. Crear una Tabla en concreto.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	/**
	 * Submenu de Crear una Tabla en concreto
	 */
	private static void subMenuCrearUnaTablaConcreta() {
		System.out.println("Crear una Tabla en concreto:");
		System.out.println("============================");
		System.out.println("1. Profesores.");
		System.out.println("2. Alumnos.");
		System.out.println("3. Matriculas.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	// Pendiente

	/**
	 * Submenu de eliminar Tablas
	 */
	private static void subMenuEliminarTablas() {
		System.out.println("Eliminar Tablas:");
		System.out.println("================");
		System.out.println("1. Eliminar todas las Tablas.");
		System.out.println("2. Eliminar una Tabla en concreto.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

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
