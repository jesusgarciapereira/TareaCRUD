package funciones;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearTabla {
	// Ofrecer al usuario la opcion de crear las 3 tablas o solo una
	public static boolean crearTabla(Connection conn, String nombreTabla) throws SQLException {
		Statement stmt = null;
		String sql = "";
		
		boolean creacionCompletada = false;
		
		try {
			stmt = conn.createStatement();
			
			switch (nombreTabla) {
			case "Profesores":
				sql += "CREATE TABLE Profesores (\r\n"
						+ "    idProfesor INT AUTO_INCREMENT,\r\n"
						+ "    Nombre VARCHAR(45),\r\n"
						+ "    Apellidos VARCHAR(45),\r\n"
						+ "    FechaNacimiento DATE,\r\n"
						+ "    Antiguedad INT,\r\n"
						+ "\r\n"
						+ "	CONSTRAINT PK_Profesores PRIMARY KEY (idProfesor)\r\n"
						+ ");";
				break;
				
			case "Alumnos":
				sql += "CREATE TABLE Alumnos (\r\n"
						+ "    idAlumno INT AUTO_INCREMENT,\r\n"
						+ "    Nombre VARCHAR(45),\r\n"
						+ "    Apellidos VARCHAR(45),\r\n"
						+ "    FechaNacimiento DATE,\r\n"
						+ "\r\n"
						+ "	CONSTRAINT PK_Alumnos PRIMARY KEY (idAlumno)\r\n"
						+ ");";
				break;
				
			case "Matriculas":
				sql += "CREATE TABLE Matriculas (\r\n"
						+ "    idMatricula INT AUTO_INCREMENT,\r\n"
						+ "    idProfesor INT,\r\n"
						+ "    idAlumno INT,\r\n"
						+ "    Asignatura VARCHAR(45),\r\n"
						+ "    Curso INT,\r\n"
						+ "\r\n"
						+ "	CONSTRAINT PK_Matriculas PRIMARY KEY (idMatricula),\r\n"
						+ "    CONSTRAINT FK_Profesores FOREIGN KEY (idProfesor) REFERENCES Profesores (idProfesor),\r\n"
						+ "    CONSTRAINT FK_Alumnos FOREIGN KEY (idAlumno) REFERENCES Alumnos (idAlumno)\r\n"
						+ ");";
				break;

			default:

				break;
			}
			
			stmt.executeUpdate(sql);
			creacionCompletada = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			stmt.close();
			conn.close();
		}


		
		return creacionCompletada;
	}
}
