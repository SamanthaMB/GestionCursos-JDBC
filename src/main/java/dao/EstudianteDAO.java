package dao;

import model.Curso;
import model.Estudiante;

import java.util.ArrayList;

public interface EstudianteDAO extends InterfazDAO<Estudiante> {

    boolean asignarEstudianteACurso(int idEstudiante, int idCurso);
    ArrayList<Curso> mostrarCursosPorEstudiante(int idEstudiante);
}
