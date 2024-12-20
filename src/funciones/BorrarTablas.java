package funciones;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase que gestiona el borrado de tablas en una base de datos MySQL.
 */
public class BorrarTablas {

	/**
	 * Borra una tabla en la base de datos segun el nombre proporcionado.
	 * 
	 * @param conn        Conexion activa a la base de datos.
	 * @param nombreTabla Nombre de la tabla a borrar. Valores validos:
	 *                    "Profesores", "Alumnos", "Matriculas".
	 * @return `true` si la tabla se borro con exito; `false` si ocurrio algún
	 *         error.
	 */
	public static boolean borrarTabla(Connection conn, String nombreTabla) {

		// Declaracion de objetos necesarios para la ejecucion de la consulta.
		Statement stmt = null;
		String sql = ""; // Variable para almacenar la consulta SQL.

		boolean borradoCompletado = false; // Indicador de exito en el borrado de tabla.
		try {
			// Inicializa el objeto Statement para ejecutar consultas SQL.
			stmt = conn.createStatement();

			// Selecciona la consulta SQL
			sql += "DROP TABLE " + nombreTabla + ";";

			// Ejecuta la consulta SQL para borrar todos los campos.
			stmt.executeUpdate(sql);
			borradoCompletado = true; // Marca el borrado como exitoso.

			// Captura errores relacionados con la ejecucion de la consulta SQL.
		} catch (SQLException e) {
			System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"

			if (e.getSQLState().equals("42S02")) {
				System.out.println("La tabla '" + nombreTabla
						+ "' no existe en la Base de Datos, puede que aún no la hayas creado o la hayas borrado con anterioridad");
			} else if (e.getSQLState().equals("23000")) {
				System.out.println(
						"No se pudo borrar la tabla '" + nombreTabla + "' porque está referenciada en otra tabla");
				System.out.println("Sugerencia: Borrar primero la tabla 'Matriculas'");
			} else {
				System.out.println("Se ha producido un error");
				System.out.println("Reinicie la App y MySQL Workbench si lo tiene abierto");
			}
//				 Para averiguar lo que debe salir 
//			System.out.println(e.getMessage());
//			System.out.println("Estado SQL " + e.getSQLState());
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

		// Retorna el estado de la creacion de la tabla.
		return borradoCompletado;
	}
}
