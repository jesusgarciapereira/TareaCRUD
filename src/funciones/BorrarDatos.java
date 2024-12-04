package funciones;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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

}
