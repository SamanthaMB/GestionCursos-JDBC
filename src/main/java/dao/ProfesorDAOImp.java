package dao;

import model.Profesor;
import util.DBConnection;
import util.SchemeDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfesorDAOImp implements ProfesorDAO{

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ProfesorDAOImp() {
        connection = DBConnection.getConnection();
    }

    // Metodo para insertar un profesor en la BBDD
    @Override
    public boolean insertarDato(Profesor data) throws SQLException {
        //Creamos la query
        String query = String.format("INSERT INTO %s (%s,%s) VALUES (?,?)",
                SchemeDB.TAB_PROFESOR,
                SchemeDB.COL_ID_PROFESOR,SchemeDB.COL_NAME_PROFESOR);

        //Preparamos la sentencia
        preparedStatement = connection.prepareStatement(query);
        //Asignamos los valores de los parámetros a la sentencia
        preparedStatement.setInt(1,data.getId());
        preparedStatement.setString(2,data.getNombre());
        //Ejecutamos y si ha ido bien devuelve false
        return preparedStatement.execute();
    }

    // Metodo para obtener todos los profesores de la BBDD
    @Override
    public ArrayList<Profesor> obtenerListaDatos() {
        //ArrayList para almacenar la lista de los profesores
        ArrayList<Profesor> listaProfesores = new ArrayList<>();
        //Creamos query
        String query = String.format("SELECT * FROM %s", SchemeDB.TAB_PROFESOR);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { //mientras haya next lo recorre y saca los datos
                int id = resultSet.getInt(SchemeDB.COL_ID_PROFESOR);
                String nombre = resultSet.getString(SchemeDB.COL_NAME_PROFESOR);
                listaProfesores.add(new Profesor(id, nombre)); //lo añadimos a la lista
            }
            return listaProfesores; //retornamos la lista de profesores
        } catch (SQLException e) {
            System.out.println("Error al obtener lista de profesores: " + e.getMessage());
        }
        return listaProfesores; //Si llega aquí esta lista estará vacía y return null
    }

    // Metodo para actualizar un profesor existente en la BBDD
    @Override
    public void actualizarDato(Profesor datoNuevo) {
        //Creamos query para actualizar nombre según id
        String query = String.format("UPDATE %s SET %s=? WHERE %s=?",
                SchemeDB.TAB_PROFESOR,
                SchemeDB.COL_NAME_PROFESOR, SchemeDB.COL_ID_PROFESOR);
        try {
            //Preparamos la query
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, datoNuevo.getNombre());
            preparedStatement.setInt(2, datoNuevo.getId());
            preparedStatement.executeUpdate(); //Ejecutamos actualizacion y devuelve filas afectadas
        } catch (SQLException e) {
            System.out.println("Error actualizando profesor: " + e.getMessage());
        }
    }


    // Metodo para borrar un profesor según su id
    @Override
    public int borrarDatos(int id) {
        //Creamos query para eliminar el profesor según su id
        String query = String.format("DELETE FROM %s WHERE %s=?",
                SchemeDB.TAB_PROFESOR,SchemeDB.COL_ID_PROFESOR);
        try {
            //Preparamos la query
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate(); //Ejecutamos eliminación y devuelve filas afectadas
        } catch (SQLException e) {
            System.out.println("Error en la ejecución de la query" + e.getMessage());
        }
        return -1; // si hay error, devuelve -1
    }
}
