package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    private int id;
    private String nombre;
    private Profesor profesor;
    private List<Estudiante> listaEstudiantes = new ArrayList<>();

    //Generamos constructor sin lista de estudiantes
    public Curso(int id, String nombre, Profesor profesor) {
        this.id = id;
        this.nombre = nombre;
        this.profesor = profesor;
    }

    //Generamos constructor sin profesor para metodo mostrarCursosPorEstudiantes
    public Curso(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Curso{id=" + id + ", nombre='" + nombre + "', profesor=" + profesor.getId() + "}";
    }

    public void mostrarDatos() {
        System.out.println("Curso ID: " + id + ", Nombre: " + nombre + ", Profesor ID: " + profesor.getId());
    }

    //Metodo para mostrar datos sin necesidad de mostrar el profesor - mostrarCursosPorEstudiante
    public void mostrarDatosSinProf() {
        System.out.println("Curso ID: " + id + ", Nombre: " + nombre );
    }
}
