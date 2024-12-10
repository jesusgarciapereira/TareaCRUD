package funciones;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BorrarTablas {

	/**
	 * Borra una tabla en la base de datos segun el nombre proporcionado.
	 * 
	 * @param conn        Conexion activa a la base de datos.
	 * @param nombreTabla Nombre de la tabla a borrar. Valores validos:
	 *                    "Profesores", "Alumnos", "Matriculas".
	 * @return `true` si la tabla se borro con exito; `false` si ocurrio algún
	 *         error.
	 * @throws SQLException En caso de errores relacionados con la conexion o SQL.
	 */
	public static boolean borrarTabla(Connection conn, String nombreTabla) throws SQLException {
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

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Libera recursos utilizados por el objeto Statement.
			stmt.close();
			// Cierra la conexion para evitar fugas de recursos.
			conn.close();
		}

		// Retorna el estado de la creacion de la tabla.
		return borradoCompletado;
	}
}

/*
 * finally { try { // Verifica si el objeto stmt no es nulo antes de cerrarlo
 * para evitar excepciones. if (stmt != null) stmt.close(); // Libera recursos
 * utilizados por el objeto Statement.
 * 
 * // Cierra la conexion para liberar recursos del sistema. conn.close(); }
 * catch (SQLException se) { // Manejo de excepciones al intentar cerrar stmt o
 * conn. System.out.println("No se ha podido cerrar la conexion."); } }
 */