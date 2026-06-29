# \# Sistema Integral Fundación OMNES

# 

# Proyecto académico desarrollado para la materia Seminario de Práctica de Informática de la Licenciatura en Informática - Universidad Siglo 21.

# 

# \## Descripción

# 

# El sistema implementa un prototipo funcional del módulo de Gestión de Turnos para la Fundación OMNES. Permite administrar pacientes, profesionales y turnos mediante una interfaz gráfica desarrollada con JavaFX, utilizando MySQL como base de datos y JDBC para la persistencia.

# 

# \## Tecnologías utilizadas

# 

# \- Java

# \- JavaFX

# \- Scene Builder

# \- MySQL

# \- JDBC

# \- Arquitectura MVC

# \- Patrón DAO

# \- NetBeans

# 

# \## Funcionalidades implementadas

# 

# \- Pantalla de bienvenida.

# \- Gestión de pacientes.

# \- Gestión de profesionales.

# \- Gestión de turnos.

# \- Estados de turnos: Programado, Reprogramado, Atendido, Ausente y Cancelado.

# \- Persistencia de datos en MySQL.

# \- Validaciones y manejo de excepciones.

# 

# \## Estructura del repositorio

# 

# ```text

# docs/

# diagramas/

# java/

# sql/

# prototipos/



# \## Configuración de la base de datos

# 

# El proyecto utiliza \*\*MySQL 8\*\* como sistema gestor de base de datos.

# 

# Para ejecutar la aplicación es necesario:

# 

# 1\. Abrir MySQL Workbench.

# 2\. Ejecutar el script ubicado en la carpeta `sql/omnes\\\_db.sql`.

# 3\. Verificar que la base de datos `omnes\\\_db` haya sido creada correctamente junto con sus tablas y datos de prueba.

# 4\. Abrir el proyecto `SistemaTurnosOMNES` en NetBeans.

# 5\. Ejecutar la aplicación.

# 

# \### Configuración de la conexión

# 

# La conexión a la base de datos se encuentra definida en la clase:

# 

# ```text

# dao/ConexionDB.java

# ```

# 

# En caso de que el equipo donde se ejecute el proyecto utilice un usuario o contraseña diferente para MySQL, será necesario modificar los siguientes parámetros:

# 

# ```java

# private static final String URL = "jdbc:mysql://localhost:3306/omnes\_db";

# private static final String USER = "root";

# private static final String PASSWORD = "root123";

# ```

# 

# Reemplazando el usuario y la contraseña por las credenciales correspondientes al servidor MySQL local.

# 

Una vez realizada esta configuración, la aplicación podrá conectarse normalmente a la base de datos.

## Requisitos
===

# 

# \- Java JDK 23 o superior

# \- Apache NetBeans 25

# \- JavaFX SDK

# \- MySQL 8

# \- MySQL Workbench (opcional, para administrar la base de datos)

# Video de presentación se encuentra en el siguiente enlace:

# https://drive.google.com/drive/folders/1C7tG2N_XHFp5a4PZtU-UG_JK_oE9Jo4F?usp=sharing

