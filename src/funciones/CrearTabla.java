package funciones;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase que gestiona la creación de tablas en una base de datos MySQL.
 */
public class CrearTabla {

    /**
     * Crea una tabla en la base de datos segun el nombre proporcionado.
     * 
     * @param conn       Conexion activa a la base de datos.
     * @param nombreTabla Nombre de la tabla a crear. Valores validos: "Profesores", "Alumnos", "Matriculas".
     * @return `true` si la tabla se creo con exito; `false` si ocurrio algún error.
     * @throws SQLException En caso de errores relacionados con la conexion o SQL.
     */
    public static boolean crearTabla(Connection conn, String nombreTabla) throws SQLException {
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
                    sql += "CREATE TABLE Profesores (\n"
                        + "    idProfesor INT AUTO_INCREMENT,\n"
                        + "    Nombre VARCHAR(45),\n"
                        + "    Apellidos VARCHAR(45),\n"
                        + "    FechaNacimiento DATE,\n"
                        + "    Antiguedad INT,\n"
                        + "    CONSTRAINT PK_Profesores PRIMARY KEY (idProfesor)\n"
                        + ");";
                    break;

                case "Alumnos":
                    sql += "CREATE TABLE Alumnos (\n"
                        + "    idAlumno INT AUTO_INCREMENT,\n"
                        + "    Nombre VARCHAR(45),\n"
                        + "    Apellidos VARCHAR(45),\n"
                        + "    FechaNacimiento DATE,\n"
                        + "    CONSTRAINT PK_Alumnos PRIMARY KEY (idAlumno)\n"
                        + ");";
                    break;

                case "Matriculas":
                    sql += "CREATE TABLE Matriculas (\n"
                        + "    idMatricula INT AUTO_INCREMENT,\n"
                        + "    idProfesor INT,\n"
                        + "    idAlumno INT,\n"
                        + "    Asignatura VARCHAR(45),\n"
                        + "    Curso INT,\n"
                        + "    CONSTRAINT PK_Matriculas PRIMARY KEY (idMatricula),\n"
                        + "    CONSTRAINT FK_Profesores FOREIGN KEY (idProfesor) REFERENCES Profesores (idProfesor),\n"
                        + "    CONSTRAINT FK_Alumnos FOREIGN KEY (idAlumno) REFERENCES Alumnos (idAlumno)\n"
                        + ");";
                    break;
                   	// Si el nombre no es valido.
                default:
                    System.err.println("Error: El nombre de la tabla especificado no es válido.");
                    
            }

            // Ejecuta la consulta SQL para crear la tabla.
            stmt.executeUpdate(sql);
            creacionCompletada = true; // Marca la creacion como exitosa.
            
            // Captura errores relacionados con la ejecucion de la consulta SQL.
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            // Libera recursos utilizados por el objeto Statement.
                stmt.close();
            // Cierra la conexion para evitar fugas de recursos.
                conn.close();
        }

        // Retorna el estado de la creacion de la tabla.
        return creacionCompletada;
    }
}