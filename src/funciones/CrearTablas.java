package funciones;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase que gestiona la creacion de tablas en una base de datos MySQL.
 */
public class CrearTablas {

	/**
	 * Crea una tabla en la base de datos segun el nombre proporcionado.
	 * @param conn Conexion activa a la base de datos.
	 * @param nombreTabla Nombre de la tabla a crear. Valores validos: "Profesores",
	 *                    "Alumnos", "Matriculas".
	 * @return `true` si la tabla se creo con exito; `false` si ocurrio algún error.
	 */
	public static boolean crearTabla(Connection conn, String nombreTabla) {
		
		// Declaracion de objetos necesarios para la ejecución de la consulta.
		Statement stmt = null;
		String sql = ""; // Variable para almacenar la consulta SQL.

		boolean creacionCompletada = false; // Indicador de exito en la creacion de la tabla.

		try {
			// Inicializa el objeto Statement para ejecutar consultas SQL.
			stmt = conn.createStatement();

			// Selecciona la consulta SQL segun el nombre de la tabla especificado.
			switch (nombreTabla) {
			case "Profesores":
				sql += "CREATE TABLE Profesores (\n" + "    idProfesor INT AUTO_INCREMENT,\n"
						+ "    Nombre VARCHAR(45),\n" + "    Apellidos VARCHAR(45),\n" + "    FechaNacimiento DATE,\n"
						+ "    Antiguedad INT,\n" + "    CONSTRAINT PK_Profesores PRIMARY KEY (idProfesor)\n" + ");";
				break;

			case "Alumnos":
				sql += "CREATE TABLE Alumnos (\n" + "    idAlumno INT AUTO_INCREMENT,\n" + "    Nombre VARCHAR(45),\n"
						+ "    Apellidos VARCHAR(45),\n" + "    FechaNacimiento DATE,\n"
						+ "    CONSTRAINT PK_Alumnos PRIMARY KEY (idAlumno)\n" + ");";
				break;

			case "Matriculas":
				sql += "CREATE TABLE Matriculas (\n" + "    idMatricula INT AUTO_INCREMENT,\n" + "    idProfesor INT,\n"
						+ "    idAlumno INT,\n" + "    Asignatura VARCHAR(45),\n" + "    Curso INT,\n"
						+ "    CONSTRAINT PK_Matriculas PRIMARY KEY (idMatricula),\n"
						+ "    CONSTRAINT FK_Profesores FOREIGN KEY (idProfesor) REFERENCES Profesores (idProfesor),\n"
						+ "    CONSTRAINT FK_Alumnos FOREIGN KEY (idAlumno) REFERENCES Alumnos (idAlumno)\n" + ");";
				break;
			// Si el nombre no es valido.
			default:
				System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
				System.out.println("El nombre de la tabla '" + nombreTabla + "' no es válido.");
				break;
			}

			// Ejecuta la consulta SQL para crear la tabla.
			stmt.executeUpdate(sql);
			creacionCompletada = true; // Marca la creacion como exitosa.

			// Captura errores relacionados con la ejecucion de la consulta SQL.
		} catch (SQLException e) {
			System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"

			if (e.getSQLState().equals("42S01")) {
				System.out.println("La tabla '" + nombreTabla + "' ya existe en la Base de Datos");
			}
			else if (e.getSQLState().equals("HY000")) {
				System.out.println("No se pudo crear la tabla '" + nombreTabla + "' porque tiene relaciones con tablas maestras que aún no existen");
				System.out.println("Sugerencia: Crear primero la tabla 'Profesores' y la tabla 'Alumnos'");
			}
			else {
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
		return creacionCompletada;
	}
}