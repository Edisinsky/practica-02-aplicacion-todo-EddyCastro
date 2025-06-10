
# 📘 Documentación Técnica – ToDoList - EPN - Eddy Castro

Este documento describe los cambios técnicos realizados en la práctica sobre la aplicación **ToDoList**, orientada a desarrolladores, con el objetivo de facilitar la continuidad y mantenimiento del proyecto.

---

## 1. 🧩 Nuevas clases y métodos

### `GlobalControllerAdvice`
- Clase anotada con `@ControllerAdvice`.
- Método `agregarUsuarioSesion(HttpSession session)` declarado con `@ModelAttribute("usuarioSesion")` inyecta en el modelo Thymeleaf el usuario logueado (tipo `UsuarioData`) o `null` si no hay sesión activa.
- Utiliza `ManagerUserSession` y `UsuarioService`.

**Ejemplo:**
```java
@ModelAttribute("usuarioSesion")
public UsuarioData agregarUsuarioSesion(HttpSession session) {
    Long idUsuario = (Long) session.getAttribute("idUsuarioLogeado");
    if (idUsuario != null) {
        return usuarioService.findById(idUsuario);
    }
    return null;
}
````

Este patrón permite que la vista condicione elementos del navbar (login, registrado, logout) según la sesión.

---

### `HomeController`

* Controlador principal con rutas:

    * `GET /about` → página "Acerca de".
    * `GET /registrados` → lista todos los usuarios registrados.
    * `GET /registrados/{id}` → muestra la descripción detallada de un usuario.
* Se inyecta `UsuarioRepository` para acceso a datos.

**Método clave:**

```java
@GetMapping("/registrados/{id}")
public String descripcionUsuarios(@PathVariable Long id, Model model) {
    Usuario usuario = usuarioRepository.findById(id).orElse(null);
    model.addAttribute("usuario", usuario);
    return "descripcionUsuarios";
}
```

---

## 2. 🖼️ Vistas Thymeleaf añadidas

Se incorporaron cinco nuevas plantillas:

### `fragments.html`

Contiene fragmentos compartidos:

* `head(titulo)` para el título y CSS.
* `javascript` con scripts para Bootstrap/jQuery.
* `navbar`: barra de navegación con lógica `th:if="${usuarioSesion != null}"` para mostrar enlaces condicionales (ToDoList, Acerca de, Registrados, login/logout).

### `about.html`

Vista simple de "Acerca de", con un `div` con información del proyecto (autor, versiones y fechas).

### `registrados.html`

Tabla con todos los usuarios, mostrando `id`, `email` y un botón "Ver datos del usuario" que enlaza a `/registrados/{id}`.

### `descripcionUsuarios.html`

Tabla centrada con datos individuales del usuario: `id`, correo, nombre, fecha de nacimiento. Incluye un botón para volver a la lista.

**Ejemplo del encabezado en Thymeleaf:**

```html
<table class="table table-striped text-center">
  <thead>
    <tr>
      <th>ID</th><th>Email</th><th>Nombre</th><th>Fecha de Nacimiento</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td th:text="${usuario.id}">1</td>
      <td th:text="${usuario.email}">ejemplo@correo.com</td>
      <!-- ... -->
    </tr>
  </tbody>
</table>
```

---

## 3. 🧪 Pruebas implementadas

### `AcercaDeWebTest`

Prueba de integración con `MockMvc`, comprueba que la ruta `/about` devuelve contenido que contiene "ToDoList".

```java
mockMvc.perform(get("/about"))
       .andExpect(content().string(containsString("ToDoList")));
```

### `GlobalControllerAdviceTest`

Verifica el comportamiento de `agregarUsuarioSesion()` en dos casos:

1. Con usuario en sesión (devuelve `UsuarioData`).
2. Sin usuario en sesión (devuelve `null`).

### `ListaRegistradosTest`

Test unitario para `HomeController.listaUsuarios(model)`:

* Simula `usuarioRepository.findAll()` con una lista.
* Verifica que se agregue el atributo `"usuarios"` al modelo y se devuelve la vista `"registrados"`.

### `DescripcionUsuarioTest`

Test unitario para `descripcionUsuarios(id, model)` con dos escenarios:

* **ID válido**: se añade `usuario` al modelo y se devuelve `"descripcionUsuarios"`.
* **ID inexistente**: se añade `usuario` con valor `null`, y se devuelve la misma vista.

---

## 4. 🧠 Explicación de código relevante

### Fragmento de inyección de sesión en `GlobalControllerAdvice`

Este método permite acceder fácilmente al usuario logueado desde cualquier plantilla Thymeleaf, eliminando la necesidad de verificar sesión en cada controlador.

### Uso condicional en `fragments.html`

```html
<li class="nav-item" th:if="${usuarioSesion != null}">
  <a class="nav-link" th:href="@{/registrados}">Registrados</a>
</li>
```

Este código ocultará el enlace de usuarios registrados si no hay sesión activa.

### `descripcionUsuarios()` y uso de `Optional`

La elección de usar `orElse(null)` simplifica el manejo de errores en vista: si no existe el usuario, la página aún carga sin lanzar excepción.

```java
Usuario usuario = usuarioRepository.findById(id).orElse(null);
```

---

## 5. 🗂️ Flujo de trabajo y versiones

1. Se creó la rama `barra-menu` para implementar y testear el fragmento compartido de la barra de navegación.
2. Luego se avanzó en la rama `listado-usuarios`, desarrollando el controlador, repositorio y plantilla `registrados.html` junto con su test.
3. Finalmente, se trabajó en la rama `descripcion-usuarios`, agregando `descripcionUsuarios()` y `descripcionUsuarios.html`, además de los tests unitarios correspondientes.

Cada funcionalidad tuvo su branch, issue asociado en Trello/GitHub Projects, y merge tras pruebas exitosas siguiendo la metodología indicada.

---

## 🐳 Dockerización y versiones

El proyecto fue dockerizado usando un Dockerfile que define una imagen basada en un archivo compilado `.jar` . Se expone el puerto 8080 y se empaqueta la aplicación con Spring Boot. Esto permite desplegar la aplicación en cualquier entorno compatible con Docker de forma rápida y consistente.

Además, se creó un nuevo release 1.1.0 en GitHub, que incluye todas las funcionalidades descritas en esta documentación: navegación mejorada, página "Acerca de", listado y descripción de usuarios, y mejoras en pruebas.

---

## ✅ Conclusión

* Se habilitó una navegación coherente y responsiva con `navbar` global.
* Se implementó una vista de listados registrada desacoplada, con lógica de presentación limpia y testes.
* Se completó la funcionalidad de ver detalles individuales sin romper flujo de navegación.
* Se mantienen pruebas unitarias e integración para asegurar calidad de código.

---
