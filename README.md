# Sistema Integral Fundación OMNES

Proyecto académico desarrollado para la materia **Seminario de Práctica de Informática** de la **Licenciatura en Informática - Universidad Siglo 21**.

## Descripción

El sistema implementa un prototipo funcional del módulo de **Gestión de Turnos** para la Fundación OMNES. Permite administrar pacientes, profesionales y turnos mediante una interfaz gráfica desarrollada con **JavaFX**, utilizando **MySQL** como base de datos y **JDBC** para la persistencia de la información.

## Tecnologías utilizadas

* Java
* JavaFX
* Scene Builder
* MySQL
* JDBC
* Arquitectura MVC
* Patrón DAO
* Apache NetBeans

## Funcionalidades implementadas

* Pantalla de bienvenida.
* Gestión de pacientes.
* Gestión de profesionales.
* Gestión de turnos.
* Estados de los turnos: Programado, Reprogramado, Atendido, Ausente y Cancelado.
* Persistencia de datos en MySQL.
* Validaciones y manejo de excepciones.

## Estructura del repositorio

```text
docs/
diagramas/
java/
sql/
prototipos/
```

## Configuración de la base de datos

El proyecto utiliza **MySQL 8** como sistema gestor de base de datos.

Para ejecutar la aplicación es necesario:

1. Abrir MySQL Workbench.
2. Ejecutar el script ubicado en la carpeta `sql/omnes_db.sql`.
3. Verificar que la base de datos `omnes_db` haya sido creada correctamente.
4. Abrir el proyecto `SistemaTurnosOMNES` en NetBeans.
5. Ejecutar la aplicación.

### Configuración de la conexión

La conexión a la base de datos se encuentra definida en:

```text
dao/ConexionDB.java
```

Si el equipo utiliza un usuario o contraseña diferente para MySQL, modificar los siguientes parámetros:

```java
private static final String URL = "jdbc:mysql://localhost:3306/omnes_db";
private static final String USER = "root";
private static final String PASSWORD = "root123";
```

Reemplazando el usuario y la contraseña por las credenciales correspondientes al servidor MySQL local.

## Requisitos

* Java JDK 23 o superior.
* Apache NetBeans 25.
* JavaFX SDK.
* MySQL 8.
* MySQL Workbench (opcional).

## Video de demostración

La presentación del funcionamiento del sistema se encuentra disponible en el siguiente enlace:

https://drive.google.com/drive/folders/1C7tG2N_XHFp5a4PZtU-UG_JK_oE9Jo4F?usp=sharing

## Autor

**David Emanuel Gigena**

Licenciatura en Informática

Universidad Siglo 21


