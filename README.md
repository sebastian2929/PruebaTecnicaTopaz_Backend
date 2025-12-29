# 🚀 Prueba Técnica - Sebastian Blanco

### 📌 Proyecto: CRUD Libros – Backend

#### 🎯 Descripción
Este proyecto corresponde al backend del sistema de gestión de libros.
Provee una API REST desarrollada con Spring Boot que permite realizar operaciones CRUD, paginación, ordenamiento y filtrado dinámico server-side sobre los libros registrados.


#### 📷 Diagrama
<img width="685" height="251" alt="ArquitecturaBack" src="https://github.com/user-attachments/assets/836a0216-9dd9-4b83-9e5c-59f81f2516e0" />


### 🛠️ Tecnologías usadas
* Java 17+
* Spring Boot
* Docker
* Maven

### 📄 Modelo Libro

| Campo             | Tipo        | Restricciones                     |
|-------------------|-------------|-----------------------------------|
| id                | Long        | PK, autogenerado                  |
| nombre            | String      | Máx. 150 caracteres, único        |
| descripcion       | String      | Máx. 300 caracteres               |
| autor             | String      | Máx. 150 caracteres               |
| fechaPublicacion  | LocalDate   | Máx. 10 años                      |
| numeroEjemplares  | Integer     | ≥ 0                               |
| costo             | BigDecimal  | 4 decimales                       |



### 🔗 Endpoints disponibles
``` 
GET /api/libros
```
- Parámetros opcionales:

      page

      size

      sort=campo,direccion

      filterField

      filterOperator

      filterValue

- Operadores soportados:
      contains

      notContains

      equals

      notEquals

      starts

      ends

#### ➕ Crear libro
```
POST /api/libros
```
#### ✏️ Actualizar libro
```
PUT /api/libros/{id}
```
#### ❌ Eliminar libro
```
DELETE /api/libros/{id}
```

### 🔎 Filtros Server-Side

Los filtros se implementan usando Spring Data JPA Specifications, permitiendo búsquedas dinámicas según el campo y operador seleccionado desde el frontend.

```
/api/libros?filterField=nombre&filterOperator=contains&filterValue=java
```

### ▶️ Cómo ejecutar el proyecto

1. Asegúrate de tener **Docker** en ejecución en tu sistema.

2. Accede a la carpeta del proyecto:
```
cd /libros
```
3. Ejecuta el siguiente comando para levantar el contenedor de la base de datos:
```
docker-compose up --build
```
4. Una vez el contenedor esté corriendo, compila el proyecto Java:
```
mvn clean install
```
5. (Opcional) ejecutar las prubas
```
mvn test
```
6. Finalmente, ejecuta la aplicación con: 
```
mvn spring-boot:run
```


### ✍ Autor
 - [Sebastián David Blanco Rodriguez](https://github.com/sebastian2929)
