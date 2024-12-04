package funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
	 *                    transaccion
	 * @return `true` si los datos se borraron con exito; `false` si ocurrio algún
	 *         error.
	 * @throws SQLException En caso de errores relacionados con la conexion o SQL.
	 */
	public static boolean borrarTodosDatos(Connection conn, String nombreTabla, boolean confirmar) throws SQLException {

		// Declaracion de objetos necesarios para la ejecución de la consulta.
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

			/*
			 * // Ejecutar la sentencia y verificar si se afectaron filas filasAfectadas =
			 * stmt.executeUpdate(sql); if (filasAfectadas >= 0) { // Si no falla, significa
			 * que la operación fue exitosa borradoCompletado = true; }
			 */

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

	public static boolean borrarDatoConcreto(Connection conn, String nombreTabla, String columna, String dato, boolean confirmar) {

		// Declaracion de objetos necesarios para la ejecución de la consulta.
		PreparedStatement stmt = null;
		String sql = ""; // Variable para almacenar la consulta SQL.
		int filasAfectadas;

		boolean borradoCompletado = false; // Indicador de exito en el borrado de campos.

		try {
			// Selecciona la consulta SQL
			sql += "DELETE FROM " + nombreTabla + "WHERE " + columna + " =?;";

			stmt = conn.prepareStatement(sql);
			
			switch (nombreTabla) {
			case "Profesores":
				
				Profesor profesor = new Profesor();
				
				switch (columna) {
				case "Nombre":
					profesor.setNombre(dato);
					stmt.setString(1, profesor.getNombre());
					break;
				case "Apellidos":
					sql += "WHERE " + columna + " =?;";
					break;
				case "FechaNacimiento":
					sql += "WHERE " + columna + " =?;";
					break;
				case "Antiguedad":
					sql += "WHERE " + columna + " =?;";
					break;
				default:
					System.err.println("Error: El nombre del campo especificado no es válido.");
					break;
				}
			case "Alumnos":
				switch (columna) {
				case "Nombre":
					sql += "WHERE " + columna + " =?;";
					break;
				case "Apellidos":
					sql += "WHERE " + columna + " =?;";
					break;
				case "FechaNacimiento":
					sql += "WHERE " + columna + " =?;";
					break;
				default:
					System.err.println("Error: El nombre del campo especificado no es válido.");
					break;
				}
			case "Matriculas":
				switch (columna) {
				case "Asignatura":
					sql += "WHERE " + columna + " =?;";
					break;
				case "Curso":
					sql += "WHERE " + columna + " =?;";
					break;
				default:
					System.err.println("Error: El nombre del campo especificado no es válido.");
					break;
				}
				// Si el nombre no es valido.
			default:
				System.err.println("Error: El nombre de la tabla especificado no es válido.");

			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Concatena los campos en la consulta SQL segun el nombre de la tabla
		// especificado.
//		switch (nombreTabla) {
//		case "Profesores":
//			switch (filtro) {
//			case "Nombre":
//				sql += "WHERE " + filtro + " =?;";
//				break;
//			case "Apellidos":
//				sql += "WHERE " + filtro + " =?;";
//				break;
//			case "FechaNacimiento":
//				sql += "WHERE " + filtro + " =?;";
//				break;
//			case "Antiguedad":
//				sql += "WHERE " + filtro + " =?;";
//				break;
//			default:
//				System.err.println("Error: El nombre del campo especificado no es válido.");
//				break;
//			}
//		case "Alumnos":
//			switch (filtro) {
//			case "Nombre":
//				sql += "WHERE " + filtro + " =?;";
//				break;
//			case "Apellidos":
//				sql += "WHERE " + filtro + " =?;";
//				break;
//			case "FechaNacimiento":
//				sql += "WHERE " + filtro + " =?;";
//				break;
//			default:
//				System.err.println("Error: El nombre del campo especificado no es válido.");
//				break;
//			}
//		case "Matriculas":
//			switch (filtro) {
//			case "Asignatura":
//				sql += "WHERE " + filtro + " =?;";
//				break;
//			case "Curso":
//				sql += "WHERE " + filtro + " =?;";
//				break;
//			default:
//				System.err.println("Error: El nombre del campo especificado no es válido.");
//				break;
//			}
//			// Si el nombre no es valido.
//		default:
//			System.err.println("Error: El nombre de la tabla especificado no es válido.");
//
//		}

		return borradoCompletado;

	}
}
