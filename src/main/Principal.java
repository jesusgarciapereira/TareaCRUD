package main;

import java.io.Console;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import funciones.BorrarDatos;
import funciones.Conectar;
import funciones.CrearTabla;
import funciones.InsertarDato;

public class Principal {

	public static void main(String[] args) {

		// COMPROBAR BORRAR UN DATO
		Connection conexion = Conectar.conectar("jdbc:mysql://dns11036.phdns11.es:3306/ad2425_jgarcia", "jgarcia",
				"12345");
		boolean borradoCompletado = false;
//		String nombreTabla = "Profesores";
		
//		String nombreColumna = "Nombre";
//		String nombreColumna = "Apellidos";
//		String nombreColumna = "FechaNacimiento";
//		String nombreColumna = "Antiguedad";

		String nombreTabla = "Alumnos";
		
		String nombreColumna = "Nombre";
//		String nombreColumna = "Apellidos";
//		String nombreColumna = "FechaNacimiento";		
		
//		String nombreTabla = "Matriculas";
//		
//		String nombreColumna = "Asignatura";
//		String nombreColumna = "Curso";

		String nombreDato = "Carlos";

		System.out.println("¿Seguro que quieres borrar este dato de la tabla " + nombreTabla + "?");
		try {
			borradoCompletado = BorrarDatos.borrarDatoConcreto(conexion, nombreTabla, nombreColumna, nombreDato, true);
			if (borradoCompletado) {
				System.out.println("Dato borrado correctamente de la tabla " + nombreTabla);
			} else {
				System.err.println("Error: No se han podido borrar los datos de la tabla " + nombreTabla);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		// COMPROBAR BORRAR TODOS LOS DATOS
//		Connection conexion = Conectar.conectar("jdbc:mysql://dns11036.phdns11.es:3306/ad2425_jgarcia", "jgarcia",
//				"12345");
//		boolean borradoCompletado = false;
////		String nombreTabla = "Profesores";
////		String nombreTabla = "Alumnos";
//		String nombreTabla = "Matriculas";
//
//		System.out.println("¿Seguro que quieres borrar todos los datos de la tabla " + nombreTabla + "?");
//		try {
//			borradoCompletado = BorrarDatos.borrarTodosDatos(conexion, nombreTabla, false);
//			if (borradoCompletado) {
//				System.out.println("Datos borrados correctamente de la tabla " + nombreTabla);
//			} else {
//				System.err.println("Error: No se han podido borrar los datos de la tabla " + nombreTabla);
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// COMPROBAR INSERTAR DATO
//		Connection conexion = Conectar.conectar("jdbc:mysql://dns11036.phdns11.es:3306/ad2425_jgarcia", "jgarcia",
//				"12345");
//		boolean datoInsertado = false;
////		String nombreTabla = "Profesores";
////		String[] campos = {"Juan", "Pérez", "1980-05-12", "10"};
////		String nombreTabla = "Alumnos";
////		String[] campos = {"Carlos", "López", "2005-07-12"};
//		String nombreTabla = "Matriculas";
//		String[] campos = {"1", "1", "Matemáticas", "2023"};
//
//		try {
//			datoInsertado = InsertarDato.insertarDato(conexion, nombreTabla, campos);
//
//			if (datoInsertado) {
//				System.out.println("Datos insertados correctamente en la tabla " + nombreTabla);
//			} else {
//				System.err.println("Error: No se han podido insertar los datos en la tabla " + nombreTabla);
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// COMPROBAR CREAR TABLA
//		Connection conexion = Conectar.conectar("jdbc:mysql://dns11036.phdns11.es:3306/ad2425_jgarcia", "jgarcia", "12345");
//		boolean tablaCreada = false;
//		String nombreTabla = "Matriculas";
//		
//		try {
//			tablaCreada = CrearTabla.crearTabla(conexion, nombreTabla);
//			if(tablaCreada) {
//				System.out.println("Tabla " + nombreTabla + " creada correctamente");
//			} else {
//				System.err.println("Error: No se ha podido crear la tabla " + nombreTabla);
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		// COMPROBAR CONECTAR
//		Connection conexion = Conectar.conectar("jdbc:mysql://dns11036.phdns11.es:3306/ad2425_jgarcia", "jgarcia", "12345");
//
//		if (conexion == null) {
//			System.err.println("Error: No se ha podido llevar a cabo la conexion");
//		} else {
//			System.out.println("Conexión exitosa");
//		}

		// CONTRASEÑA

//		Console console = System.console();
//		char[] passwordArray = console.readPassword("Introduce la contraseña");
//		Arrays.fill(passwordArray, ' ');

	}

}
