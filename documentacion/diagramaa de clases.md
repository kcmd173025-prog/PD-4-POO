# Diagrama de Clases (Patrón MVC + Capa BD)

El siguiente diagrama representa la estructura orientada a objetos del sistema, mostrando en bloques visuales las clases de las capas **Modelo**, **Vista**, **Controlador** y **Base de Datos (DAO)**, así como sus relaciones de dependencia y asociación.

```mermaid
classDiagram
    direction TB

    class Main {
        +main(String[] args) void
    }

    class VistaConsola {
        +mostrarMensaje(String mensaje) void
        +mostrarStands(List~Stand~ stands) void
        +mostrarEditoriales(List~Editorial~ editoriales) void
        +mostrarReservaciones(List~Reservacion~ reservaciones) void
    }

    class FeriaControlador {
        -StandDAO standDAO
        -EditorialDAO editorialDAO
        -ReservacionDAO reservacionDAO
        -VistaConsola vista
        +FeriaControlador(Connection conexion, VistaConsola vista)
        +registrarStand(Stand stand) void
        +registrarEditorial(Editorial editorial) void
        +registrarReservacion(Reservacion reservacion) void
        +listarStands() void
        +listarEditoriales() void
        +listarReservaciones() void
    }

    class ConexionBD {
        -String URL
        -Connection conexion
        +conectar() Connection
        +inicializarTablas() void
        +cerrar() void
    }

    class StandDAO {
        -Connection conexion
        +StandDAO(Connection conexion)
        +crear(Stand stand) boolean
        +leerTodos() List~Stand~
    }

    class EditorialDAO {
        -Connection conexion
        +EditorialDAO(Connection conexion)
        +crear(Editorial editorial) boolean
        +leerTodas() List~Editorial~
    }

    class ReservacionDAO {
        -Connection conexion
        +ReservacionDAO(Connection conexion)
        +crear(Reservacion res) boolean
        +leerTodas() List~Reservacion~
    }

    class Stand {
        -String id
        -double metrosCuadrados
        -double costoMetroCuadrado
        -String estado
        +Stand(String id, double metrosCuadrados, double costoMetroCuadrado, String estado)
        +getId() String
        +getMetrosCuadrados() double
        +getCostoMetroCuadrado() double
        +getEstado() String
        +getPrecioTotal() double
    }

    class Editorial {
        -String id
        -String nombre
        -String correoContacto
        -String telefono
        +Editorial(String id, String nombre, String correoContacto, String telefono)
        +getId() String
        +getNombre() String
        +getCorreoContacto() String
        +getTelefono() String
    }

    class Reservacion {
        -String id
        -String idStand
        -String idEditorial
        -String fechaReservacion
        -String estadoPago
        -double montoTotal
        +Reservacion(String id, String idStand, String idEditorial, String fechaReservacion, String estadoPago, double montoTotal)
        +getId() String
        +getIdStand() String
        +getIdEditorial() String
        +getFechaReservacion() String
        +getEstadoPago() String
        +getMontoTotal() double
    }

    Main --> ConexionBD : inicia conexion
    Main --> VistaConsola : instancia
    Main --> FeriaControlador : coordina
    FeriaControlador --> VistaConsola : actualiza vista
    FeriaControlador --> StandDAO : usa
    FeriaControlador --> EditorialDAO : usa
    FeriaControlador --> ReservacionDAO : usa
    StandDAO ..> Stand : mapea
    EditorialDAO ..> Editorial : mapea
    ReservacionDAO ..> Reservacion : mapea
```