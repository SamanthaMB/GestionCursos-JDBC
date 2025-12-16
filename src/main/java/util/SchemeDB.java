package util;

public interface SchemeDB {

    String DB_NAME = "GestionCursos";

    // TABLA PROFESOR
    String TAB_PROFESOR = "profesores";
    String COL_NAME_PROFESOR = "nombre";
    String COL_ID_PROFESOR = "id";

    // TABLA CURSOS
    String TAB_CURSOS = "cursos";
    String COL_ID_CURSO = "id";
    String COL_NAME_CURSO = "nombre";
    String COL_PROFESOR = "id_profesor";

    // TABLA ESTUDIANTE
    String TAB_ESTUDIANTE = "estudiantes";
    String COL_NAME_ESTUDIANTE = "nombre";
    String COL_ID_ESTUDIANTE = "id";

    // TABLA INTERMEDIA (N a N)
    String TAB_CURSOS_ESTUDIANTES = "cursos_estudiantes";
    String COL_CE_CURSO = "id_curso";
    String COL_CE_ESTUDIANTE = "id_estudiante";
}
