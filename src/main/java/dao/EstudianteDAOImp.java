package dao;

import model.Curso;
import model.Estudiante;
import util.DBConnection;
import util.SchemeDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EstudianteDAOImp implements EstudianteDAO{

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public EstudianteDAOImp() {
        connection = DBConnection.getConnection();
    }

    // Metodo para insertar un estudiante en la BBDD
    @Override
    public boolean insertarDato(Estudiante data) throws SQLException {
        //Creamos query
        String query = String.format("INSERT INTO %s (%s,%s) VALUES (?,?)",
                SchemeDB.TAB_ESTUDIANTE,
                SchemeDB.COL_ID_ESTUDIANTE,SchemeDB.COL_NAME_ESTUDIANTE);

        //Preparamos la sentencia
        preparedStatement = connection.prepareStatement(query);
        //Asignamos los valores de los parámetros a la sentencia
        preparedStatement.setInt(1,data.getId());
        preparedStatement.setString(2,data.getNombre());
        //Ejecutamos y si ha ido bien devuelve false
        return preparedStatement.execute();
    }

    // Metodo para obtener todos los estudiantes de la BBDD
    @Override
    public ArrayList<Estudiante> obtenerListaDatos() {
        //ArrayList para almacenar la lista de estudiantes
        ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
        //Creamos query
        String query = String.format("SELECT * FROM %s", SchemeDB.TAB_ESTUDIANTE);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { //mientras haya next lo recorre y saca los datos
                int idEstudiante = resultSet.getInt(SchemeDB.COL_ID_ESTUDIANTE);
                String nombre = resultSet.getString(SchemeDB.COL_NAME_ESTUDIANTE);

                listaEstudiantes.add(new Estudiante(idEstudiante, nombre)); //lo añadimos a la lista
            }
            return listaEstudiantes; //retornamos la lista de estudiantes
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de estudiantes: " + e.getMessage());
        }
        return listaEstudiantes; //Si llega aquí esta lista estará vacía y return null
    }

    // Metodo para actualizar un estudiante existente en la BBDD
    @Override
    public void actualizarDato(Estudiante datoNuevo) {
        //Creamos query para actualizar nombre según id del estudiante
        String query = String.format("UPDATE %s SET %s=? WHERE %s=?",
                SchemeDB.TAB_ESTUDIANTE,
                SchemeDB.COL_NAME_ESTUDIANTE, SchemeDB.COL_ID_ESTUDIANTE);
        try {
            //Preparamos la query
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, datoNuevo.getNombre());
            preparedStatement.setInt(2, datoNuevo.getId());
            preparedStatement.executeUpdate(); //Ejecutamos actualizacion y devuelve filas afectadas
        } catch (SQLException e) {
            System.out.println("Error actualizando estudiante: " + e.getMessage());
        }

    }

    // Metodo para borrar un estudiante por su id
    @Override
    public int borrarDatos(int id) {
        //Creamos query para eliminar el estudiante según su id
        String query = String.format("DELETE FROM %s WHERE %s=?",
                SchemeDB.TAB_ESTUDIANTE, SchemeDB.COL_ID_ESTUDIANTE);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate(); //Ejecutamos eliminación y devuelve filas afectadas
        } catch (SQLException e) {
            System.out.println("Error borrando estudiante: " + e.getMessage());
        }
        return -1;// si hay error, devuelve -1
    }

    // Metodo para asignar un estudiante a un curso en la tabla intermedia (N:M)
    @Override
    public boolean asignarEstudianteACurso(int idEstudiante, int idCurso) {
        //Creamos query en la tabla intermedia cursos_estidiantes e insertamos los id correspondientes
        String query = String.format("INSERT INTO %s (%s,%s) VALUES (?,?)",
                SchemeDB.TAB_CURSOS_ESTUDIANTES,
                SchemeDB.COL_CE_CURSO, SchemeDB.COL_CE_ESTUDIANTE);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idCurso);
            preparedStatement.setInt(2, idEstudiante);
            preparedStatement.executeUpdate(); //Ejecutamos la inserción
            return true; // devuelve true si fue bien
        } catch (SQLException e) {
            System.out.println("Error asignando estudiante a curso: " + e.getMessage());
            return false; // devuelve false si hubo error
        }
    }

    // Metodo para obtener los cursos en los que está matriculado un estudiante
    @Override
    public ArrayList<Curso> mostrarCursosPorEstudiante(int idEstudiante) {
        //ArrayList para almacenar la lista de cursos
        ArrayList<Curso> listaCursos = new ArrayList<>();
        //Creamos query con JOIN entre cursos y tabla intermedia cursos_estudiantes (N:M)
        String query = String.format(
                "SELECT c.%s, c.%s FROM %s c " +
                        "JOIN %s ce ON c.%s = ce.%s " +
                        "WHERE ce.%s = ?",
                SchemeDB.COL_ID_CURSO, SchemeDB.COL_NAME_CURSO,
                SchemeDB.TAB_CURSOS,
                SchemeDB.TAB_CURSOS_ESTUDIANTES, SchemeDB.COL_ID_CURSO, SchemeDB.COL_CE_CURSO,
                SchemeDB.COL_CE_ESTUDIANTE
        );
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idEstudiante);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { //mientras haya next lo recorre y saca los datos
                int idCurso = resultSet.getInt(SchemeDB.COL_ID_CURSO);
                String nombre = resultSet.getString(SchemeDB.COL_NAME_CURSO);

                listaCursos.add(new Curso(idCurso, nombre)); //lo añadimos a la lista
            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo cursos por estudiante: " + e.getMessage());
        }
        return listaCursos; //retornamos la lista de cursos
    }

}
