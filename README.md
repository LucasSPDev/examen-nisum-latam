# Proyecto Spring Boot con HSQLDB

Este proyecto es una aplicación Spring Boot + Java 1.8 y utiliza HSQLDB como base de datos en memoria. Proporciona diversas APIs REST para gestionar usuarios.

## 🚀 Cómo levantar el proyecto

### Configuracion (Opcional)
Los campos email y password de las API's, se validan mediante una expresión regular la cual debe ser configurada manualmente desde el archivo application.properties

Por ejemplo:

	- regex.email=^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$
	- regex.password=^(?=.*[a-z])(?=.*[A-Z])(?=(.*\\d){9,}).*$
 
### Pre Requisitos
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
A continuación se lista el detalle de las API's de este proyecto (no olvides hacer click en **Detalles** :smile:)

<Details>
   
### 1. Verificar estado del servicio
- **URL:** `http://localhost:8080/getStatusService`
- **Método:** `GET`
- **Response:**
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
- **Response:**
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
  - `200` - OK
  - `500` - Internal Server Error

### 3. Obtener lista paginada de usuarios
- **URL:** `http://localhost:8080/getPaginateUserInfo`
- **Método:** `POST`
- **Parámetros:**
   - `start` (Number) - Índice de inicio para la paginación.
   - `end` (Number) - Índice de fin para la paginación.
- **Request:**
  ```json
  {
    "start": 0,
    "end": 10
  }
  ```
- **Response:**
  ```json
   {
       "code": "00",
       "codeDescription": "OK",
       "userList": [
           {
               "uuid": "42858c49-2b50-479c-99a1-274dc2f15bbb",
               "created": "2025-03-22T14:39:48.739+00:00",
               "modified": "2025-03-22T14:39:48.739+00:00",
               "lastLogin": "2025-03-22T14:39:48.739+00:00",
               "active": true,
               "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIiwiaWF0IjoxNzQyNjU0Mzg4LCJleHAiOjE3NDI3NDA3ODh9.y-Mhju45awhyVoX2TPR_B0Y5G_BQx99sSb-iu0Nhk-Q",
               "name": "Lucas dos",
               "email": "test@test.com",
               "password": "Ab123456789aaaaa",
               "phones": [
                   {
                       "id": 2,
                       "number": 11,
                       "cityCode": "1",
                       "countryCode": "57"
                   }
               ]
           },
           {
               "uuid": "b74951b4-ab96-4243-9ad0-8afd27772b06",
               "created": "2025-03-22T14:39:30.442+00:00",
               "modified": "2025-03-22T14:39:30.442+00:00",
               "lastLogin": "2025-03-22T14:39:30.442+00:00",
               "active": true,
               "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsdWNhc0BsdWNhcy5jb20iLCJpYXQiOjE3NDI2NTQzNzAsImV4cCI6MTc0Mjc0MDc3MH0.QkS8u8P8gKTPEtoKO41gVNDB9NouCcgma509-K2_h-E",
               "name": "11111",
               "email": "lucas@lucas.com",
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
       ]
   }
  ```
- **Códigos de error:**
  - `200` - OK
  - `500` - Internal Server Error

### 4. Buscar usuario por email
- **URL:** `http://localhost:8080/getUserInfoByEmail`
- **Método:** `GET`
- **Parámetros:**
  - `email` (String) - Email del usuario a buscar.
- **Request:**
   ```json
   {
       "email":"test@test.com"
   }
   ```
- **Response:**
  ```json
   {
       "code": "00",
       "codeDescription": "OK",
       "user": {
           "uuid": "42858c49-2b50-479c-99a1-274dc2f15bbb",
           "created": "2025-03-22T14:39:48.739+00:00",
           "modified": "2025-03-22T14:39:48.739+00:00",
           "lastLogin": "2025-03-22T14:39:48.739+00:00",
           "active": true,
           "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIiwiaWF0IjoxNzQyNjU0Mzg4LCJleHAiOjE3NDI3NDA3ODh9.y-Mhju45awhyVoX2TPR_B0Y5G_BQx99sSb-iu0Nhk-Q",
           "name": "Lucas dos",
           "email": "test@test.com",
           "password": "Ab123456789aaaaa",
           "phones": [
               {
                   "id": 2,
                   "number": 11,
                   "cityCode": "1",
                   "countryCode": "57"
               }
           ]
       }
   }
  ```
- **Códigos de error:**
  - `200` - OK
  - `500` - Internal Server Error

### 5. Crear nuevo usuario
- **URL:** `http://localhost:8080/createNewUser`
- **Método:** `POST`
- **Parámetros:**
   - `name` (String) - Nombre del usuario.
   - `email`  (String) - Email de contacto del usuario.
   - `password`  (String) - Contraseña del usuario.
   - `phones`  (Lista) -  Telefonos de contacto del usuario.
      -  `number`  (Number) - Numero telefonico de contacto.
      -  `cityCode` (String) - Codigo telefonico de la ciudad.
      -  `countryCode`(String) - Codigo telefonico del pais.
