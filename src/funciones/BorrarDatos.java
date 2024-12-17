package funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import entidades.Alumno;
import entidades.Matricula;
import entidades.Profesor;

/**
 * Clase que gestiona el borrado de datos en una base de datos MySQL.
 */
public class BorrarDatos {

	/**
	 * Borra todos los datos de una tabla de la base de datos segun los parametros
	 * proporcionados.
	 * 
	 * @param conn        Conexion activa a la base de datos.
	 * @param nombreTabla Nombre de la tabla.
	 * @param confirmar   Declaracion del usuario que confirma o revierte la
	 *                    transaccion.
	 * @return `true` si los datos se borraron con exito; `false` si ocurrio algun
	 *         error.
	 * @throws SQLException En caso de errores relacionados con la conexion o SQL.
	 */
	public static boolean borrarTodosDatos(Connection conn, String nombreTabla, boolean confirmado) {

		// Declaracion de objetos necesarios para la ejecucion de la consulta.
		Statement stmt = null;
		String sql = ""; // Variable para almacenar la consulta SQL.
		int filasAfectadas;

		boolean borradoCompletado = false; // Indicador de exito en el borrado de campos.

		try {

			// Inicializa el objeto Statement para ejecutar consultas SQL.
			stmt = conn.createStatement();

			// Selecciona la consulta SQL
			sql += "DELETE FROM " + nombreTabla + ";";

			
			// Segun la confirmacion del usuario
			if (confirmado) {
				// Ejecuta la consulta SQL para borrar todos los campos.
				stmt.executeUpdate(sql);
				borradoCompletado = true; // Marca el borrado como exitoso.
				// System.out.println("Cambios confirmados");
				// System.out.println("Cambios confirmados. Filas afectadas: " +
				// filasAfectadas);

			} else {
				System.out.println("\u001B[91mOperación cancelada \u001B[0m"); // Color personalizado para el "error"

			}
			
			// Captura errores relacionados con la ejecucion de la consulta SQL.
		} catch (SQLException e) {
			if (!confirmado) {

				System.out.println("De todas maneras, esta operación no podría realizarse");
			}

			System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"

			if (e.getSQLState().equals("23000")) {
				System.out.println("Violación de restricción de clave foránea definida en la Tabla 'Matriculas'");

				System.out.println(
						"Sugerencia: Borre todos los datos asociados de la Tabla 'Matriculas' y luego intentar borrar los datos de la Tabla '"
								+ nombreTabla + "'");

			} else {
				System.out.println("Se ha producido un error");
				System.out.println("Reinicie la App y MySQL Workbench si lo tiene abierto");
				System.out.println(e.getMessage());
				System.out.println("Estado SQL " + e.getSQLState());
			}

//			 Para averiguar lo que debe salir 
			System.out.println(e.getMessage());
			System.out.println("Estado SQL " + e.getSQLState());
		} finally {
			try {
				// Verifica si el objeto stmt no es nulo antes de cerrarlo para evitar
				// excepciones.
				if (stmt != null)
					stmt.close(); // Libera recursos utilizados por el objeto Statement.

				// Manejo de excepciones al intentar cerrar el Statement.
			} catch (SQLException se) {
				System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
				System.out.println("No se ha podido cerrar el Statement.");

			}
		}
		// Retorna el estado del borrado.
		return borradoCompletado;

	}

