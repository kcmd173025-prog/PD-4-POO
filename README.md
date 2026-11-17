# Sistema de Gestión de Stands - Feria del Libro (PD4)

## Descripción de lo Implementado
Este proyecto evoluciona el sistema base de gestión de stands (PD2) incorporando persistencia de datos mediante JDBC y SQLite, estructurado bajo el patrón arquitectónico **MVC (Modelo - Vista - Controlador) + Capa de Base de Datos (DAO)**.

El sistema permite almacenar y consultar de forma permanente la información de las tres entidades principales del recinto:
1. **Stands:** Espacios físicos con dimensiones en metros cuadrados, tarifa base, estado y cálculo dinámico de precio total.
2. **Editoriales:** Empresas expositoras registradas con sus datos de contacto.
3. **Reservaciones:** Registros transaccionales que vinculan un stand específico con una editorial, indicando fecha, estado de pago y monto total.

Para cumplir con los requerimientos de la práctica, la capa de datos implementa operaciones **CR (Create y Read)** utilizando consultas SQL parametrizadas (`INSERT` y `SELECT`), comprobando desde el método `main()` la conexión exitosa a SQLite y presentando en consola las listas completas de cada tabla.

---

## Arquitectura del Sistema (Patrón MVC + BD)

El código fuente dentro de la carpeta `codigo/` está organizado en paquetes independientes para garantizar la separación de responsabilidades:

* **`modelo/` (Capa Modelo):** Contiene las entidades puras del dominio (`Stand.java`, `Editorial.java`, `Reservacion.java`) con sus atributos, constructores, métodos de acceso (*getters*) y lógica de cálculo (`getPrecioTotal()`).
* **`bd/` (Capa de Base de Datos / Persistencia):**
  * `ConexionBD.java`: Gestiona la conexión JDBC con el archivo local `feria_stands.db`, activa las llaves foráneas (`PRAGMA foreign_keys = ON`) e inicializa las tablas si no existen.
  * `StandDAO.java`, `EditorialDAO.java` y `ReservacionDAO.java`: Clases de acceso a datos encargadas exclusivamente de ejecutar sentencias `INSERT` (Create) y `SELECT` (Read), mapeando cada `ResultSet` a listas de objetos Java.
* **`controlador/` (Capa Controlador):** `FeriaControlador.java` actúa como intermediario entre la capa de base de datos y la vista, coordinando el registro de datos y solicitando las listas para su visualización.
* **`vista/` (Capa Vista):** `VistaConsola.java` recibe las listas de objetos desde el controlador y las formatea en tablas legibles para la terminal, manteniendo la salida visual aislada de la lógica SQL.
* **`Main.java`:** Punto de entrada que verifica la conexión a SQLite, ejecuta la carga inicial de registros y despliega las tres tablas en consola.