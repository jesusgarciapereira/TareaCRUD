package funciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que gestiona la conexion con una base de datos MySQL mediante la
 * funcion conectar().
 */
public class Conectar {

	/**
	 * Establece una conexion con la base de datos especificada.
	 * 
	 * @param url          Direccion de la base de datos (formato JDBC, por ejemplo:
	 *                     jdbc:mysql://dns11036.phdns11.es:3306)
	 * @param usuario      Nombre de usuario de la base de datos.
	 * @param contrasennia Contrasennia del usuario para acceder a la base de datos.
	 * @return Una instancia de la conexion (Connection) o null si ocurre un error.
	 */
	public static Connection conectar(String url, String usuario, String contrasennia) {

		// Objeto Connection que sera devuelto por la funcion.
		Connection conn = null;

		try {
			// Carga el controlador de MySQL JDBC.
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establece la conexion utilizando los parametros proporcionados.
			conn = DriverManager.getConnection(url, usuario, contrasennia);

			// Captura excepciones si el controlador JDBC no se encuentra en el classpath.
		} catch (ClassNotFoundException e) {
			System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"
			System.out.println("Controlador JDBC no encontrado.");

			// Captura excepciones relacionadas con errores de conexion, como credenciales
			// incorrectas o URL invalida.
		} catch (SQLException e) {
			System.out.print("\u001B[91mError: \u001B[0m"); // Color personalizado para el "error"

			if (e.getSQLState().equals("08S01"))
				System.out.println(
						"No se puede conectar al servidor. Es posible que no haya conexión a Internet o ésta sea muy lenta.");
			else if (e.getSQLState().equals("08001") || e.getSQLState().equals("28000")) {
				System.out.println("Dirección, usuario y/o contraseña incorrectos.");
			}

//			 Para averiguar lo que debe salir 
//			 System.out.println(e.getMessage());
//			 System.out.println("Estado SQL " + e.getSQLState());

		}

		// Retorna la conexión si fue exitosa; de lo contrario, retorna null.
		return conn;

	}
}
