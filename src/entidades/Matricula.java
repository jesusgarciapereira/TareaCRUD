package entidades;

/**
 * Clase que representa una matricula que vincula a un alumno y un profesor.
 */
public class Matricula {
    /**
     * Identificador unico de la matricula.
     */
    private int idMatricula;

    /**
     * Identificador unico del alumno.
     */
    private int idAlumno;

    /**
     * Identificador unico del profesor.
     */
    private int idProfesor;

    /**
     * Nombre de la asignatura asociada a la matricula.
     */
    private String asignatura;

    /**
     * Curso academico al que pertenece la matricula.
     */
    private int curso;

    /**
     * Constructor vacio para crear un objeto Matricula sin inicializar los atributos.
     */
    public Matricula() {
    }

    /**
     * Constructor que inicializa todos los atributos de la matricula.
     *
     * @param idMatricula Identificador unico de la matricula.
     * @param idAlumno    Identificador unico del alumno.
     * @param idProfesor  Identificador unico del profesor.
     * @param asignatura  Nombre de la asignatura.
     * @param curso       Curso academico de la matricula.
     */
    public Matricula(int idMatricula, int idAlumno, int idProfesor, String asignatura, int curso) {
        this.idMatricula = idMatricula;
        this.idAlumno = idAlumno;
        this.idProfesor = idProfesor;
        this.asignatura = asignatura;
        this.curso = curso;
    }

    /**
     * Obtiene el identificador unico de la matricula.
     *
     * @return El identificador unico de la matricula.
     */
    public int getIdMatricula() {
        return idMatricula;
    }

    /**
     * Obtiene el identificador unico del alumno.
     *
     * @return El identificador unico del alumno.
     */
    public int getIdAlumno() {
        return idAlumno;
    }

    /**
     * Obtiene el identificador unico del profesor.
     *
     * @return El identificador unico del profesor.
     */
    public int getIdProfesor() {
        return idProfesor;
    }

    /**
     * Obtiene el nombre de la asignatura asociada a la matricula.
     *
     * @return El nombre de la asignatura.
     */
    public String getAsignatura() {
        return asignatura;
    }

    /**
     * Obtiene el curso academico al que pertenece la matricula.
     *
     * @return El curso academico.
     */
    public int getCurso() {
        return curso;
    }
}