	/**
	 * Borra un dato concreto de una tabla de la base de datos segun los parametros
	 * proporcionados.
	 * 
	 * @param conn          Conexion activa a la base de datos.
	 * @param nombreTabla   Nombre de la tabla.
	 * @param nombreColumnaFiltro Nombre de la columna en la que se realizara el filtro.
	 * @param nombreDatoFiltro    Valor especifico que se busca en la columna.
	 * @param confirmar     Declaracion del usuario que confirma o revierte la
	 *                      transaccion.
	 * @return `true` si el dato se borro con exito; `false` si ocurrio algun error.
	 * @throws SQLException En caso de errores relacionados con la conexion o SQL.
	 */
	public static boolean borrarDatoConcreto(Connection conn, String nombreTabla, String nombreColumnaFiltro,
			String nombreDatoFiltro, boolean confirmado)  {

		// Declaracion de objetos necesarios para la ejecucion de la consulta.
		PreparedStatement stmt = null;
		String sql = ""; // Variable para almacenar la consulta SQL.
		int filasAfectadas;

		boolean borradoCompletado = false; // Indicador de exito en el borrado de campos.

		try {

			// Selecciona la consulta SQL
			sql += "DELETE FROM " + nombreTabla + " WHERE " + nombreColumnaFiltro + " = ?;";

			// Pasamos la consulta al objeto PreparedStatement
			stmt = conn.prepareStatement(sql);

			// Dependiendo del nombre de la tabla, inicializa el objeto correspondiente
			switch (nombreTabla) {
			case "Profesores":
				// Crea un nuevo objeto Profesor
				Profesor profesor = new Profesor();

				// Segun el nombre de la columna, establece el valor que se va a buscar
				switch (nombreColumnaFiltro) {
				case "idProfesor":
					// Convierte el dato a un numero entero
					profesor.setIdProfesor(Integer.parseInt(nombreDatoFiltro));
					stmt.setInt(1, profesor.getIdProfesor()); // Asigna el valor al PreparedStatement
					break;
				case "Nombre":
					profesor.setNombre(nombreDatoFiltro);
					stmt.setString(1, profesor.getNombre()); // Asigna el valor al PreparedStatement
					break;
				case "Apellidos":
					profesor.setApellidos(nombreDatoFiltro);
					stmt.setString(1, profesor.getApellidos()); // Asigna el valor al PreparedStatement
					break;
				case "FechaNacimiento":
					// Convierte la fecha de nacimiento de String a LocalDate
					profesor.setFechaNacimiento(LocalDate.parse(nombreDatoFiltro)); // 2000-05-15
					stmt.setString(1, profesor.getFechaNacimiento().toString()); // Asigna el valor al PreparedStatement
					break;
				case "Antiguedad":
					// Convierte el dato a un numero entero
					profesor.setAntiguedad(Integer.parseInt(nombreDatoFiltro));
					stmt.setInt(1, profesor.getAntiguedad()); // Asigna el valor al PreparedStatement
					break;
				default:
					System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"

					// Si el nombre de columna no es valido
					System.out.println("El nombre del campo especificado no es valido.");
					break;
				}
				break;
			case "Alumnos":
				// Crea un nuevo objeto Alumno
				Alumno alumno = new Alumno();

				// Segun el nombre de la columna, establece el valor que se va a buscar
				switch (nombreColumnaFiltro) {
				case "idAlumno":
					// Convierte el dato a un numero entero
					alumno.setIdAlumno(Integer.parseInt(nombreDatoFiltro));
					stmt.setInt(1, alumno.getIdAlumno()); // Asigna el valor al PreparedStatement
					break;
				case "Nombre":
					alumno.setNombre(nombreDatoFiltro);
					stmt.setString(1, alumno.getNombre()); // Asigna el valor al PreparedStatement
					break;
				case "Apellidos":
					alumno.setApellidos(nombreDatoFiltro);
					stmt.setString(1, alumno.getApellidos()); // Asigna el valor al PreparedStatement
					break;
				case "FechaNacimiento":
					// Convierte la fecha de nacimiento de String a LocalDate
					alumno.setFechaNacimiento(LocalDate.parse(nombreDatoFiltro)); // 2000-05-15
					stmt.setString(1, alumno.getFechaNacimiento().toString()); // Asigna el valor al PreparedStatement
					break;
				default:
					System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"

					// Si el nombre de columna no es valido
					System.out.println("El nombre del campo especificado no es valido.");
					break;
				}
				break;
			case "Matriculas":
				// Crea un nuevo objeto Matricula
				Matricula matricula = new Matricula();

				// Segun el nombre de la columna, establece el valor que se va a buscar
				switch (nombreColumnaFiltro) {
				case "idMatricula":
					// Convierte el dato a un numero entero
					matricula.setIdMatricula(Integer.parseInt(nombreDatoFiltro));
					stmt.setInt(1, matricula.getIdMatricula()); // Asigna el valor al PreparedStatement
					break;
				case "idProfesor":
					// Convierte el dato a un numero entero
					matricula.setIdProfesor(Integer.parseInt(nombreDatoFiltro));
					stmt.setInt(1, matricula.getIdProfesor()); // Asigna el valor al PreparedStatement
					break;
				case "idAlumno":
					// Convierte el dato a un numero entero
					matricula.setIdAlumno(Integer.parseInt(nombreDatoFiltro));
					stmt.setInt(1, matricula.getIdAlumno()); // Asigna el valor al PreparedStatement
					break;
				case "Asignatura":
					matricula.setAsignatura(nombreDatoFiltro);
					stmt.setString(1, matricula.getAsignatura()); // Asigna el valor al PreparedStatement
					break;
				case "Curso":
					// Convierte el curso de String a Integer
					matricula.setCurso(Integer.parseInt(nombreDatoFiltro));
					stmt.setInt(1, matricula.getCurso()); // Asigna el valor al PreparedStatement
					break;
				default:
					System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"

					// Si el nombre de columna no es valido
					System.out.println("El nombre del campo especificado no es valido.");
					break;
				}
				break;
			// Si el nombre de la tabla no es valido
			default:
				System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"

				System.out.println("El nombre de la tabla especificado no es valido.");
				break;
			}

			

			// Segun la confirmacion del usuario
			if (confirmado) {
				// Ejecuta la sentencia SQL
				stmt.execute();
				borradoCompletado = true; // Marca el borrado como exitoso.
				// System.out.println("Cambios confirmados");
				// System.out.println("Cambios confirmados. Filas afectadas: " +
				// filasAfectadas);

			} else {
				System.out.println("\u001B[91mOperación cancelada \u001B[0m"); // Color personalizado para el "error"

			}
			// Captura errores relacionados con la ejecucion de la consulta SQL.
		} catch (SQLException e) {
			if (!confirmado) {

				System.out.println("De todas maneras, esta operación no podría realizarse");
			}

			System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"

			 if (e.getSQLState().equals("23000")) {
				System.out.println("Violación de restricción de clave foránea definida en la Tabla 'Matriculas'");

				System.out.println(
						"Sugerencia: Borre todos los datos asociados de la Tabla 'Matriculas' y luego intentar borrar los datos de la Tabla '"
								+ nombreTabla + "'");

			} else {
				System.out.println("Se ha producido un error");
				System.out.println("Reinicie la App y MySQL Workbench si lo tiene abierto");
				System.out.println(e.getMessage());
				System.out.println("Estado SQL " + e.getSQLState());
			}

//			 Para averiguar lo que debe salir 
			System.out.println(e.getMessage());
			System.out.println("Estado SQL " + e.getSQLState());
		} finally {
			try {
				// Verifica si el objeto stmt no es nulo antes de cerrarlo para evitar
				// excepciones.
				if (stmt != null)
					stmt.close(); // Libera recursos utilizados por el objeto Statement.

				// Manejo de excepciones al intentar cerrar el Statement.
			} catch (SQLException se) {
				System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
				System.out.println("No se ha podido cerrar el Statement.");

			}
		}

		// Retorna si el borrado fue exitoso o no.
		return borradoCompletado;

	}
}

