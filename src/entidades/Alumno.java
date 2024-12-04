package entidades;

import java.time.LocalDate;


/**
 * Clase que representa un alumno.
 */
public class Alumno {
    /**
     * Identificador unico del alumno.
     */
    private int idAlumno;

    /**
     * Nombre del alumno.
     */
    private String nombre;

    /**
     * Apellidos del alumno.
     */
    private String apellidos;

    /**
     * Fecha de nacimiento del alumno.
     */
    private LocalDate fechaNacimiento;

    /**
     * Constructor vacio para crear un objeto Alumno sin inicializar los atributos.
     */
    public Alumno() {
    }

    /**
     * Constructor que inicializa todos los atributos del alumno.
     *
     * @param idAlumno              Identificador unico del alumno.
     * @param nombre                Nombre del alumno.
     * @param apellidos             Apellidos del alumno.
     * @param fechaNacimiento       Fecha de nacimiento del alumno.
     */
    public Alumno(int idAlumno, String nombre, String apellidos, LocalDate fechaNacimiento) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene el identificador unico del alumno.
     *
     * @return El identificador del alumno.
     */
    public int getIdAlumno() {
        return idAlumno;
    }

    /**
     * Establece el identificador unico del alumno.
     *
     * @param idAlumno El nuevo identificador del alumno.
     */
    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    /**
     * Obtiene el nombre del alumno.
     *
     * @return El nombre del alumno.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del alumno.
     *
     * @param nombre El nuevo nombre del alumno.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los apellidos del alumno.
     *
     * @return Los apellidos del alumno.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del alumno.
     *
     * @param apellidos Los nuevos apellidos del alumno.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene la fecha de nacimiento del alumno.
     *
     * @return La fecha de nacimiento del alumno.
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del alumno.
     *
     * @param fechaNacimiento La nueva fecha de nacimiento del alumno.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
