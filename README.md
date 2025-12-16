# 📚 Sistema de Gestión de Cursos (JDBC)

Proyecto en Java que permite gestionar **profesores, cursos y estudiantes**, con operaciones CRUD y relaciones:

- **1:N** → Profesor → Curso  
- **N:M** → Estudiante ↔ Curso (tabla intermedia)

Se utiliza **JDBC** y el **patrón DAO**.

---

## Estructura del proyecto

src/
├─ main/java/
│ ├─ dao/ # DAO: ProfesorDAO, CursoDAO, EstudianteDAO
│ ├─ model/ # Clases: Profesor, Curso, Estudiante
│ ├─ util/ # DBConnection, SchemeDB
│ └─ main/ # Clase Main con menú
└─ main/resources/
└─ database.properties

---

## Base de datos

**Nombre:** `GestionCursos`

**Tablas:**

- **profesores:** `id` (PK), `nombre`  
- **cursos:** `id` (PK), `nombre`, `id_profesor` (FK)  
- **estudiantes:** `id` (PK), `nombre`  
- **cursos_estudiantes:** `id_curso` (FK), `id_estudiante` (FK), PK compuesta  

> Nota: Los IDs no son autoincrementales.

---

## Configuración

`database.properties`:

user=""
pass=""
url=jdbc:mysql://127.0.0.1:3306/GestionCursos


---

## Ejemplo de uso

```java
Profesor prof1 = new Profesor(1, "Samantha Mohedano");
profesorDAO.insertarDato(prof1);

Curso curso1 = new Curso(10, "Big Data", prof1);
cursoDAO.insertarDato(curso1);

Estudiante est1 = new Estudiante(20, "Laura");
estudianteDAO.insertarDato(est1);
estudianteDAO.asignarEstudianteACurso(est1.getId(), curso1.getId());

---

## Funcionalidades

- CRUD completo para **profesores, cursos y estudiantes**
- Asignación de estudiantes a cursos
- Mostrar cursos de un profesor
- Mostrar estudiantes de un curso

## Mejoras futuras

- IDs **autoincrementales**
- Separar la lógica en **Controllers**
- Migración a **Hibernate/JPA**
- Mejor manejo de errores y validaciones

## Buenas prácticas

- Patrón **DAO**
- Uso de **PreparedStatement**
- Paquetes organizados (`model`, `dao`, `util`, `main`)
- Métodos descriptivos y claros

## Conclusión personal

Me gustó hacer este proyecto y “pelearme” con él, aprendiendo sobre **JDBC**, relaciones **1:N y N:M**, y cómo estructurar un proyecto Java con DAO.  
Una mejora sería usar **IDs autoincrementales** para evitar errores al insertar manualmente.
