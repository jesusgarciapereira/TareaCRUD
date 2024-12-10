package funciones;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase que gestiona la actualizacion de datos en una base de datos MySQL.
 */
public class ActualizarDatos {

	public static boolean actualizarDato(Connection conn, String nombreTabla, String nombreColumnaAntigua, String nombreColumnaNueva,
			String nombreDatoAntiguo, String nombreDatoNuevo, boolean confirmar) throws SQLException {

		// Declaracion de objetos necesarios para la ejecucion de la consulta.
				Statement stmt = null;
				String sql = ""; // Variable para almacenar la consulta SQL.
				int filasAfectadas;

				boolean actualizadoCompletado = false; // Indicador de exito en el actualizado de campos.

				try {
					// Inicializa el objeto Statement para ejecutar consultas SQL.
					stmt = conn.createStatement();

					// Desactivar el modo de autocommit para manejar transacciones manualmente
					conn.setAutoCommit(false);

					// Selecciona la consulta SQL
					sql += "UPDATE " + nombreTabla + " SET " + nombreColumnaNueva + " = " + nombreDatoNuevo + " WHERE " + nombreColumnaAntigua + " = " + nombreDatoAntiguo + ";";

					stmt.executeUpdate(sql);
					
					// Segun la confirmacion del usuario
					if (confirmar) {
						conn.commit(); // Confirmamos la transaccion
						actualizadoCompletado = true; // Marca el borrado como exitoso.
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
				return actualizadoCompletado;
	}

}
