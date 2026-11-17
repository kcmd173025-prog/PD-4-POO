import bd.ConexionBD;
import controlador.FeriaControlador;
import modelo.Editorial;
import modelo.Reservacion;
import modelo.Stand;
import vista.VistaConsola;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        VistaConsola vista = new VistaConsola();
        ConexionBD conexionBD = new ConexionBD();

        // 1. Verificar conexion a la Base de Datos SQLite
        Connection conn = conexionBD.conectar();
        if (conn == null) {
            vista.mostrarMensaje("No se pudo establecer conexi\u00F3n con la base de datos.");
            return;
        }
        vista.mostrarMensaje("[OK] Conexi\u00F3n exitosa a la base de datos SQLite (feria_stands.db).");

        // 2. Crear tablas en SQLite si aun no existen
        conexionBD.inicializarTablas();
        vista.mostrarMensaje("[OK] Tablas verificadas/creadas: stands, editoriales, reservaciones.");

        // 3. Inicializar Controlador (MVC)
        FeriaControlador controlador = new FeriaControlador(conn, vista);

        // 4. Operacion CREATE (Insertar registros iniciales de prueba)
        Stand s1 = new Stand("ST-01", 12.0, 300.0, "OCUPADO");
        Stand s2 = new Stand("ST-02", 9.5, 300.0, "RESERVADO");
        Stand s3 = new Stand("ST-03", 20.0, 300.0, "DISPONIBLE");
        Stand s4 = new Stand("ST-04", 15.0, 300.0, "BLOQUEADO");

        controlador.registrarStand(s1);
        controlador.registrarStand(s2);
        controlador.registrarStand(s3);
        controlador.registrarStand(s4);

        Editorial e1 = new Editorial("ED-01", "Editorial Planeta", "contacto@planeta.com", "999-123-4567");
        Editorial e2 = new Editorial("ED-02", "Penguin Random House", "ferias@penguin.com", "999-765-4321");
        Editorial e3 = new Editorial("ED-03", "Fondo de Cultura Econ\u00F3mica", "eventos@fce.com", "999-555-8899");

        controlador.registrarEditorial(e1);
        controlador.registrarEditorial(e2);
        controlador.registrarEditorial(e3);

        Reservacion r1 = new Reservacion("RES-01", "ST-01", "ED-01", "2026-11-12", "PAGADO", s1.getPrecioTotal());
        Reservacion r2 = new Reservacion("RES-02", "ST-02", "ED-02", "2026-11-13", "PENDIENTE", s2.getPrecioTotal());

        controlador.registrarReservacion(r1);
        controlador.registrarReservacion(r2);

        vista.mostrarMensaje("[OK] Registros iniciales procesados (Operaci\u00F3n CREATE).");

        // 5. Operacion READ (Consultar y presentar las listas de cada tabla)
        controlador.listarStands();
        controlador.listarEditoriales();
        controlador.listarReservaciones();

        // 6. Cerrar conexion
        conexionBD.cerrar();
        vista.mostrarMensaje("[OK] Conexi\u00F3n cerrada correctamente.");
    }
}
