package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante {

    private int id;
    private String nombre;
    private List<Curso> listaCursos = new ArrayList<>();

    //Generamos constructor sin lista de cursos
    public Estudiante(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public void mostrarDatos() {
        System.out.println("Estudiante ID: " + id + ", Nombre: " + nombre);
    }
}
