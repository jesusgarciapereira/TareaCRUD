package principal;

import java.io.IOException;
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
		int opcionSubmenuD;
		int opcionSubmenuE;
		int opcionSubmenuF;
		int opcionSubmenuG;

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
			Menus.menuPrincipal();
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
						Menus.subMenuCrearTablas();
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

								Menus.subMenuCrearUnaTablaConcreta();
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
						Menus.subMenuEliminarTablas();
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

								Menus.subMenuEliminarUnaTablaConcreta();
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
						Menus.subMenuListarTablas();
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

								Menus.subMenuListarUnaTablaConcreta();
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
								} // Cierre del switch B

								if (!nombreTabla.equals("")) {
									opcionSubmenuC = -1;

									while (opcionSubmenuC != 0) {
										Menus.submenuCompletoOFiltrado(nombreTabla);
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
												opcionSubmenuD = -1;
												while (opcionSubmenuD != 0) {
													Menus.submenuFiltrarColumnasProfesores();
													opcionSubmenuD = leeInt(sc);
													System.out.println();

													switch (opcionSubmenuD) {
													case 1:
														nombreColumnaFiltro = "idProfesor";

														opcionSubmenuE = -1;
														while (opcionSubmenuE != 0) {
															Menus.submenuOpcionFiltroINT();
															opcionSubmenuE = leeInt(sc);
															System.out.println();

															switch (opcionSubmenuE) {
															case 1:
																opcionFiltro = "=";
																opcionSubmenuE = 0;
																break;
															case 2:
																opcionFiltro = ">";
																opcionSubmenuE = 0;
																break;
															case 3:
																opcionFiltro = "<";
																opcionSubmenuE = 0;
																break;
															case 4:
																opcionFiltro = ">=";
																opcionSubmenuE = 0;
																break;
															case 5:
																opcionFiltro = "<=";
																opcionSubmenuE = 0;
																break;
															case 0:
																break;

															default:
																System.out.print("\u001B[91mError: \u001B[0m"); // Color
																// personalizado
																// para
																// el
																// "error"
																System.out.println(
																		"Opción no disponible, elija del 0 al 5");
																System.out.println();

																break;
															} // Cierre del switch E

														} // Cierre del while E

														if (!opcionFiltro.equals("")) {
															while (nombreDatoFiltro.equals("") || nombreDatoFiltro.equals("-1")) {
																System.out.print("Introduzca el idProfesor (los id son siempre mayores que 0): ");
																nombreDatoFiltro = String.valueOf(leeInt(sc));
																System.out.println();
															}

															ListarTablas.listarFiltrado(conexion, nombreTabla,
																	nombreColumnaFiltro, opcionFiltro,
																	nombreDatoFiltro);
															System.out.println("Ésta es la Consulta: SELECT * FROM "
																	+ nombreTabla + " WHERE " + nombreColumnaFiltro
																	+ " " + opcionFiltro + " " + nombreDatoFiltro);
															
															opcionSubmenuE = 0;
															opcionSubmenuD = 0;
															opcionSubmenuC = 0;
															opcionSubmenuB = 0;
															opcionSubmenuA = 0;
															nombreTabla = "";
															nombreColumnaFiltro = "";
															opcionFiltro = "";
															nombreDatoFiltro = "";
														}
														break;
													case 0:
														break;
													default:
														System.out.print("\u001B[91mError: \u001B[0m"); // Color
																										// personalizado
																										// para
														// el
														// "error"
														System.out.println("Opción no disponible, elija del 0 al 5");
														System.out.println();
														break;
													} // Cierre del switch D

												} // Cierre del while D
												break;
											case "Alumnos":
												Menus.submenuFiltrarColumnasAlumnos();

												break;
											case "Matriculas":
												Menus.submenuFiltrarColumnasMatriculas();

												break;

											default:
												break;
											} // Cierre del switch nombreTabla
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
										} // Cierre del switch C
									} // Cierre del while C
								}

							} // Cierre del while B
							System.out.println();
							break;// Cierre case 2 opcionSubmenuA
						case 0:
							break;

						default:
							System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
							System.out.println("Opción no disponible, elija del 0 al 2");
							System.out.println();
							break;

						} // Cierre del switch A
					} // Cierre del while A
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
	/*
	 * private static String leerPasswordManual() { StringBuilder password = new
	 * StringBuilder();
	 * 
	 * try { while (true) { char c = (char) System.in.read(); // Lee un carácter
	 * 
	 * // Detecta Enter para finalizar la captura if (c == '\n' || c == '\r') {
	 * break; }
	 * 
	 * // Muestra un punto por cada carácter ingresado System.out.print("•");
	 * 
	 * // Almacena el carácter real password.append(c); } } catch (IOException e) {
	 * System.out.println("\nError al leer la entrada. Inténtelo de nuevo."); }
	 * 
	 * return password.toString(); }
	 */
}
