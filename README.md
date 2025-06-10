# Aplicación ToDoList con Spring Boot
Este repositorio contiene el desarrollo de la Práctica para la asignatura de Metologias Agiles 2025-A EPN usando Spring Boot y plantillas Thymeleaf. El objetivo de esta práctica es ampliar la funcionalidad de una aplicación base utilizando el framework Spring Boot, siguiendo metodologías ágiles de desarrollo colaborativo.


La aplicación cuenta con las siguientes funcionalidades implementadas durante la práctica:

- Página "Acerca de"
- Barra de menú con navegación entre secciones
- Listado de usuarios registrados (sin mostrar contraseñas)
- Vista detallada de cada usuario


## Requisitos

- Java 8
- Maven
- IDE recomendado: IntelliJ IDEA o VS Code
- Navegador web moderno

## Instalación

Para ejecutar la aplicación en local:

1. Clona este repositorio:

   ```bash
   git clone https://github.com/Edisinsky/practica-02-aplicacion-todo-EddyCastro.git

## Ejecución

Puedes ejecutar la aplicación usando el _goal_ `run` del _plugin_ Maven
de Spring Boot:

```
$ ./mvnw spring-boot:run 
```   

También puedes generar un `jar` y ejecutarlo:

```
$ ./mvnw package
$ java -jar target/mads-todolist-inicial-0.0.1-SNAPSHOT.jar 
```

Una vez lanzada la aplicación puedes abrir un navegador y probar la página de inicio:

- [http://localhost:8080/login](http://localhost:8080/login)

# Enlace al tablero de Trello de la práctica:
[Trello/EddyCastro/epn-todo-list](https://trello.com/invite/b/683a4dd191d724abd0827dce/ATTIcd3feea94bc292167e8077beb2c4538fF18411AD/todolist-epn)

# Enlace al repositorio de DockerHub: 
[DockerHub/EddyrCastro/epn-todolist](https://hub.docker.com/r/eddyrcastro/epn-todolist)


# Enlace al repositorio de GitHub:
[Repositorio en Github de la práctica](https://github.com/Edisinsky/practica-02-aplicacion-todo-EddyCastro.git)


## **NOTA:** 
Para la documentación técnica sobre clases, métodos, vistas y pruebas, consulta el archivo 
[Práctica.md](doc/practica.md) 
