# Biblioteca API - Spring Boot

API REST desarrollada con Spring Boot para gestionar libros, autores y categorías.

# Diagrama ER

```text
┌────────────────────┐
│      AUTORES       │
├────────────────────┤
│ PK id              │
│ nombre             │
└────────────────────┘
          │
          │ 1
          │
          │
          │ N
┌────────────────────┐
│      LIBROS        │
├────────────────────┤
│ PK id              │
│ titulo             │
│ FK autor_id        │
└────────────────────┘
          │
          │ N
          │
          │
          │ N
┌────────────────────┐
│  LIBRO_CATEGORIA   │
├────────────────────┤
│ FK libro_id        │
│ FK categoria_id    │
└────────────────────┘
          │
          │ N
          │
          │
          │ 1
┌────────────────────┐
│    CATEGORIAS      │
├────────────────────┤
│ PK id              │
│ tipo               │
└────────────────────┘
```

## Tecnologías utilizadas

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Lombok
* Jakarta Validation

---

# Funcionalidades

* CRUD de libros
* CRUD de autores
* CRUD de categorías
* Relaciones:

    * ManyToOne → Libro → Autor
    * ManyToMany → Libro ↔ Categoría
* Validación de datos con:

    * `@Valid`
    * `@NotBlank`
    * `@NotNull`
    * `@Size`
* Búsquedas con Query Params
* Ordenación dinámica con `Sort`
* Métodos derivados Spring Data JPA
* Consultas JPQL con `@Query`

---

# Requisitos

Antes de arrancar el proyecto necesitas:

* Java 17 o superior
* Maven
* MySQL

---

# Configuración de base de datos

Crear una base de datos en MySQL:

```sql
CREATE DATABASE biblioteca;
```

---

# Configurar application.properties

Ruta:

```text
src/main/resources/application.properties
```

Ejemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
spring.datasource.username=root
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

# Instalar dependencias

Desde terminal:

```bash
mvn clean install
```

---

# Arrancar el proyecto

## Desde IntelliJ

Ejecutar:

```text
TrabajoApplication.java
```

---

## Desde terminal

```bash
mvn spring-boot:run
```

---

# URL base de la API

```text
http://localhost:8080
```

---

# Endpoints principales

## Libros

| Método | Endpoint     |
| ------ | ------------ |
| GET    | /libros      |
| GET    | /libros/{id} |
| POST   | /libros      |
| PUT    | /libros/{id} |
| DELETE | /libros/{id} |

---

## Buscar libros

```http
GET /libros/buscar?titulo=hobbit
```

---

## Buscar libros por autor

```http
GET /libros/autor/4
```

---

## Contar libros de un autor

```http
GET /libros/autor/4/count
```

---

## Autores

| Método | Endpoint      |
| ------ | ------------- |
| GET    | /autores      |
| POST   | /autores      |
| PUT    | /autores/{id} |
| DELETE | /autores/{id} |

---

## Categorías

| Método | Endpoint         |
| ------ | ---------------- |
| GET    | /categorias      |
| POST   | /categorias      |
| PUT    | /categorias/{id} |
| DELETE | /categorias/{id} |

---

# Ejemplos de peticiones

## Crear autor

```json
{
    "nombre": "Tolkien"
}
```

---

## Crear categoría

```json
{
    "tipo": "Fantasia"
}
```

---

## Crear libro

```json
{
    "titulo": "El Hobbit",
    "autor": {
        "id": 1
    },
    "categorias": [
        {
            "id": 1
        }
    ]
}
```

---

# Validaciones implementadas

Ejemplos:

* `@NotBlank`
* `@NotNull`
* `@Size`

Ejemplo de error:

```json
{
    "titulo": "El título no puede estar vacío"
}
```

---

# Relaciones JPA

## Libro → Autor

```text
ManyToOne
```

Un autor puede tener muchos libros.

---

## Libro ↔ Categoría

```text
ManyToMany
```

Un libro puede tener muchas categorías y una categoría puede pertenecer a muchos libros.

---

# Consultas avanzadas

## Método derivado

```java
findByTituloContainingIgnoreCase();
```

---

## JPQL

```java
//@Query("""
//SELECT COUNT(l)
//FROM libro l
// l.autor.id = :id
//""")
```

---

## SEGURIDAD POSTMAN 
## Seguridad y autenticación en Postman

El proyecto utiliza Spring Security con autenticación básica (`Basic Auth`).

Las credenciales se configuran en el archivo:

```text
src/main/java/com/biblioteca/trabajo/security/SecurityConfig.java
```

Ejemplo de configuración:

```java
UserDetails admin = User.withDefaultPasswordEncoder()
        .username("admin")      // usuario
        .password("admin123")  // contraseña
        .roles("ADMIN")
        .build();

//return new InMemoryUserDetailsManager(admin);
```

---

# Cómo usarlo en Postman

Para acceder a los endpoints protegidos:

1. Abrir Postman
2. Ir a la pestaña **Authorization**
3. Seleccionar:

```text
Basic Auth
```

4. Introducir las credenciales configuradas en `SecurityConfig.java`

Ejemplo:

| Campo    | Valor    |
| -------- | -------- |
| Username | admin    |
| Password | admin123 |

---

# Ejemplo de endpoint protegido

```http
GET http://localhost:8080/libros
```

Si las credenciales son correctas, la API responderá normalmente.

Si las credenciales son incorrectas o no se envían, Spring Security devolverá:

```http
401 Unauthorized
```
