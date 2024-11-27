package paquete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

		// Objeto Connection que sera devuelto por la fucion.
		Connection conn = null;

		try {
			// Carga el controlador de MySQL JDBC.
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establece la conexion utilizando los parametros proporcionados.
			conn = DriverManager.getConnection(url, usuario, contrasennia);
			
			// Captura excepciones si el controlador JDBC no se encuentra en el classpath.
		} catch (ClassNotFoundException e) {
			System.err.println("Error: Controlador JDBC no encontrado.");
			e.printStackTrace();
			// Captura excepciones relacionadas con errores de conexion, como credenciales
			// incorrectas o URL invalida.
		} catch (SQLException e) {
			System.err.println("Error: No se pudo establecer la conexión con la base de datos.");
			e.printStackTrace();
		}
		
		// Retorna la conexión si fue exitosa; de lo contrario, retorna null.
		return conn;

	}
}
