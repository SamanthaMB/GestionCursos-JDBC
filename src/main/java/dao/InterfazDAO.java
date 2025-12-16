package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InterfazDAO<T> {

    // CRUD genérico

    boolean insertarDato(T data) throws SQLException;

    ArrayList<T> obtenerListaDatos();

    void actualizarDato(T datoNuevo);

    int borrarDatos(int id);  //me

}
