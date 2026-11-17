package bd;

import modelo.Stand;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StandDAO {
    private Connection conexion;

    public StandDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // CREATE (Insertar Stand)
    public boolean crear(Stand stand) {
        String sql = "INSERT OR IGNORE INTO stands (id, metros_cuadrados, costo_metro_cuadrado, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, stand.getId());
            pstmt.setDouble(2, stand.getMetrosCuadrados());
            pstmt.setDouble(3, stand.getCostoMetroCuadrado());
            pstmt.setString(4, stand.getEstado());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar Stand: " + e.getMessage());
            return false;
        }
    }

    // READ (Leer lista de Stands)
    public List<Stand> leerTodos() {
        List<Stand> lista = new ArrayList<>();
        String sql = "SELECT id, metros_cuadrados, costo_metro_cuadrado, estado FROM stands";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Stand s = new Stand(
                    rs.getString("id"),
                    rs.getDouble("metros_cuadrados"),
                    rs.getDouble("costo_metro_cuadrado"),
                    rs.getString("estado")
                );
                lista.add(s);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer Stands: " + e.getMessage());
        }
        return lista;
    }
}
