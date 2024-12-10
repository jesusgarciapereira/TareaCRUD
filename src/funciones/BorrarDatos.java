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
	public static boolean borrarTodosDatos(Connection conn, String nombreTabla, boolean confirmar) throws SQLException {

		// Declaracion de objetos necesarios para la ejecucion de la consulta.
		Statement stmt = null;
		String sql = ""; // Variable para almacenar la consulta SQL.
		int filasAfectadas;

		boolean borradoCompletado = false; // Indicador de exito en el borrado de campos.

		try {

			// Desactivar el modo de autocommit para manejar transacciones manualmente
			conn.setAutoCommit(false);

			// Inicializa el objeto Statement para ejecutar consultas SQL.
			stmt = conn.createStatement();

			// Selecciona la consulta SQL
			sql += "DELETE FROM " + nombreTabla + ";";

			// Ejecuta la consulta SQL para borrar todos los campos.
			stmt.executeUpdate(sql);

			// Segun la confirmacion del usuario
			if (confirmar) {
				conn.commit(); // Confirmamos la transaccion
				borradoCompletado = true; // Marca el borrado como exitoso.
				// System.out.println("Cambios confirmados");
				// System.out.println("Cambios confirmados. Filas afectadas: " +
				// filasAfectadas);

			} else {
				conn.rollback(); // Revertimos la transaccion
				// System.out.println("Cambios revertidos.");
			}
			// Captura errores relacionados con la ejecucion de la consulta SQL.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Libera recursos utilizados por el objeto Statement.
			stmt.close();
			// Cierra la conexion para evitar fugas de recursos.
			conn.close();
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
	 * @param nombreColumna Nombre de la columna en la que se realizara el filtro.
	 * @param nombreDato    Valor especifico que se busca en la columna.
	 * @param confirmar     Declaracion del usuario que confirma o revierte la
	 *                      transaccion.
	 * @return `true` si el dato se borro con exito; `false` si ocurrio algun error.
	 * @throws SQLException En caso de errores relacionados con la conexion o SQL.
	 */
	public static boolean borrarDatoConcreto(Connection conn, String nombreTabla, String nombreColumna,
			String nombreDato, boolean confirmar) throws SQLException {

		// Declaracion de objetos necesarios para la ejecucion de la consulta.
		PreparedStatement stmt = null;
		String sql = ""; // Variable para almacenar la consulta SQL.
		int filasAfectadas;

		boolean borradoCompletado = false; // Indicador de exito en el borrado de campos.

		try {

			// Desactivar el modo de autocommit para manejar transacciones manualmente
			conn.setAutoCommit(false);

			// Selecciona la consulta SQL
			sql += "DELETE FROM " + nombreTabla + " WHERE " + nombreColumna + " = ?;";

			stmt = conn.prepareStatement(sql);

			// Dependiendo del nombre de la tabla, inicializa el objeto correspondiente
			switch (nombreTabla) {
			case "Profesores":
				// Crea un nuevo objeto Profesor
				Profesor profesor = new Profesor();

				// Segun el nombre de la columna, establece el valor que se va a buscar
				switch (nombreColumna) {
				case "Nombre":
					profesor.setNombre(nombreDato);
					stmt.setString(1, profesor.getNombre()); // Asigna el valor al PreparedStatement
					break;
				case "Apellidos":
					profesor.setApellidos(nombreDato);
					stmt.setString(1, profesor.getApellidos()); // Asigna el valor al PreparedStatement
					break;
				case "FechaNacimiento":
					// Convierte la fecha de nacimiento de String a LocalDate
					profesor.setFechaNacimiento(LocalDate.parse(nombreDato)); // 2000-05-15
					stmt.setString(1, profesor.getFechaNacimiento().toString()); // Asigna el valor al PreparedStatement
					break;
				case "Antiguedad":
					// Convierte el dato a un numero entero
					profesor.setAntiguedad(Integer.parseInt(nombreDato));
					stmt.setInt(1, profesor.getAntiguedad()); // Asigna el valor al PreparedStatement
					break;
				default:
					// Si el nombre de columna no es valido
					System.err.println("Error: El nombre del campo especificado no es valido.");
					break;
				}
				break;
			case "Alumnos":
				// Crea un nuevo objeto Alumno
				Alumno alumno = new Alumno();

				// Segun el nombre de la columna, establece el valor que se va a buscar
				switch (nombreColumna) {
				case "Nombre":
					alumno.setNombre(nombreDato);
					stmt.setString(1, alumno.getNombre()); // Asigna el valor al PreparedStatement
					break;
				case "Apellidos":
					alumno.setApellidos(nombreDato);
					stmt.setString(1, alumno.getApellidos()); // Asigna el valor al PreparedStatement
					break;
				case "FechaNacimiento":
					// Convierte la fecha de nacimiento de String a LocalDate
					alumno.setFechaNacimiento(LocalDate.parse(nombreDato)); // 2000-05-15
					stmt.setString(1, alumno.getFechaNacimiento().toString()); // Asigna el valor al PreparedStatement
					break;
				default:
					// Si el nombre de columna no es valido
					System.err.println("Error: El nombre del campo especificado no es valido.");
					break;
				}
				break;
			case "Matriculas":
				// Crea un nuevo objeto Matricula
				Matricula matricula = new Matricula();

				// Segun el nombre de la columna, establece el valor que se va a buscar
				switch (nombreColumna) {
				case "Asignatura":
					matricula.setAsignatura(nombreDato);
					stmt.setString(1, matricula.getAsignatura()); // Asigna el valor al PreparedStatement
					break;
				case "Curso":
					// Convierte el curso de String a Integer
					matricula.setCurso(Integer.parseInt(nombreDato));
					stmt.setInt(1, matricula.getCurso()); // Asigna el valor al PreparedStatement
					break;
				default:
					// Si el nombre de columna no es valido
					System.err.println("Error: El nombre del campo especificado no es valido.");
					break;
				}
				break;
			// Si el nombre de la tabla no es valido
			default:
				System.err.println("Error: El nombre de la tabla especificado no es valido.");
				break;
			}

			// Ejecuta la sentencia SQL
			stmt.execute();

			// Segun la confirmacion del usuario
			if (confirmar) {
				conn.commit(); // Confirmamos la transaccion
				borradoCompletado = true; // Marca el borrado como exitoso.
				// System.out.println("Cambios confirmados");
				// System.out.println("Cambios confirmados. Filas afectadas: " +
				// filasAfectadas);

			} else {
				conn.rollback(); // Revertimos la transaccion
				// System.out.println("Cambios revertidos.");
			}
			// Captura errores relacionados con la ejecucion de la consulta SQL.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Libera recursos utilizados por el objeto Statement.
			stmt.close();
			// Cierra la conexion para evitar fugas de recursos.
			conn.close();
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
