package funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que gestiona el listado de tablas en una base de datos MySQL.
 */
public class ListarTablas {

	/**
     * Metodo para listar todas las filas de una tabla en la base de datos.
     * 
     * @param conn       Objeto Connection que representa la conexion a la base de datos.
     * @param nombreTabla Nombre de la tabla que se desea listar.
     */
	public static void listarTodo(Connection conn, String nombreTabla) {
		
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

			// Muestra el nombre de la tabla a listar
			System.out.println(!nombreTabla.equals("Alumnos") ? "--------------" : "-----------");
			System.out.println("| " + nombreTabla + " |");
			System.out.println(!nombreTabla.equals("Alumnos") ? "--------------" : "-----------");

			// Dependiendo del nombre de la tabla, mostrara los datos correspondientes
			switch (nombreTabla) {
			case "Profesores":
				// Iteramos por los resultados obtenidos de la consulta.
				while (resultado.next()) {

					System.out.println("ID del Profesor: " + resultado.getInt("idProfesor"));
					System.out.println("Nombre: " + resultado.getString("Nombre"));
					System.out.println("Apellidos: " + resultado.getString("Apellidos"));
					System.out.println("Fecha de Nacimiento: " + resultado.getString("FechaNacimiento"));
					System.out.println("Antigüedad: " + resultado.getInt("Antiguedad"));
					System.out.println("===============================");
				}
				break;
			case "Alumnos":
				// Iteramos por los resultados obtenidos de la consulta.
				while (resultado.next()) {

					System.out.println("ID del Alumno: " + resultado.getInt("idAlumno"));
					System.out.println("Nombre: " + resultado.getString("Nombre"));
					System.out.println("Apellidos: " + resultado.getString("Apellidos"));
					System.out.println("Fecha de Nacimiento: " + resultado.getString("FechaNacimiento"));
					System.out.println("===============================");
				}
				break;
			case "Matriculas":
				// Iteramos por los resultados obtenidos de la consulta.
				while (resultado.next()) {
					System.out.println("ID de la Matrícula: " + resultado.getInt("idMatricula"));
					System.out.println("ID del Profesor: " + resultado.getInt("idProfesor"));
					System.out.println("ID del Alumno: " + resultado.getInt("idAlumno"));
					System.out.println("Nombre: " + resultado.getString("Asignatura"));
					System.out.println("Curso: " + resultado.getInt("Curso"));
					System.out.println("==========================");

				}
				break;

			// Si el nombre de la tabla no es valido
			default:
				System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
				System.out.println("Error: El nombre de la tabla especificado no es valido.");
				break;
			}

			// Captura errores relacionados con la ejecucion de la consulta SQL.
		} catch (SQLException e) {
			System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"

			if (e.getSQLState().equals("42S02")) {
				System.out.println("La tabla '" + nombreTabla + "' no existe en la Base de Datos");

				System.out.println(
						"Sugerencia: Primero, siga los pasos de la opción 2 (Crear Tablas) y una vez creadas, se podrán mostrar con la opción 4 (Listar Tablas)");

			}
			else {
				System.out.println("Se ha producido un error");
				System.out.println("Reinicie la App y MySQL Workbench si lo tiene abierto");
			}
//			 Para averiguar lo que debe salir 
//			System.out.println(e.getMessage());
//			System.out.println("Estado SQL " + e.getSQLState());
//			e.printStackTrace();
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
	}

	/**
     * Metodo para listar datos de una tabla aplicando un filtro especifico.
     * 
     * @param conn               Objeto Connection que representa la conexion a la base de datos.
     * @param nombreTabla        Nombre de la tabla a consultar.
     * @param nombreColumnaFiltro Nombre de la columna sobre la que se aplicara el filtro.
     * @param opcionFiltro       Operador del filtro (por ejemplo, '=', 'LIKE', etc.).
     * @param nombreDatoFiltro   Valor del filtro a aplicar.
     */
	public static void listarFiltrado(Connection conn, String nombreTabla, String nombreColumnaFiltro,
			String opcionFiltro, String nombreDatoFiltro) {
		
		// Declaracion de objetos necesarios para la ejecucion de la consulta.
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		String sql = ""; // Variable para almacenar la consulta SQL.

		try {
			// Selecciona la consulta SQL
			sql += "SELECT * FROM " + nombreTabla + " WHERE " + nombreColumnaFiltro + " " + opcionFiltro + " "
					+ nombreDatoFiltro;

			// Pasamos la consulta al objeto PreparedStatement.
			stmt = conn.prepareStatement(sql);

			// Ejecutamos la consulta y la almacenamos al ResultSet.
			resultado = stmt.executeQuery();

			// Muestra el nombre de la tabla a listar
			System.out.println(!nombreTabla.equals("Alumnos") ? "--------------" : "-----------");
			System.out.println("| " + nombreTabla + " |");
			System.out.println(!nombreTabla.equals("Alumnos") ? "--------------" : "-----------");

			// Dependiendo del nombre de la tabla, mostrara los datos correspondientes
			switch (nombreTabla) {
			case "Profesores":
				// Iteramos por los resultados obtenidos de la consulta.
				while (resultado.next()) {
					System.out.println("ID del Profesor: " + resultado.getInt("idProfesor"));
					System.out.println("Nombre: " + resultado.getString("Nombre"));
					System.out.println("Apellidos: " + resultado.getString("Apellidos"));
					System.out.println("Fecha de Nacimiento: " + resultado.getString("FechaNacimiento"));
					System.out.println("Antigüedad: " + resultado.getInt("Antiguedad"));
					System.out.println("==============================================");
				}
				break;
			case "Alumnos":
				// Iteramos por los resultados obtenidos de la consulta.
				while (resultado.next()) {
					System.out.println("ID del Alumno: " + resultado.getInt("idAlumno"));
					System.out.println("Nombre: " + resultado.getString("Nombre"));
					System.out.println("Apellidos: " + resultado.getString("Apellidos"));
					System.out.println("Fecha de Nacimiento: " + resultado.getString("FechaNacimiento"));
					System.out.println("==============================================");
				}
				break;
			case "Matriculas":
				// Iteramos por los resultados obtenidos de la consulta.
				while (resultado.next()) {
					System.out.println("ID de la Matrícula: " + resultado.getInt("idMatricula"));
					System.out.println("ID del Profesor: " + resultado.getInt("idProfesor"));
					System.out.println("ID del Alumno: " + resultado.getInt("idAlumno"));
					System.out.println("Nombre: " + resultado.getString("Asignatura"));
					System.out.println("Curso: " + resultado.getInt("Curso"));
					System.out.println("==============================================");
				}
				break;

			// Si el nombre de la tabla no es valido
			default:
				System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
				System.out.println("Error: El nombre de la tabla especificado no es valido.");
				break;
			}

			// Captura errores relacionados con la ejecucion de la consulta SQL.
		} catch (SQLException e) {

			System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
			if (e.getSQLState().equals("42S02")) {
				System.out.println("La tabla '" + nombreTabla + "' no existe en la Base de Datos");

				System.out.println(
						"Sugerencia: Primero, siga los pasos de la opción 2 (Crear Tablas) y una vez creadas, se podrán mostrar con la opción 4 (Listar Tablas)");

			}else {
				System.out.println("Se ha producido un error");
				System.out.println("Reinicie la App y MySQL Workbench si lo tiene abierto");
			}
//		 Para averiguar lo que debe salir 
//		System.out.println(e.getMessage());
//		System.out.println("Estado SQL " + e.getSQLState());
//		e.printStackTrace();
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
	}
}