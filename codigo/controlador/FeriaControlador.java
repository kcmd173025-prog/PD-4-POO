package controlador;

import bd.EditorialDAO;
import bd.ReservacionDAO;
import bd.StandDAO;
import modelo.Editorial;
import modelo.Reservacion;
import modelo.Stand;
import vista.VistaConsola;
import java.sql.Connection;
import java.util.List;

public class FeriaControlador {
    private StandDAO standDAO;
    private EditorialDAO editorialDAO;
    private ReservacionDAO reservacionDAO;
    private VistaConsola vista;

    public FeriaControlador(Connection conexion, VistaConsola vista) {
        this.standDAO = new StandDAO(conexion);
        this.editorialDAO = new EditorialDAO(conexion);
        this.reservacionDAO = new ReservacionDAO(conexion);
        this.vista = vista;
    }

    // Operaciones CREATE (C)
    public void registrarStand(Stand stand) {
        standDAO.crear(stand);
    }

    public void registrarEditorial(Editorial editorial) {
        editorialDAO.crear(editorial);
    }

    public void registrarReservacion(Reservacion reservacion) {
        reservacionDAO.crear(reservacion);
    }

    // Operaciones READ (R) conectadas a la Vista
    public void listarStands() {
        List<Stand> stands = standDAO.leerTodos();
        vista.mostrarStands(stands);
    }

    public void listarEditoriales() {
        List<Editorial> editoriales = editorialDAO.leerTodas();
        vista.mostrarEditoriales(editoriales);
    }

    public void listarReservaciones() {
        List<Reservacion> reservaciones = reservacionDAO.leerTodas();
        vista.mostrarReservaciones(reservaciones);
    }
}
