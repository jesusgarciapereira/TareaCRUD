package funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que gestiona el listado de tablas en una base de datos MySQL.
 */
public class ListarTablas {

	public static void listarTodo(Connection conn, String nombreTabla) throws SQLException {
		// Declaracion de objetos necesarios para la ejecucion de la consulta.
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		String sql = ""; // Variable para almacenar la consulta SQL.

		try {
			// Selecciona la consulta SQL
			sql += "SELECT * FROM " + nombreTabla;

			// Pasamos la consulta al objeto PreparedStatement.
			stmt = conn.prepareStatement(sql);

			// Ejecutamos la consulta y la almacenamos al ResultSet.
			resultado = stmt.executeQuery();

			System.out.println("Tabla " + nombreTabla);

			// Dependiendo del nombre de la tabla, mostrara los datos correspondientes
			switch (nombreTabla) {
			case "Profesores":
				// Iteramos por los resultados obtenidos de la consulta.
				while (resultado.next()) {
					System.out.println("===============================");
					System.out.println("ID del Profesor: " + resultado.getInt("idProfesor"));
					System.out.println("Nombre: " + resultado.getString("Nombre"));
					System.out.println("Apellidos: " + resultado.getString("Apellidos"));
					System.out.println("Fecha de Nacimiento: " + resultado.getString("FechaNacimiento"));
					System.out.println("Antigüedad: " + resultado.getInt("Antiguedad"));
				}
				break;
			case "Alumnos":
				// Iteramos por los resultados obtenidos de la consulta.
				while (resultado.next()) {
					System.out.println("===============================");
					System.out.println("ID del Alumno: " + resultado.getInt("idAlumno"));
					System.out.println("Nombre: " + resultado.getString("Nombre"));
					System.out.println("Apellidos: " + resultado.getString("Apellidos"));
					System.out.println("Fecha de Nacimiento: " + resultado.getString("FechaNacimiento"));
				}
				break;
			case "Matriculas":
				// Iteramos por los resultados obtenidos de la consulta.
				while (resultado.next()) {
					System.out.println("==========================");
					System.out.println("ID de la Matrícula: " + resultado.getInt("idMatricula"));
					System.out.println("ID del Profesor: " + resultado.getInt("idProfesor"));
					System.out.println("ID del Alumno: " + resultado.getInt("idAlumno"));
					System.out.println("Nombre: " + resultado.getString("Asignatura"));
					System.out.println("Curso: " + resultado.getInt("Curso"));
				}
				break;

			// Si el nombre de la tabla no es valido
			default:
				System.err.println("Error: El nombre de la tabla especificado no es valido.");
				break;
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
	}
}