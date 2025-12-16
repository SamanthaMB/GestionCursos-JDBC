package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profesor {

    private int id;
    private String nombre;
    private List<Curso>listaCursos =new ArrayList<>();

    //Generamos constructor sin lista de cursos
    public Profesor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    //Para poder obtener solo el id del profesor
    public Profesor(int id) {
        this.id = id;
    }
}
