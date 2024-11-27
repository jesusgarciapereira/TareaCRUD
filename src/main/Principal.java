package main;

import java.sql.Connection;
import java.sql.SQLException;

import funciones.Conectar;
import funciones.CrearTabla;

public class Principal {

	public static void main(String[] args) {
		
		Connection conexion = Conectar.conectar("jdbc:mysql://dns11036.phdns11.es:3306/ad2425_jgarcia", "jgarcia", "12345");
		boolean tablaCreada = false;
		String nombreTabla = "Profesores";
		
		try {
			tablaCreada = CrearTabla.crearTabla(conexion, nombreTabla);
			if(tablaCreada) {
				System.out.println("Tabla " + nombreTabla + " creada correctamente");
			} else {
				System.out.println("No se ha podido crear la tabla " + nombreTabla);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	
//		// COMPROBAR CONECTAR
//		Connection conexion = Conectar.conectar("jdbc:mysql://dns11036.phdns11.es:3306/ad2425_jgarcia", "jgarcia", "12345");
//
//		if (conexion == null) {
//			System.out.println("No se ha podido llevar a cabo la conexion");
//		} else {
//			System.out.println("Conexión exitosa");
//		}

	}

}
