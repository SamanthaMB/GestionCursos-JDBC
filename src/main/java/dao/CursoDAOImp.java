package dao;

import model.Curso;
import model.Estudiante;
import model.Profesor;
import util.DBConnection;
import util.SchemeDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CursoDAOImp implements CursoDAO{

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public CursoDAOImp() {
        connection = DBConnection.getConnection();
    }

    // Metodo para insertar un curso en la BBDD
    @Override
    public boolean insertarDato(Curso data) throws SQLException {
        //Creamos query
        String query = String.format("INSERT INTO %s (%s,%s,%s) VALUES (?,?,?)",
                SchemeDB.TAB_CURSOS,
                SchemeDB.COL_ID_CURSO,SchemeDB.COL_NAME_CURSO,SchemeDB.COL_PROFESOR);

        //Preparamos la sentencia
        preparedStatement = connection.prepareStatement(query);
        //Asignamos los valores de los parámetros a la sentencia
        preparedStatement.setInt(1,data.getId());
        preparedStatement.setString(2,data.getNombre());
        preparedStatement.setInt(3,data.getProfesor().getId());
        //Ejecutamos y si ha ido bien devuelve false
        return preparedStatement.execute();
    }

    // Metodo para obtener todos los cursos de la BBDD
    @Override
    public ArrayList<Curso> obtenerListaDatos() {
        //ArrayList para almacenar la lista de cursos
        ArrayList<Curso> listaCursos = new ArrayList<>();
        //Creamos query
        String query = String.format("SELECT * FROM %s", SchemeDB.TAB_CURSOS);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { //mientras haya next lo recorre y saca los datos
                int idCurso = resultSet.getInt(SchemeDB.COL_ID_CURSO);
                String nombre = resultSet.getString(SchemeDB.COL_NAME_CURSO);
                Profesor profesor = new Profesor(resultSet.getInt(SchemeDB.COL_PROFESOR));

                listaCursos.add(new Curso(idCurso, nombre, profesor)); //lo añadimos a la lista
            }
            return listaCursos; //retornamos la lista de cursos
        } catch (SQLException e) {
            System.out.println("Error al obtener lista de cursos: " + e.getMessage());
        }
        return listaCursos; //Si llega aquí esta lista estará vacía y return null
    }


    // Metodo para actualizar un curso existente en la BBDD
    @Override
    public void actualizarDato(Curso datoNuevo) {
        //Creamos query para actualizar nombre y profesor según id del curso
        String query = String.format("UPDATE %s SET %s=?, %s=? WHERE %s=?",
                SchemeDB.TAB_CURSOS, SchemeDB.COL_NAME_CURSO, SchemeDB.COL_PROFESOR, SchemeDB.COL_ID_CURSO);
        try {
            //Preparamos la query
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, datoNuevo.getNombre());
            preparedStatement.setInt(2, datoNuevo.getProfesor().getId());
            preparedStatement.setInt(3, datoNuevo.getId());
            preparedStatement.executeUpdate(); //Ejecutamos actualizacion y devuelve filas afectadas
        } catch (SQLException e) {
            System.out.println("Error actualizando curso: " + e.getMessage());
        }

    }

    // Metodo para borrar un curso según su id
    @Override
    public int borrarDatos(int id) {
        //Creamos query para eliminar el curso según su id
        String query = String.format("DELETE FROM %s WHERE %s=?",
                SchemeDB.TAB_CURSOS, SchemeDB.COL_ID_CURSO);
        try {
            //Preparamos la query
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate(); //Ejecutamos eliminación y devuelve filas afectadas
        } catch (SQLException e) {
            System.out.println("Error borrando curso: " + e.getMessage());
            return -1; // si hay error, devuelve -1
        }
    }

    // Metodo para obtener los cursos impartidos por un profesor
    @Override
    public ArrayList<Curso> mostrarCursosPorProfesor(int idProfesor) {
        //ArrayList para almacenar la lista de cursos
        ArrayList<Curso> listaCursosPorPofesor = new ArrayList<>();
        //Creamos query filtrando por id del profesor
        String query = String.format("SELECT * FROM %s WHERE %s=?",
                SchemeDB.TAB_CURSOS, SchemeDB.COL_PROFESOR);
        try {
            //Preparamos la query
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idProfesor);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { //mientras haya next lo recorre y saca los datos
                int idCurso = resultSet.getInt(SchemeDB.COL_ID_CURSO);
                String nombre = resultSet.getString(SchemeDB.COL_NAME_CURSO);
                Profesor profesor = new Profesor(idProfesor,"");

                listaCursosPorPofesor.add(new Curso(idCurso, nombre, profesor)); //lo añadimos a la lista
            }
        } catch (SQLException e) {
            System.out.println("Error consultando cursos por profesor: " + e.getMessage());
        }
        return listaCursosPorPofesor; //retornamos la lista de cursos
    }


    // Metodo para obtener los estudiantes matriculados en un curso
    @Override
    public ArrayList<Estudiante> mostrarEstudiantesPorCurso(int idCurso) {
        //ArrayList para almacenar la lista de estudiantes
        ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
        //Creamos query con JOIN entre estudiantes y tabla intermedia cursos_estudiantes (N:M)
        String query = String.format(
                "SELECT e.%s, e.%s FROM %s e " +
                        "JOIN %s ce ON e.%s = ce.%s " +
                        "WHERE ce.%s = ?",
                SchemeDB.COL_ID_ESTUDIANTE, SchemeDB.COL_NAME_ESTUDIANTE,
                SchemeDB.TAB_ESTUDIANTE,
                SchemeDB.TAB_CURSOS_ESTUDIANTES, SchemeDB.COL_ID_ESTUDIANTE, SchemeDB.COL_CE_ESTUDIANTE,
                SchemeDB.COL_CE_CURSO
        );
        try {
            //Preparemos la query
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idCurso);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { //mientras haya next lo recorre y saca los datos
                int idEstudiante = resultSet.getInt(SchemeDB.COL_ID_ESTUDIANTE);
                String nombre = resultSet.getString(SchemeDB.COL_NAME_ESTUDIANTE);

                listaEstudiantes.add(new Estudiante(idEstudiante, nombre)); //lo añadimos a la lista
            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo estudiantes por curso: " + e.getMessage());
        }
        return listaEstudiantes; //retornamos la lista de estudiantes
    }
}
