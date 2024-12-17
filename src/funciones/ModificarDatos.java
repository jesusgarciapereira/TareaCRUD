package funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import entidades.Alumno;
import entidades.Matricula;
import entidades.Profesor;

/**
 * Clase que gestiona la modificacion de datos en una base de datos MySQL.
 */
public class ModificarDatos {

	/**
	 * Modifica los datos de una tabla de la base de datos segun los parametros
	 * proporcionados.
	 * 
	 * @param conn                    Conexion activa a la base de datos.
	 * @param nombreTabla             Nombre de la tabla.
	 * @param nombreColumnaModificada Nombre de la columna con modificacion
	 * @param nombreDatoNuevo         Nombre del valor nuevo
	 * @param nombreColumnaFiltro     Nombre de la columna que se usara como filtro
	 * @param nombreDatoFiltro        Nombre del valor que se usara como filtro
	 * @param confirmado
	 * @return `true` si los datos se modificaron con exito; `false` si ocurrio
	 *         algun error.
	 * @throws SQLException En caso de errores relacionados con la conexion o SQL.
	 */
	public static boolean modificarDato(Connection conn, String nombreTabla, String nombreColumnaModificada,
			String nombreDatoNuevo, String nombreColumnaFiltro, String nombreDatoFiltro, boolean confirmado) {

		// Declaracion de objetos necesarios para la ejecucion de la consulta.
		PreparedStatement stmt = null;
		String sql = ""; // Variable para almacenar la consulta SQL.
		int filasAfectadas;

		boolean modificadoCompleto = false; // Indicador de exito en la modificacion de campos.

		try {
			// Desactivar el modo de autocommit para manejar transacciones manualmente
			conn.setAutoCommit(false);

			// Selecciona la consulta SQL
			sql += "UPDATE " + nombreTabla + " SET " + nombreColumnaModificada + " = ? WHERE " + nombreColumnaFiltro
					+ " = ?;";

			// Pasamos la consulta al objeto PreparedStatement
			stmt = conn.prepareStatement(sql);

			// Dependiendo del nombre de la tabla, inicializa el objeto correspondiente
			switch (nombreTabla) {
			case "Profesores":
				// Crea un nuevo objeto Profesor
				Profesor profesor = new Profesor();

				// Segun el nombre de la columna modificada, establece el primer valor que se va
				// a buscar
				switch (nombreColumnaModificada) {
				case "idProfesor":
					// Convierte el dato a un numero entero
					profesor.setIdProfesor(Integer.parseInt(nombreDatoNuevo));
					stmt.setInt(1, profesor.getIdProfesor()); // Asigna el valor al PreparedStatement
					break;
				case "Nombre":
					profesor.setNombre(nombreDatoNuevo);
					stmt.setString(1, profesor.getNombre()); // Asigna el valor al PreparedStatement
					break;
				case "Apellidos":
					profesor.setApellidos(nombreDatoNuevo);
					stmt.setString(1, profesor.getApellidos()); // Asigna el valor al PreparedStatement
					break;
				case "FechaNacimiento":
					// Convierte la fecha de nacimiento de String a LocalDate
					profesor.setFechaNacimiento(LocalDate.parse(nombreDatoNuevo)); // 2000-05-15
					stmt.setString(1, profesor.getFechaNacimiento().toString()); // Asigna el valor al PreparedStatement
					break;
				case "Antiguedad":
					// Convierte el dato a un numero entero
					profesor.setAntiguedad(Integer.parseInt(nombreDatoNuevo));
					stmt.setInt(1, profesor.getAntiguedad()); // Asigna el valor al PreparedStatement
					break;
				default:
					System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"

					// Si el nombre de columna no es valido
					System.out.println("El nombre del campo especificado no es valido.");
					break;
				}

				// Segun el nombre de la columna filtro, establece el segundo valor que se va a
				// buscar
				switch (nombreColumnaFiltro) {
				case "idProfesor":
					// Convierte el dato a un numero entero
					profesor.setIdProfesor(Integer.parseInt(nombreDatoFiltro));
					stmt.setInt(2, profesor.getIdProfesor()); // Asigna el valor al PreparedStatement
					break;
				case "Nombre":
					profesor.setNombre(nombreDatoFiltro);
					stmt.setString(2, profesor.getNombre()); // Asigna el valor al PreparedStatement
					break;
				case "Apellidos":
					profesor.setApellidos(nombreDatoFiltro);
					stmt.setString(2, profesor.getApellidos()); // Asigna el valor al PreparedStatement
					break;
				case "FechaNacimiento":
					// Convierte la fecha de nacimiento de String a LocalDate
					profesor.setFechaNacimiento(LocalDate.parse(nombreDatoFiltro)); // 2000-05-15
					stmt.setString(2, profesor.getFechaNacimiento().toString()); // Asigna el valor al PreparedStatement
					break;
				case "Antiguedad":
					// Convierte el dato a un numero entero
					profesor.setAntiguedad(Integer.parseInt(nombreDatoFiltro));
					stmt.setInt(2, profesor.getAntiguedad()); // Asigna el valor al PreparedStatement
					break;
				default:
					System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"

					// Si el nombre de columna no es valido
					System.out.println("El nombre del campo especificado no es valido.");
					break;
				}
				break;

			case "Alumnos":
				// Crea un nuevo objeto Alumno
				Alumno alumno = new Alumno();

				// Segun el nombre de la columna modificada, establece el primer valor que se va
				// a buscar
				switch (nombreColumnaModificada) {
				case "idAlumno":
					// Convierte el dato a un numero entero
					alumno.setIdAlumno(Integer.parseInt(nombreDatoNuevo));
					stmt.setInt(1, alumno.getIdAlumno()); // Asigna el valor al PreparedStatement
					break;
				case "Nombre":
					alumno.setNombre(nombreDatoNuevo);
					stmt.setString(1, alumno.getNombre()); // Asigna el valor al PreparedStatement
					break;
				case "Apellidos":
					alumno.setApellidos(nombreDatoNuevo);
					stmt.setString(1, alumno.getApellidos()); // Asigna el valor al PreparedStatement
					break;
				case "FechaNacimiento":
					// Convierte la fecha de nacimiento de String a LocalDate
					alumno.setFechaNacimiento(LocalDate.parse(nombreDatoNuevo)); // 2000-05-15
					stmt.setString(1, alumno.getFechaNacimiento().toString()); // Asigna el valor al PreparedStatement
					break;
				default:
					System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"

					// Si el nombre de columna no es valido
					System.out.println("El nombre del campo especificado no es valido.");
					break;
				}

				// Segun el nombre de la columna filtro, establece el segundo valor que se va a
				// buscar
				switch (nombreColumnaFiltro) {
				case "idAlumno":
					// Convierte el dato a un numero entero
					alumno.setIdAlumno(Integer.parseInt(nombreDatoFiltro));
					stmt.setInt(2, alumno.getIdAlumno()); // Asigna el valor al PreparedStatement
					break;
				case "Nombre":
					alumno.setNombre(nombreDatoFiltro);
					stmt.setString(2, alumno.getNombre()); // Asigna el valor al PreparedStatement
					break;
				case "Apellidos":
					alumno.setApellidos(nombreDatoFiltro);
					stmt.setString(2, alumno.getApellidos()); // Asigna el valor al PreparedStatement
					break;
				case "FechaNacimiento":
					// Convierte la fecha de nacimiento de String a LocalDate
					alumno.setFechaNacimiento(LocalDate.parse(nombreDatoFiltro)); // 2000-05-15
					stmt.setString(2, alumno.getFechaNacimiento().toString()); // Asigna el valor al PreparedStatement
					break;
				default:
					System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"

					// Si el nombre de columna no es valido
					System.out.println("El nombre del campo especificado no es valido.");
					break;
				}
				break;
			case "Matriculas":
				// Crea un nuevo objeto Matricula
				Matricula matricula = new Matricula();

				// Segun el nombre de la columna modificada, establece el primer valor que se va
				// a buscar
				switch (nombreColumnaModificada) {
				case "idMatricula":
					// Convierte el dato a un numero entero
					matricula.setIdMatricula(Integer.parseInt(nombreDatoNuevo));
					stmt.setInt(1, matricula.getIdMatricula()); // Asigna el valor al PreparedStatement
					break;
				case "idProfesor":
					// Convierte el dato a un numero entero
					matricula.setIdProfesor(Integer.parseInt(nombreDatoNuevo));
					stmt.setInt(1, matricula.getIdProfesor()); // Asigna el valor al PreparedStatement
					break;
				case "idAlumno":
					// Convierte el dato a un numero entero
					matricula.setIdAlumno(Integer.parseInt(nombreDatoNuevo));
					stmt.setInt(1, matricula.getIdAlumno()); // Asigna el valor al PreparedStatement
					break;
				case "Asignatura":
					matricula.setAsignatura(nombreDatoNuevo);
					stmt.setString(1, matricula.getAsignatura()); // Asigna el valor al PreparedStatement
					break;
				case "Curso":
					// Convierte el curso de String a Integer
					matricula.setCurso(Integer.parseInt(nombreDatoNuevo));
					stmt.setInt(1, matricula.getCurso()); // Asigna el valor al PreparedStatement
					break;
				default:
					System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"

					// Si el nombre de columna no es valido
					System.out.println("El nombre del campo especificado no es valido.");
					break;
				}

				// Segun el nombre de la columna filtro, establece el segundo valor que se va a
				// buscar
				switch (nombreColumnaFiltro) {
				case "idMatricula":
					// Convierte el dato a un numero entero
					matricula.setIdMatricula(Integer.parseInt(nombreDatoFiltro));
					stmt.setInt(2, matricula.getIdMatricula()); // Asigna el valor al PreparedStatement
					break;
				case "idProfesor":
					// Convierte el dato a un numero entero
					matricula.setIdProfesor(Integer.parseInt(nombreDatoFiltro));
					stmt.setInt(2, matricula.getIdProfesor()); // Asigna el valor al PreparedStatement
					break;
				case "idAlumno":
					// Convierte el dato a un numero entero
					matricula.setIdAlumno(Integer.parseInt(nombreDatoFiltro));
					stmt.setInt(2, matricula.getIdAlumno()); // Asigna el valor al PreparedStatement
					break;
				case "Asignatura":
					matricula.setAsignatura(nombreDatoFiltro);
					stmt.setString(2, matricula.getAsignatura()); // Asigna el valor al PreparedStatement
					break;
				case "Curso":
					// Convierte el curso de String a Integer
					matricula.setCurso(Integer.parseInt(nombreDatoFiltro));
					stmt.setInt(2, matricula.getCurso()); // Asigna el valor al PreparedStatement
					break;
				default:
					System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"

					// Si el nombre de columna no es valido
					System.out.println("El nombre del campo especificado no es valido.");
					break;
				}
				break;

			// Si el nombre de la tabla no es valido
			default:
				System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"

				System.out.println("El nombre de la tabla especificado no es valido.");
				break;
			}

			// Ejecuta la sentencia SQL
			stmt.execute();

			// Segun la confirmacion del usuario
			if (confirmado) {
				conn.commit(); // Confirmamos la transaccion
				modificadoCompleto = true; // Marca el actualizado como exitoso.
				// System.out.println("Cambios confirmados");
//				System.out.println("Cambios confirmados. Filas afectadas: " +
//				filasAfectadas);

			} else {
				conn.rollback(); // Revertimos la transaccion
				// System.out.println("Cambios revertidos.");
			}
			// Captura errores relacionados con la ejecucion de la consulta SQL.
		} catch (SQLException e) {
			if (!confirmado) {
				System.out.print("\u001B[91mOperación cancelada: \u001B[0m"); // Color personalizado para el "error"

				System.out.println("De todas maneras, esta operación no podría realizarse");
			}
			System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
			if (e.getSQLState().equals("42S02")) {
				System.out.println("La tabla '" + nombreTabla + "' no existe en la Base de Datos");

				System.out.println(
						"Sugerencia: Primero, siga los pasos de la opción 2 (Crear Tablas) y una vez creadas, se podrán modificar los datos con la opción 6 (Insertar Datos)");

			} else if (e.getSQLState().equals("23000")) {
				System.out.println("Violación de restricción de clave foránea definida en la Tabla 'Matriculas'");
				if (!nombreTabla.equals("Matriculas")) {
					System.out.println("Sugerencia: Modifique o borre los " + nombreColumnaModificada
							+ " de la Tabla 'Matriculas' antes de modificar el " + nombreColumnaModificada
							+ " de la Tabla '" + nombreTabla + "'");
				} else {
					System.out.println(
							"Sugerencia: Asegúrese de que el nuevo idProfesor esté presente en la Tabla 'Profesores' y/o el nuevo idAlumno esté presente en la Tabla 'Alumnos'");
				}

			} else {
				System.out.println("Se ha producido un error");
				System.out.println("Reinicie la App y MySQL Workbench si lo tiene abierto");
			}

//			 Para averiguar lo que debe salir 
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

		// Retorna si la modificacion fue exitosa o no.
		return modificadoCompleto;
	}

}
