package dao;

import model.Curso;
import model.Estudiante;

import java.util.ArrayList;

public interface CursoDAO extends InterfazDAO<Curso> {

    ArrayList<Curso> mostrarCursosPorProfesor(int idProfesor);
    ArrayList<Estudiante> mostrarEstudiantesPorCurso(int idCurso);
}
