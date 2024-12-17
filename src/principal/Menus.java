package principal;

/**
 * Clase que gestiona los menus y submenus.
 */
public class Menus {
	
	static void submenuModificarColumnasProfesores() {
		System.out.println("Modificado de Tabla 'Profesores:");
		System.out.println("===============================");
		System.out.println("1. idProfesor.");
		System.out.println("2. Nombre.");
		System.out.println("3. Apellidos.");
		System.out.println("4. FechaNacimiento");
		System.out.println("5. Antiguedad.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	static void submenuModificarColumnasAlumnos() {
		System.out.println("Modificado de Tabla 'Alumnos:");
		System.out.println("============================");
		System.out.println("1. idAlumno.");
		System.out.println("2. Nombre.");
		System.out.println("3. Apellidos.");
		System.out.println("4. FechaNacimiento");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	static void submenuModificarColumnasMatriculas() {
		System.out.println("Modificado de Tabla 'Matriculas:");
		System.out.println("===============================");
		System.out.println("1. idMatricula.");
		System.out.println("2. idProfesor.");
		System.out.println("3. idAlumno.");
		System.out.println("4. Asignatura.");
		System.out.println("5. Curso.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}
	
	/**
	 * Submenu de Modificar Datos
	 */
	static void subMenuModificarDatos() {
		System.out.println("Modificar Datos:");
		System.out.println("===============");
		System.out.println("1. Profesores.");
		System.out.println("2. Alumnos.");
		System.out.println("3. Matriculas.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.println(
				"Consejo: Antes de Modificar Datos de una Tabla, asegúrese de que dicha Tabla existe eligiendo primero la opción 4 del Menú Principal (Listar Tablas)");
		
		System.out.print("Escriba una opción: ");
	}
	
	/**
	 * Submenu de Insertar Datos
	 */
	static void subMenuInsertarDatos() {
		System.out.println("Insertar Datos:");
		System.out.println("===============");
		System.out.println("1. Profesores.");
		System.out.println("2. Alumnos.");
		System.out.println("3. Matriculas.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.println(
				"Consejo: Antes de Insertar Datos en una Tabla, asegúrese de que dicha Tabla existe eligiendo primero la opción 4 del Menú Principal (Listar Tablas)");
		
		System.out.print("Escriba una opción: ");
	}

	static void submenuOpcionFiltroVARCHAR() {
		System.out.println("Opción filtro:");
		System.out.println("==============");
		System.out.println("1. =");
		System.out.println("2. LIKE");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	static void submenuOpcionFiltroINT() {
		System.out.println("Opción filtro:");
		System.out.println("==============");
		System.out.println("1. =");
		System.out.println("2. >");
		System.out.println("3. <");
		System.out.println("4. >=");
		System.out.println("5. <=");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	static void submenuFiltrarColumnasProfesores() {
		System.out.println("Filtrado de Tabla 'Profesores:");
		System.out.println("==============================");
		System.out.println("1. idProfesor.");
		System.out.println("2. Nombre.");
		System.out.println("3. Apellidos.");
		System.out.println("4. FechaNacimiento");
		System.out.println("5. Antiguedad.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	static void submenuFiltrarColumnasAlumnos() {
		System.out.println("Filtrado de Tabla 'Alumnos:");
		System.out.println("===========================");
		System.out.println("1. idAlumno.");
		System.out.println("2. Nombre.");
		System.out.println("3. Apellidos.");
		System.out.println("4. FechaNacimiento");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	static void submenuFiltrarColumnasMatriculas() {
		System.out.println("Filtrado de Tabla 'Matriculas:");
		System.out.println("==============================");
		System.out.println("1. idMatricula.");
		System.out.println("2. idProfesor.");
		System.out.println("3. idAlumno.");
		System.out.println("4. Asignatura.");
		System.out.println("5. Curso.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	static void submenuCompletoOFiltrado(String nombreTabla) {
		System.out.println("Listar Tabla '" + nombreTabla + "':");
		System.out.println("=========================");
		System.out.println("1. Completa.");
		System.out.println("2. Filtrada.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.println(
				"Consejo: Antes de listar de manera filtrada, asegúrese de que dicha Tabla existe eligiendo primero la opción 1 (Completa)");
		
		System.out.print("Escriba una opción: ");
	}

	/**
	 * Submenu de Listar una Tabla en concreto
	 */
	static void subMenuListarUnaTablaConcreta() {
		System.out.println("Listar una Tabla en concreto:");
		System.out.println("=============================");
		System.out.println("1. Profesores.");
		System.out.println("2. Alumnos.");
		System.out.println("3. Matriculas.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	/**
	 * Submenu de Crear Tablas
	 */
	static void subMenuListarTablas() {
		System.out.println("Listar Tablas:");
		System.out.println("==============");
		System.out.println("1. Listar todas las Tablas.");
		System.out.println("2. Listar una Tabla en concreto.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	/**
	 * Submenu de Eliminar una Tabla en concreto
	 */
	static void subMenuEliminarUnaTablaConcreta() {
		System.out.println("Eliminar una Tabla en concreto:");
		System.out.println("===============================");
		System.out.println("1. Profesores.");
		System.out.println("2. Alumnos.");
		System.out.println("3. Matriculas.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	/**
	 * Submenu de Crear Tablas
	 */
	static void subMenuCrearTablas() {
		System.out.println("Crear Tablas:");
		System.out.println("=============");
		System.out.println("1. Crear todas las Tablas.");
		System.out.println("2. Crear una Tabla en concreto.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	/**
	 * Submenu de Crear una Tabla en concreto
	 */
	static void subMenuCrearUnaTablaConcreta() {
		System.out.println("Crear una Tabla en concreto:");
		System.out.println("============================");
		System.out.println("1. Profesores.");
		System.out.println("2. Alumnos.");
		System.out.println("3. Matriculas.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	// Pendiente

	/**
	 * Submenu de eliminar Tablas
	 */
	static void subMenuEliminarTablas() {
		System.out.println("Eliminar Tablas:");
		System.out.println("================");
		System.out.println("1. Eliminar todas las Tablas.");
		System.out.println("2. Eliminar una Tabla en concreto.");
		System.out.println("0. Volver al Menú anterior.");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}

	/**
	 * Menu principal del programa que muestra las diferentes opciones
	 */
	static void menuPrincipal() {
		System.out.println("Menú Principal:");
		System.out.println("===============");
		System.out.println("1. Conectar con la Base de Datos.");
		System.out.println("2. Crear Tablas.");
		System.out.println("3. Eliminar Tablas.");
		System.out.println("4. Listar Tablas.");
		System.out.println("5. Insertar Datos.");
		System.out.println("6. Modificar Datos.");
		System.out.println("7. Borrar Datos.");
		System.out.println("0. Salir del Programa");
		System.out.println();
		System.out.print("Escriba una opción: ");
	}
}