- **Request:**
  ```json
   {
       "name": "Lucas dos",
       "email": "test@asasasasas.com",
       "password": "Ab123456789aaaaa",
       "phones": [
           {
               "number": 11,
               "cityCode": "1",
               "countryCode": "57"
           }
       ]
   }
  ```
- **Response:**
  ```json
   {
       "code": "00",
       "codeDescription": "OK",
       "user": {
           "uuid": "7933a074-4c88-45e8-bee5-a8fe75c86ca1",
           "created": "2025-03-22T14:57:47.464+00:00",
           "modified": "2025-03-22T14:57:47.464+00:00",
           "lastLogin": "2025-03-22T14:57:47.464+00:00",
           "active": true,
           "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGFzYXNhc2FzYXMuY29tIiwiaWF0IjoxNzQyNjU1NDY3LCJleHAiOjE3NDI3NDE4Njd9.t-Mi_vAQBjvessa_ZG8AXAaphmT-u92fz3-0wRHLg3c",
           "name": "Lucas dos",
           "email": "test@asasasasas.com",
           "password": "Ab123456789aaaaa",
           "phones": [
               {
                   "id": 3,
                   "number": 11,
                   "cityCode": "1",
                   "countryCode": "57"
               }
           ]
       }
   }
  ```
- **Códigos de error:**
  - `200` - OK
  - `500` - Internal Server Error

### 6. Modificar usuario
- **URL:** `http://localhost:8080/modifyUser`
- **Método:** `PUT`
- **Parámetros:**
   - `name` (String) - Nombre del usuario.
   - `email`  (String) - Email de contacto del usuario.
   - `password`  (String) - Contraseña del usuario.
   - `phones`  (Lista) -  Telefonos de contacto del usuario.
      -  `number`  (Number) - Numero telefonico de contacto.
      -  `cityCode` (String) - Codigo telefonico de la ciudad.
      -  `countryCode`(String) - Codigo telefonico del pais.
- **Request:**
  ```json
   {
       "name": "Lucas Salvador",
       "email": "test@asasasasas.com",
       "password": "Ab123456789aaaaa",
       "phones": [
           {
               "number": "11",
               "cityCode": "1",
               "countryCode": "57"
           }
       ]
   }
  ```
- **Response:**
  ```json
      {
       "code": "00",
       "codeDescription": "OK",
       "user": {
           "uuid": "b9cdf54b-0a33-42aa-a9c9-fa44ca770f2d",
           "created": "2025-03-22T15:05:02.925+00:00",
           "modified": "2025-03-22T15:05:36.348+00:00",
           "lastLogin": "2025-03-22T15:05:02.925+00:00",
           "active": true,
           "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGFzYXNhc2FzYXMuY29tIiwiaWF0IjoxNzQyNjU1OTAyLCJleHAiOjE3NDI3NDIzMDJ9.6RZ57KFAPRDqTbAN-28ZQukspBzkpXIHFghfiXZHwq8",
           "name": "Lucas Salvador",
           "email": "test@asasasasas.com",
           "password": "Ab123456789aaaaa",
           "phones": [
               {
                   "id": 2,
                   "number": 11,
                   "cityCode": "1",
                   "countryCode": "57"
               }
           ]
       }
   }
  ```
- **Códigos de error:**
  - `200` - OK
  - `500` - Internal Server Error
## ⚠️ Códigos de Error y Descripciones

La API maneja errores customizados con códigos específicos para facilitar la depuración y el manejo de respuestas en los clientes.

| Código  | Descripción |
|---------|------------|
| `00`    | OK. |
| `01`    | General Error. |
| `02`    | Error en el campo 'name', no es válido. |
| `03`    | Error en el campo 'email', no es válido (Ej: examen@nisum.com). |
| `04`    | Error en el campo 'paswword', no es válido (Ej: Ab123456789). |
| `05`    | Error en el campo 'phone', no es válido. |
| `06`    | Error en la creacion del usuario. |
| `07`    | Usuario no existe. |
| `08`    | El email ya se encuentra registrado. |
| `09`    | El email NO se encuentra registrado. |

> 📌 **Nota:** En caso de error, la API devuelve una respuesta con el código de error y su descripción correspondiente.
</Details>

## 📄 Documentación Adicional

### Acceder a Swagger UI
Una vez que la aplicación esté en ejecución, puedes acceder a la interfaz gráfica de Swagger desde el siguiente enlace:
 - [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html)

### Diagrama de flujo
Puedes encontrar un diagrama de las API's más importantes dentro del proyecto
 - DiagramaCreateNewUser.png
 - DiagramaModifyUser.png
   
### Postman
Puedes importar la colección de Postman adjunta en el repositorio para probar estas APIs.
- examenNisumLatam.postman_collection.json

---

¡Listo para usar tu API! 🚀

