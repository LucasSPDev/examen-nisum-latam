# Proyecto Spring Boot con HSQLDB

Este proyecto es una aplicación Spring Boot + Java 1.8 y utiliza HSQLDB como base de datos en memoria. Proporciona diversas APIs REST para gestionar usuarios.

## 🚀 Cómo levantar el proyecto

### Prerrequisitos
- Java 8+
- Maven 3.9.7
- HSQLDB 2.5.2 (Opcional)

### Pasos para ejecutar
1. Clonar el repositorio:
   ```sh
   git clone <git clone https://github.com/LucasSPDev/examen-nisum-latam.git>
   cd examen-nisum-latam
   ```
2. Compilar y ejecutar la aplicación:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```
3. La aplicación estará disponible en `http://localhost:8080`

## 🗄️ Base de Datos HSQLDB
HSQLDB es una base de datos en memoria utilizada para almacenamiento temporal. Se puede acceder a la consola con:
```sh
jdbc:hsqldb:mem:.
usuario: sa
contraseña: (vacía)
```

## 📌 API Endpoints

### 1. Verificar estado del servicio
- **URL:** `http://localhost:8080/getStatusService`
- **Método:** `GET`
- **Respuesta:**
  ```json
  {
    "status": "OK",
    "timestamp": "2025-03-22T12:00:00Z"
  }
  ```

### 2. Obtener usuario por UUID
- **URL:** `http://localhost:8080/getUserInfoByUUID`
- **Método:** `GET`
- **Parámetros:**
  - `uuid` (String) - UUID del usuario a buscar.
- **Request:**
  ```json
  {
    "uuid": "614edaeb-610e-44bf-b5ca-9783e725078d"
  }
- **Respuesta:**
  ```json
{
    "code": "00",
    "codeDescription": "OK",
    "user": {
        "uuid": "614edaeb-610e-44bf-b5ca-9783e725078d",
        "created": "2025-03-22T11:58:53.512+00:00",
        "modified": "2025-03-22T11:58:53.512+00:00",
        "lastLogin": "2025-03-22T11:58:53.512+00:00",
        "active": true,
        "token": "abc",
        "name": "111",
        "email": "juan@rodriguez.org",
        "password": "Ab123456789aaaaa",
        "phones": [
            {
                "id": 1,
                "number": 11,
                "cityCode": "1",
                "countryCode": "57"
            }
        ]
    }
}
  ```
- **Códigos de error:**
  - `404` - Usuario no encontrado
  - `400` - UUID inválido

### 3. Obtener lista paginada de usuarios
- **URL:** `http://localhost:8080/getPaginateUserInfo`
- **Método:** `POST`
- **Parámetros:**
  ```json
  {
    "start": 0,
    "end": 10
  }
  ```
- **Respuesta:**
  ```json
  [
    {"uuid": "1234", "name": "Juan"},
    {"uuid": "5678", "name": "Ana"}
  ]
  ```
- **Códigos de error:**
  - `400` - Parámetros de paginación inválidos
  - `404` - No hay usuarios disponibles

### 4. Buscar usuario por email
- **URL:** `http://localhost:8080/getUserInfoByEmail`
- **Método:** `GET`
- **Parámetros:**
  - `email` (String) - Email del usuario a buscar.
- **Respuesta:**
  ```json
  {
    "uuid": "1234",
    "name": "Juan",
    "email": "juan@example.com"
  }
  ```
- **Códigos de error:**
  - `404` - Usuario no encontrado
  - `400` - Email inválido

### 5. Crear nuevo usuario
- **URL:** `http://localhost:8080/createNewUser`
- **Método:** `POST`
- **Parámetros:**
  ```json
  {
    "name": "Juan",
    "email": "juan@example.com",
    "password": "securePass123",
    "phones": [
      {"number": "12345678", "cityCode": "1", "countryCode": "57"}
    ]
  }
  ```
- **Respuesta:**
  ```json
  {
    "uuid": "abcd-1234",
    "message": "Usuario creado exitosamente"
  }
  ```
- **Códigos de error:**
  - `400` - Datos de usuario inválidos
  - `409` - Email ya registrado

### 6. Modificar usuario
- **URL:** `http://localhost:8080/modifyUser`
- **Método:** `PUT`
- **Parámetros:**
  ```json
  {
    "uuid": "abcd-1234",
    "name": "Juan Actualizado",
    "email": "juan@example.com"
  }
  ```
- **Respuesta:**
  ```json
  {
    "message": "Usuario actualizado exitosamente"
  }
  ```
- **Códigos de error:**
  - `404` - Usuario no encontrado
  - `400` - Datos inválidos

## 📄 Colección de Postman
Puedes importar la colección de Postman adjunta en el repositorio para probar estas APIs.

- examenNisumLatam.postman_collection.json

## 📄 Documentación API con Swagger

Esta aplicación incluye documentación interactiva de la API utilizando **Swagger UI**, lo que facilita la exploración y prueba de los endpoints disponibles.

### 🔹 Acceder a Swagger UI
Una vez que la aplicación esté en ejecución, puedes acceder a la interfaz gráfica de Swagger desde el siguiente enlace:

📌 **URL por defecto**  
[`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html)
---

¡Listo para usar tu API! 🚀

