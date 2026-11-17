package bd;

import modelo.Editorial;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EditorialDAO {
    private Connection conexion;

    public EditorialDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // CREATE (Insertar Editorial)
    public boolean crear(Editorial editorial) {
        String sql = "INSERT OR IGNORE INTO editoriales (id, nombre, correo_contacto, telefono) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, editorial.getId());
            pstmt.setString(2, editorial.getNombre());
            pstmt.setString(3, editorial.getCorreoContacto());
            pstmt.setString(4, editorial.getTelefono());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar Editorial: " + e.getMessage());
            return false;
        }
    }

    // READ (Leer lista de Editoriales)
    public List<Editorial> leerTodas() {
        List<Editorial> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, correo_contacto, telefono FROM editoriales";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Editorial ed = new Editorial(
                    rs.getString("id"),
                    rs.getString("nombre"),
                    rs.getString("correo_contacto"),
                    rs.getString("telefono")
                );
                lista.add(ed);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer Editoriales: " + e.getMessage());
        }
        return lista;
    }
}
