# 🐾 PettiWay-Back

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=flat&logo=springsecurity&logoColor=white)](https://spring.io/projects/spring-security)

API Backend para la plataforma PettiWay - Servicio backend REST/GraphQL para la gestión de peticiones y servicios de mascotas.

## 📝 Descripción

**PettiWay-Back** es el backend que soporta la plataforma PettiWay, una solución integral para servicios de mascotas que incluye:

- 🐕 Cuidado y paseo de mascotas
- 📅 Sistema de reservas
- 🛍️ Gestión de productos
- 👥 Perfiles de usuarios y mascotas
- 💳 Sistema de pagos integrado

### Características principales

- 🔐 Autenticación segura con JWT
- 📊 API RESTful/GraphQL
- 🛡️ Sistema de roles y permisos
- 📝 Documentación completa de la API
- 🧪 Cobertura de tests
- 🚀 Despliegue con Docker

## 🧰 Stack Tecnológico

### Lenguajes y Frameworks
- **Lenguaje:** Java 21
- **Framework Backend:** Spring Boot 3.5.6
- **Base de datos:** MySQL
- **ORM:** Spring Data JPA (Hibernate)
- **Autenticación:** JWT, OAuth2
- **Documentación API:** SpringDoc OpenAPI 2.5.0
- **Mapeo de objetos:** MapStruct 1.5.5
- **Mensajería:** WebSocket
- **Procesamiento de imágenes:** Thumbnailator
- **Envío de correos:** Spring Mail

### Dependencias Principales
- **Spring Boot Starters:**
    - Spring Web
    - Spring Security
    - Spring Data JPA
    - Validation
    - WebSocket
    - OAuth2 Client
    - Mail
- **Seguridad:**
    - Spring Security
    - jjwt 0.12.6
    - java-jwt 4.2.0
- **Utilidades:**
    - Lombok
    - MapStruct
    - SpringDoc OpenAPI
    - Thumbnailator

### Herramientas de Desarrollo
- **Build:** Maven
- **Testing:** JUnit, Spring Test, Spring Security Test
- **Documentación:** SpringDoc OpenAPI UI
- **Contenedorización:** Docker (configurable)
- **IDE:** Cualquier IDE compatible con Java/Spring (IntelliJ IDEA, VS Code, etc.)

## 🚀 Instalación y Configuración

### Requisitos Previos
- Java 21 JDK
- Apache Maven 3.8.6 o superior
- MySQL 8.0 o superior
- Docker (opcional para desarrollo con contenedores)

### Configuración del Entorno

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Altiora-Tech/PettiWay-Back.git
   cd PettiWay-Back
   ```

2. Configurar la base de datos:
    - Crear una base de datos MySQL llamada `pettidev`
    - Configurar el usuario y contraseña en `application.properties`

3. Configuración de la aplicación:
   Crear o modificar el archivo `src/main/resources/application.properties`:
   ```properties
   # Configuración del servidor
   server.port=8080
   
   # Configuración de la base de datos
   spring.datasource.url=jdbc:mysql://localhost:3306/pettidev?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   
   # Configuración de JPA/Hibernate
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true
   
   # Configuración de JWT
   jwt.secret=TuSecretoMuySeguro
   jwt.expiration=86400000  # 24 horas en milisegundos
   
   # Configuración de CORS
   cors.allowed-origins=http://localhost:3000
   
   # Configuración de Spring Doc (OpenAPI)
   springdoc.api-docs.path=/api-docs
   springdoc.swagger-ui.path=/swagger-ui.html
   springdoc.swagger-ui.operationsSorter=method
   ```

4. Compilar el proyecto:
   ```bash
   mvn clean install
   ```

5. Ejecutar la aplicación:
   ```bash
   mvn spring-boot:run
   ```
   O ejecutar directamente el archivo JAR generado en `target/`

## 🏃 Ejecución

### Modo Desarrollo
```bash
# Ejecutar con perfil de desarrollo
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Construir y Ejecutar
```bash
# Compilar y empaquetar
mvn clean package

# Ejecutar el archivo JAR generado
java -jar target/PettiWay-0.0.1-SNAPSHOT.jar
```

## 🧪 Testing

### Ejecutar Pruebas Unitarias
```bash
# Ejecutar todas las pruebas
mvn test

# Ejecutar pruebas con cobertura (requiere plugin jacoco)
mvn clean test jacoco:report
```

### Pruebas de Integración
```bash
# Ejecutar pruebas de integración
mvn verify -Pintegration-test
```

### Generar Documentación de la API
```bash
# Iniciar la aplicación y acceder a:
# - Swagger UI: http://localhost:8080/swagger-ui.html
# - OpenAPI JSON: http://localhost:8080/api-docs
```

## 📡 Documentación de la API

La documentación completa de la API está disponible a través de Swagger UI cuando la aplicación está en ejecución:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

### Endpoints Principales

#### Autenticación
| Método | Ruta | Descripción |
|--------|------|-------------|
| `POST` | `/api/auth/signin` | Iniciar sesión |
| `POST` | `/api/auth/signup` | Registrar nuevo usuario |
| `POST` | `/api/auth/refresh` | Refrescar token JWT |

#### Usuarios
| Método | Ruta | Descripción |
|--------|------|-------------|
| `GET` | `/api/users/me` | Obtener perfil del usuario actual |
| `PUT` | `/api/users/me` | Actualizar perfil del usuario |
| `GET` | `/api/users` | Listar usuarios (solo ADMIN) |

#### Mascotas
| Método | Ruta | Descripción |
|--------|------|-------------|
| `GET` | `/api/pets` | Listar mascotas del usuario |
| `POST` | `/api/pets` | Crear nueva mascota |
| `GET` | `/api/pets/{id}` | Obtener detalles de mascota |
| `PUT` | `/api/pets/{id}` | Actualizar mascota |
| `DELETE` | `/api/pets/{id}` | Eliminar mascota |

#### Servicios
| Método | Ruta | Descripción |
|--------|------|-------------|
| `GET` | `/api/services` | Listar servicios disponibles |
| `POST` | `/api/services` | Crear nuevo servicio (solo PROVIDER/ADMIN) |
| `GET` | `/api/services/{id}` | Obtener detalles de servicio |
| `PUT` | `/api/services/{id}` | Actualizar servicio |

#### Reservaciones
| Método | Ruta | Descripción |
|--------|------|-------------|
| `GET` | `/api/reservations` | Listar reservaciones del usuario |
| `POST` | `/api/reservations` | Crear nueva reservación |
| `GET` | `/api/reservations/{id}` | Obtener detalles de reservación |
| `PUT` | `/api/reservations/{id}/cancel` | Cancelar reservación |

> **Nota:** Para la documentación completa de la API, consulte la [documentación de Swagger](http://localhost:4000/api) después de iniciar el servidor en modo desarrollo.

## 🔐 Autenticación

La autenticación se realiza mediante JWT. Para acceder a rutas protegidas, incluye el token en el header de la solicitud:

```
Authorization: Bearer <token>
```

### Roles Disponibles

| Rol | Descripción | Verificación Automática |
|-----|------------|-------------------------|
| `CLIENT` | Cliente o dueño de mascotas | ✅ Sí |
| `SITTER` | Profesional que ofrece servicios | ❌ No |
| `BUSINESS` | Veterinaria o comercio de servicios | ❌ No |
| `PROVIDER` | Distribuidor o comercio que vende productos | ❌ No |
| `SELLER` | Emprendedor independiente que vende productos | ❌ No |
| `SUPER_ADMIN` | Administrador de la plataforma | ✅ Sí |

### Endpoints Públicos

Los siguientes endpoints están disponibles sin autenticación:

- `POST /auth/login` - Iniciar sesión
- `POST /user/register` - Registrar nuevo usuario
- `POST /auth/google` - Autenticación con Google
- `GET /auth/**` - Endpoints de autenticación
- `GET /public/**` - Recursos públicos
- `GET /swagger-ui/**` - Documentación de la API
- `GET /v3/api-docs/**` - Especificación OpenAPI

### Seguridad de Endpoints

- Los endpoints que no están en la lista de públicos requieren autenticación
- La autorización se maneja a nivel de método usando `@PreAuthorize`
- Se recomienda implementar la verificación de roles en los controladores según sea necesario

### Proceso de Verificación

- Los roles con `autoVerified = true` son verificados automáticamente
- Los roles que requieren verificación manual deben ser aprobados por un administrador
- Los usuarios pueden tener múltiples roles según sus necesidades

### Configuración de CORS

La aplicación está configurada para aceptar solicitudes desde:
- La URL del frontend en producción
- `http://localhost`
- `http://localhost/julia-rodriguez/petcare`

## 🤝 Contribución

1. Haz un Fork del proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Haz commit de tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Haz push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Distribuido bajo la licencia MIT. Ver `LICENSE` para más información.

## ✉️ Contacto

Equipo de Desarrollo - [@AltioraTech](https://twitter.com/AltioraTech)

---

<div align="center">
  Hecho con ❤️ por el equipo de <a href="https://github.com/Altiora-Tech">Altiora Tech</a>
</div># PettiWay
