package main;

import java.sql.Connection;
import java.sql.SQLException;

import funciones.Conectar;
import funciones.CrearTabla;
import funciones.InsertarDato;

public class Principal {

	public static void main(String[] args) {

		Connection conexion = Conectar.conectar("jdbc:mysql://dns11036.phdns11.es:3306/ad2425_jgarcia", "jgarcia",
				"12345");
		boolean datoInsertado = false;
//		String nombreTabla = "Profesores";
//		String[] campos = {"Juan", "Pérez", "1980-05-12", "10"};
//		String nombreTabla = "Alumnos";
//		String[] campos = {"Carlos", "López", "2005-07-12"};
		String nombreTabla = "Matriculas";
		String[] campos = {"4", "4", "Matemáticas", "2023"};

		try {
			datoInsertado = InsertarDato.insertarDato(conexion, nombreTabla, campos);

			if (datoInsertado) {
				System.out.println("Datos insertados correctamente en le tabla " + nombreTabla);
			} else {
				System.err.println("Error: No se ha podido insertar los datos en la tabla " + nombreTabla);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// COMPROBAR CCREAR TABLA
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

	}

}
