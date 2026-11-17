package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
    private static final String URL = "jdbc:sqlite:feria_stands.db";
    private Connection conexion;

    public Connection conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL);
                try (Statement stmt = conexion.createStatement()) {
                    stmt.execute("PRAGMA foreign_keys = ON;");
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontr\u00F3 el driver JDBC de SQLite. Verifica la carpeta lib/.");
        } catch (SQLException e) {
            System.err.println("Error al conectar con SQLite: " + e.getMessage());
        }
        return conexion;
    }

    public void inicializarTablas() {
        String sqlStands = "CREATE TABLE IF NOT EXISTS stands ("
                + "id TEXT PRIMARY KEY, "
                + "metros_cuadrados REAL NOT NULL, "
                + "costo_metro_cuadrado REAL NOT NULL, "
                + "estado TEXT NOT NULL"
                + ");";

        String sqlEditoriales = "CREATE TABLE IF NOT EXISTS editoriales ("
                + "id TEXT PRIMARY KEY, "
                + "nombre TEXT NOT NULL, "
                + "correo_contacto TEXT NOT NULL, "
                + "telefono TEXT NOT NULL"
                + ");";

        String sqlReservaciones = "CREATE TABLE IF NOT EXISTS reservaciones ("
                + "id TEXT PRIMARY KEY, "
                + "id_stand TEXT NOT NULL, "
                + "id_editorial TEXT NOT NULL, "
                + "fecha_reservacion TEXT NOT NULL, "
                + "estado_pago TEXT NOT NULL, "
                + "monto_total REAL NOT NULL, "
                + "FOREIGN KEY (id_stand) REFERENCES stands(id), "
                + "FOREIGN KEY (id_editorial) REFERENCES editoriales(id)"
                + ");";

        try (Statement stmt = conectar().createStatement()) {
            stmt.execute(sqlStands);
            stmt.execute(sqlEditoriales);
            stmt.execute(sqlReservaciones);
        } catch (SQLException e) {
            System.err.println("Error al crear tablas en la BD: " + e.getMessage());
        }
    }

    public void cerrar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexi\u00F3n: " + e.getMessage());
        }
    }
}
