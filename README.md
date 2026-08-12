# 📚 REST API - Sistema de Gestión de Libros y Préstamos

API RESTful desarrollada con **Spring Boot** para la gestión de catálogo de libros, categorías y préstamos. El sistema implementa una arquitectura por capas, validaciones de entrada, DTOs y un manejo centralizado de excepciones.

---

## 🚀 Tecnologías Utilizadas

* **Java 17**
* **Spring Boot 3**
    * Spring Data JPA
    * Spring Web 
    * Validation (`hibernate-validator`)
* **Base de Datos:** PostgreSQL / MySQL
* **Herramientas:** Lombok, Maven, Postman

---

## 🏗️ Arquitectura y Principios de Diseño

* **Arquitectura en Capas:** Clara separación entre Controladores (`Controller`), Servicios (`Service`), Repositorios (`Repository`) y Capa de Datos.
* **Patrón DTO:** Desacoplamiento entre la capa de presentación y el dominio JPA para evitar *Over-Posting* y filtrado de datos sensibles.
* **Manejo Centralizado de Excepciones:** Respuestas de error estandarizadas (`400`, `404`, `409`, `500`) mediante `@RestControllerAdvice`.
* **Transaccionalidad:** Gestión estricta del ciclo de vida de persistencia y *Dirty Checking* con `@Transactional`.

---

## 📌 Endpoints Principales

### 📖 Libros (`/api/libros`)
| Método | Endpoint | Descripción | Código HTTP |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/libros` | Listar todos los libros | `200 OK` |
| `GET` | `/api/libros/{id}` | Obtener libro por ID | `200 OK` / `404 NOT FOUND` |
| `POST` | `/api/libros` | Crear un nuevo libro | `201 CREATED` / `400 BAD REQUEST` |
| `PUT` | `/api/libros/{id}` | Actualizar un libro existente | `200 OK` / `404 NOT FOUND` |
| `DELETE` | `/api/libros/{id}` | Eliminar un libro | `204 NO CONTENT` / `404 NOT FOUND` |

---

## 🛡️ Estructura de Manejo de Errores

La API devuelve respuestas de error estandarizadas con el siguiente formato JSON:

```json
{
  "timestamp": "2026-08-12T10:30:00",
  "status": 400,
  "mensaje": "Falló la validación de los datos enviados",
  "detalles": "uri=/api/libros"
}