// Concatena los campos en la consulta SQL segun el nombre de la tabla
// especificado.
//switch (nombreTabla) {
//case "Profesores":
//	switch (filtro) {
//	case "Nombre":
//		sql += "WHERE " + filtro + " =?;";
//		break;
//	case "Apellidos":
//		sql += "WHERE " + filtro + " =?;";
//		break;
//	case "FechaNacimiento":
//		sql += "WHERE " + filtro + " =?;";
//		break;
//	case "Antiguedad":
//		sql += "WHERE " + filtro + " =?;";
//		break;
//	default:
//		System.err.println("Error: El nombre del campo especificado no es válido.");
//		break;
//	}
//case "Alumnos":
//	switch (filtro) {
//	case "Nombre":
//		sql += "WHERE " + filtro + " =?;";
//		break;
//	case "Apellidos":
//		sql += "WHERE " + filtro + " =?;";
//		break;
//	case "FechaNacimiento":
//		sql += "WHERE " + filtro + " =?;";
//		break;
//	default:
//		System.err.println("Error: El nombre del campo especificado no es válido.");
//		break;
//	}
//case "Matriculas":
//	switch (filtro) {
//	case "Asignatura":
//		sql += "WHERE " + filtro + " =?;";
//		break;
//	case "Curso":
//		sql += "WHERE " + filtro + " =?;";
//		break;
//	default:
//		System.err.println("Error: El nombre del campo especificado no es válido.");
//		break;
//	}
//	// Si el nombre no es valido.
//default:
//	System.err.println("Error: El nombre de la tabla especificado no es válido.");
//
//}
