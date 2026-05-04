# academia-web
AA2 Programacion_Itziar Arizcuren

Aplicación web desarrollada en Java con Servlets y JSP (Tomcat) para la gestión de una academia.
Incluye:

- Gestión de alumnos, profesores, cursos y matrículas
- CRUD completo
- Sistema de login con roles (admin / alumno)
- Búsqueda avanzada con múltiples criterios
- Subida de imágenes (solo hay una imagen de ejemplo en el usuario Ana Gomez)
- Mensaje de confirmacion de edicion o borrado
- Interfaz con Bootstrap

# Tecnologías utilizadas
- Java
- Servlets
- Apache Tomcat
- MySQL
- Bootstrap

# Requisitos
- Java JDK
- Apache Tomcat 10+
- MySQL
- IDE (IntelliJ / Eclipse)

# Configuración
Modificar el archivo:
DBConnection.java
Con tus datos de conexión:
- String url = "jdbc:mysql://localhost:3306/academia";
- String user = "root";
- String password = "tu_password";

# Ejecución
Importar el proyecto en el IDE
Configurar Apache Tomcat
Ejecutar el proyecto
Acceder a:
http://localhost:8080/index.html

# Usuarios de prueba
Admin:
- email: admin@admin.com
- password: 1234
Alumno:
- email: ana.gomez@example.com
- password: 1234

## Notas: El proyecto está preparado para ejecutarse en entorno local con Tomcat.
