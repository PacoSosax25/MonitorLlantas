# MonitorLlantas

Sistema web para monitorear la profundidad de llantas de una flota de vehículos (taxis, combis, autobuses). 
Muestra el estado de cada llanta con un semáforo de colores y permite filtrar por tipo de unidad sin recargar la página.

---

## Stack tecnológico

| Componente      | Tecnología                          |
|-----------------|-------------------------------------|
| Lenguaje        | Java 17                             |
| Framework       | Spring Boot 3.2.5                   |
| Vistas          | Thymeleaf                           |
| Base de datos   | SQL Server 2016    |
| Driver JDBC     | Microsoft JDBC 12.4.2               |
| Pool conexiones | HikariCP                            |
| Build           | Maven                               |
| Frontend        | HTML / CSS / JavaScript (vanilla)   |

---

## Estructura del proyecto

```
src/main/java/com/monitorllantas/
  controller/   ← Recibe peticiones HTTP, delega a servicios
  service/      ← Lógica de negocio (semáforo, validaciones)
  repository/   ← Interfaces de acceso a datos
  dao/          ← Implementaciones con JdbcTemplate
  model/        ← Entidades y DTOs
  config/       ← Interceptor de sesión y configuración web

src/main/resources/
  templates/
    login.html
    dashboard.html
  static/
    css/style.css
    js/dashboard.js
  application.properties
```

---

## Configuración de base de datos

Las credenciales se leen de variables de entorno. Si no están definidas, se usan los valores por defecto de `application.properties`.

| Variable de entorno | Descripción              | Valor por defecto                                               |
|---------------------|--------------------------|-----------------------------------------------------------------|
| `DB_DRIVER`         | Clase del driver JDBC    | `com.microsoft.sqlserver.jdbc.SQLServerDriver`                  |
| `DB_URL`            | URL de conexión JDBC     | `` |
| `DB_USER`           | Usuario de la BD         | ``                                                            |
| `DB_PASS`           | Contraseña de la BD      | ``                                                          |
| `DB_MAX_POOL_SIZE`  | Máx. conexiones en pool  | `10`                                                            |
| `DB_IDLE_TIME`      | Tiempo idle (minutos)    | `2`                                                             |

---

## Cómo ejecutar

```bash
# Compilar y empaquetar
mvn clean package

# Ejecutar la aplicación
mvn spring-boot:run

# Ejecutar todos los tests
mvn test
```

La aplicación queda disponible en `http://localhost:8080`.

Para sobrescribir credenciales sin modificar el código:

```bash
# Windows PowerShell
$env:DB_URL="jdbc:sqlserver://MI_SERVIDOR:1433;Database=databaseName"
$env:DB_USER="mi_usuario"
$env:DB_PASS="mi_pass"
mvn spring-boot:run
```

---

## Funcionalidades

### Login
- Formulario con campos `Usuario` y `Contraseña`.
- Solo permite acceso a usuarios con `ESTADO = 'ACTIVO'` en la tabla `USUARIO`.
- La sesión expira tras **1 hora** de inactividad y redirige silenciosamente al login.

### Dashboard — Tabla de llantas
Muestra la lectura más reciente de profundidad por posición para cada unidad de la flota.

**Columnas (en orden):**

| Columna en vista           | Código en BD |
|----------------------------|--------------|
| Unidad                     | —            |
| Tipo                       | —            |
| Delantera Izquierda        | `DI`         |
| Delantera Derecha          | `DD`         |
| Trasera Izquierda Interior | `TII`        |
| Trasera Izquierda Exterior | `TIE`        |
| Trasera Derecha Interior   | `TDI`        |
| Trasera Derecha Exterior   | `TDE`        |

### Semáforo de colores

| Profundidad (mm)     | Color de celda |
|----------------------|----------------|
| `null` o `0`         | Sin color      |
| `≤ 3.0`              | Rojo           |
| `3.1` — `5.9`        | Amarillo       |
| `≥ 6.0`              | Verde          |

La lógica del semáforo vive en la capa de servicio (`LlantaService`), no en la vista.

### Filtro por tipo de unidad
- Dropdown con la opción **"Todos"** más los tipos disponibles en la BD.
- Al cambiar la selección se hace una petición AJAX al endpoint `GET /dashboard/llantas?tipo=TAXI`.
- El servidor devuelve JSON y el JavaScript actualiza la tabla sin recargar la página.

### Cierre de sesión
- Botón de salida en el dashboard que invalida la sesión y redirige al login.


