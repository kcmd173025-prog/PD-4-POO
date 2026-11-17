package bd;

import modelo.Reservacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservacionDAO {
    private Connection conexion;

    public ReservacionDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // CREATE (Insertar Reservacion)
    public boolean crear(Reservacion res) {
        String sql = "INSERT OR IGNORE INTO reservaciones (id, id_stand, id_editorial, fecha_reservacion, estado_pago, monto_total) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, res.getId());
            pstmt.setString(2, res.getIdStand());
            pstmt.setString(3, res.getIdEditorial());
            pstmt.setString(4, res.getFechaReservacion());
            pstmt.setString(5, res.getEstadoPago());
            pstmt.setDouble(6, res.getMontoTotal());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar Reservaci\u00F3n: " + e.getMessage());
            return false;
        }
    }

    // READ (Leer lista de Reservaciones)
    public List<Reservacion> leerTodas() {
        List<Reservacion> lista = new ArrayList<>();
        String sql = "SELECT id, id_stand, id_editorial, fecha_reservacion, estado_pago, monto_total FROM reservaciones";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Reservacion r = new Reservacion(
                    rs.getString("id"),
                    rs.getString("id_stand"),
                    rs.getString("id_editorial"),
                    rs.getString("fecha_reservacion"),
                    rs.getString("estado_pago"),
                    rs.getDouble("monto_total")
                );
                lista.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer Reservaciones: " + e.getMessage());
        }
        return lista;
    }
}
