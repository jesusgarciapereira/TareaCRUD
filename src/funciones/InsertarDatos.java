package funciones;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase que gestiona la insercion de datos en una base de datos MySQL.
 */
public class InsertarDatos {

	/**
	 * Inserta un dato en una tabla de la base de datos segun los parametros
	 * proporcionados.
	 * 
	 * @param conn        Conexion activa a la base de datos.
	 * @param nombreTabla Nombre de la tabla. Valores validos: "Profesores",
	 *                    "Alumnos", "Matriculas".
	 * @param campos      Array de tipo String con cada uno de los campos a insertar
	 * @return `true` si el dato se inserto con exito; `false` si ocurrio algún
	 *         error.
	 * @throws SQLException En caso de errores relacionados con la conexion o SQL.
	 */
	public static boolean insertarDato(Connection conn, String nombreTabla, String[] campos) {

		// Declaracion de objetos necesarios para la ejecución de la consulta.
		Statement stmt = null;
		String sql = ""; // Variable para almacenar la consulta SQL.

		boolean insercionCompletada = false; // Indicador de exito en la insercion de datos.

		try {
			// Inicializa el objeto Statement para ejecutar consultas SQL.
			stmt = conn.createStatement();

			// Selecciona la consulta SQL
			sql += "INSERT INTO " + nombreTabla + " VALUES ";

			// Concatena los campos en la consulta SQL segun el nombre de la tabla
			// especificado.
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
			// Si el nombre no es valido.
			default:
				System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"

				System.out.println("El nombre de la tabla especificado no es válido.");
				break;
			}
			// Ejecuta la consulta SQL para insertar dato.
			stmt.executeUpdate(sql);
			insercionCompletada = true; // Marca la insercion como exitosa.

			// Captura errores relacionados con la ejecucion de la consulta SQL.
		} catch (SQLException e) {
			System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
			if (e.getSQLState().equals("42S02")) {
				System.out.println("La tabla '" + nombreTabla + "' no existe en la Base de Datos");

				System.out.println(
						"Sugerencia: Primero, siga los pasos de la opción 2 (Crear Tablas) y una vez creadas, se podrán insertar los datos con la opción 5 (Insertar Datos)");

			}
			
			else if (e.getSQLState().equals("23000")) {
				System.out.println("Violación de restricción de clave foránea definida en la Tabla '" + nombreTabla + "'");
				System.out.println("Sugerencia: Introduzca un idProfesor presente en la Tabla 'Profesores' y un idAlumno presente en la Tabla 'Alumnos'");
			
			}
			else {
				System.out.println("Se ha producido un error");
				System.out.println("Reinicie la App y MySQL Workbench si lo tiene abierto");
			}
			
			
//			 Para averiguar lo que debe salir 
//		System.out.println(e.getMessage());
//		System.out.println("Estado SQL " + e.getSQLState());
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

		// Retorna el estado de la insercion.
		return insercionCompletada;
	}
}
