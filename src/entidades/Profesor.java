package entidades;

import java.time.LocalDate;

/**
 * Clase que representa un profesor.
 */
public class Profesor {
    /**
     * Identificador unico del profesor.
     */
    private int idProfesor;

    /**
     * Nombre del profesor.
     */
    private String nombre;

    /**
     * Apellidos del profesor.
     */
    private String apellidos;

    /**
     * Fecha de nacimiento del profesor.
     */
    private LocalDate fechaNacimiento;

    /**
     * Annios de antiguedad del profesor.
     */
    private int antiguedad;

    /**
     * Constructor vacio para crear un objeto Profesor sin inicializar los atributos.
     */
    public Profesor() {
    }

    /**
     * Constructor que inicializa todos los atributos del profesor.
     *
     * @param idProfesor              Identificador unico del profesor.
     * @param nombre          Nombre del profesor.
     * @param apellidos       Apellidos del profesor.
     * @param fechaNacimiento Fecha de nacimiento del profesor.
     * @param antiguedad      Años de antigüedad del profesor en la institución.
     */
    public Profesor(int idProfesor, String nombre, String apellidos, LocalDate fechaNacimiento, int antiguedad) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.antiguedad = antiguedad;
    }

    /**
     * Obtiene el identificador unico del profesor.
     *
     * @return El identificador del profesor.
     */
    public int getIdProfesor() {
        return idProfesor;
    }

    /**
     * Obtiene el nombre del profesor.
     *
     * @return El nombre del profesor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene los apellidos del profesor.
     *
     * @return Los apellidos del profesor.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Obtiene la fecha de nacimiento del profesor.
     *
     * @return La fecha de nacimiento del profesor.
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Obtiene los anniosos de antigüedad del profesor en la institucion.
     *
     * @return Los annios de antiguedad del profesor.
     */
    public int getAntiguedad() {
        return antiguedad;
    }
}
