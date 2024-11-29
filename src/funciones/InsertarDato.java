package funciones;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertarDato {

	public static boolean insertarDato(Connection conn, String nombreTabla, String[] campos) throws SQLException {

		// Declaracion de objetos necesarios para la ejecución de la consulta.
		Statement stmt = null;
		String sql = ""; // Variable para almacenar la consulta SQL.

		boolean insercionCompletada = false; // Indicador de exito en la creacion de la tabla.

		try {
			// Inicializa el objeto Statement para ejecutar consultas SQL.
			stmt = conn.createStatement();

			// Selecciona la consulta SQL
			sql += "INSERT INTO " + nombreTabla + " VALUES ";

			// Concatena los campos en la consulta SQL segun el nombre de la tabla especificado.
			switch (nombreTabla) {
			case "Profesores":
				sql += "(NULL, '" + campos[0] + "', '" + campos[1] + "', '" + campos[2] + "', " + campos[3] + ");";
				break;
			case "Alumnos":
				sql += "(NULL, '" + campos[0] + "', '" + campos[1] + "', '" + campos[2] + "');";
				break;

			case "Matriculas":
				sql += "(NULL, " + campos[0] + ", " + campos[1] + ", '" + campos[2] + "', " + campos[3] + ");";
				break;

			default:
				// Si el nombre no es valido.
				System.err.println("Error: El nombre de la tabla especificado no es válido.");

			}
			// Ejecuta la consulta SQL para insertar dato.
			stmt.executeUpdate(sql);
			insercionCompletada = true; // Marca la insercion como exitosa.

			// Captura errores relacionados con la ejecucion de la consulta SQL.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Libera recursos utilizados por el objeto Statement.
			stmt.close();
			// Cierra la conexion para evitar fugas de recursos.
			conn.close();
		}

		// Retorna el estado de la insercion.
		return insercionCompletada;
	}
}
