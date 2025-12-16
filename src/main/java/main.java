import dao.CursoDAOImp;
import dao.EstudianteDAOImp;
import dao.ProfesorDAOImp;
import model.Curso;
import model.Estudiante;
import model.Profesor;

import java.sql.SQLException;
import java.util.ArrayList;

public class main {

    public static void main(String[] args){

        ProfesorDAOImp profesorDAO = new ProfesorDAOImp();
        CursoDAOImp cursoDAO = new CursoDAOImp();
        EstudianteDAOImp estudianteDAO = new EstudianteDAOImp();

        //Instanciamos objetos de las 3 entidades que tenemos
        // Creamos 2 profesores
        Profesor profesor1 = new Profesor(1,"Samantha Mohedano");
        Profesor profesor2 = new Profesor(2,"Carlos Moreno");

        // Creamos 4 estudiantes
        Estudiante estudiante1 = new Estudiante(10,"Sara Ruiz");
        Estudiante estudiante2 = new Estudiante(20,"Andrea Paz");
        Estudiante estudiante3 = new Estudiante(30,"Luis Diaz");
        Estudiante estudiante4 = new Estudiante(40,"Jorge Casas");

        // Creamos 4 cursos
        Curso curso1 =new Curso(100,"Acceso a Datos",profesor1);
        Curso curso2 =new Curso(200,"Devops",profesor1);
        Curso curso3 =new Curso(300,"Cloud",profesor2);
        Curso curso4 =new Curso(400,"Big Data",profesor2);


        try {

            //Insertamos profesores
            profesorDAO.insertarDato(profesor1);
           System.out.println("Profesor insertado: id = " + profesor1.getId() + " nombre = " +profesor1.getNombre());
           profesorDAO.insertarDato(profesor2);
           System.out.println("Profesor insertado: id = " + profesor2.getId() + " nombre = " +profesor2.getNombre());


        /*

            //Insertamos dos cursos para cada profesor
            cursoDAO.insertarDato(curso1);
            cursoDAO.insertarDato(curso2);
            System.out.println("Cursos insertados: " + "id = " + curso1.getId() + ", " + "id = " + curso2.getId()
                 + " al profesor con id = " + curso1.getProfesor().getId());
            cursoDAO.insertarDato(curso3);
            cursoDAO.insertarDato(curso4);
            System.out.println("Cursos insertados: " + "id = " + curso3.getId() + ", " + "id = " + curso4.getId()
              + " al profesor con id = " + curso3.getProfesor().getId());

         */

          /*

            //Insertamos estudiantes:
            estudianteDAO.insertarDato(estudiante1);
            estudianteDAO.insertarDato(estudiante2);
            estudianteDAO.insertarDato(estudiante3);
            estudianteDAO.insertarDato(estudiante4);
            System.out.println("Estudiantes insertados correctamente");

          */

           /*

            // Asignar estudiantes a cursos (tabla intermedia relación N:M)
            estudianteDAO.asignarEstudianteACurso(estudiante1.getId(), curso1.getId());
            estudianteDAO.asignarEstudianteACurso(estudiante2.getId(), curso2.getId());
            estudianteDAO.asignarEstudianteACurso(estudiante3.getId(), curso1.getId());
            estudianteDAO.asignarEstudianteACurso(estudiante3.getId(), curso3.getId());
            estudianteDAO.asignarEstudianteACurso(estudiante2.getId(), curso3.getId());
            estudianteDAO.asignarEstudianteACurso(estudiante1.getId(), curso4.getId());
            estudianteDAO.asignarEstudianteACurso(estudiante4.getId(), curso4.getId());
            estudianteDAO.asignarEstudianteACurso(estudiante4.getId(), curso2.getId());
            System.out.println("Estudiantes asignados a cursos correctamente");

            */

            /*

            //Mostrar Cursos de un profesor - Profesor1
           System.out.println("\nCursos del profesor con id = " + profesor1.getId() + ":");
            ArrayList<Curso> listaCursosPorProf = cursoDAO.mostrarCursosPorProfesor(profesor1.getId());
            for (Curso curso : listaCursosPorProf)
                 curso.mostrarDatos();

            //Mostrar Cursos de un profesor - Profesor2
            System.out.println("\nCursos del profesor con id = " + profesor2.getId() + ":");
             ArrayList<Curso> listaCursoPorProf = cursoDAO.mostrarCursosPorProfesor(profesor2.getId());
             for (Curso curso : listaCursoPorProf)
                  curso.mostrarDatos();

             */

            /*

            // Mostrar estudiantes de un curso - Curso1
            System.out.println("\nEstudiantes inscritos en el curso " + curso1.getNombre() + " id = " + curso1.getId() + ":");
             ArrayList<Estudiante> listaEstudiantesPorCurso = cursoDAO.mostrarEstudiantesPorCurso(curso1.getId());
              for (Estudiante estudiantes : listaEstudiantesPorCurso)
                   estudiantes.mostrarDatos();

            // Mostrar estudiantes de un curso - Curso3
            System.out.println("\nEstudiantes inscritos en curso " + curso3.getNombre() + " id = " + curso3.getId() + ":");
            ArrayList<Estudiante> listaEstudiantePorCurso = cursoDAO.mostrarEstudiantesPorCurso(curso3.getId());
            for (Estudiante estudiantes : listaEstudiantePorCurso)
                estudiantes.mostrarDatos();

           */

            /*

            //Mostrar cursos por estudiante - Estudiante1
            System.out.println("\nCursos del estudiante " + estudiante1.getNombre() + " con id = " + estudiante1.getId() + ":");
            ArrayList<Curso> listaCursosPorEstudiante = estudianteDAO.mostrarCursosPorEstudiante(estudiante1.getId());
            for (Curso cursos : listaCursosPorEstudiante)
                cursos.mostrarDatosSinProf();

            //Mostrar cursos por estudiante - Estudiante4
            System.out.println("\nCursos del estudiante " + estudiante4.getNombre() + " con id = " + estudiante4.getId() + ":");
            ArrayList<Curso> listaCursoPorEstudiante = estudianteDAO.mostrarCursosPorEstudiante(estudiante4.getId());
            for (Curso cursos : listaCursoPorEstudiante)
            cursos.mostrarDatosSinProf();

            */

        } catch (Exception e) {
            System.out.println("Error en ejecución: " + e.getMessage());
        }

    }
}
