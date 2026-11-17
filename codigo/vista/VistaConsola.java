package vista;

import modelo.Editorial;
import modelo.Reservacion;
import modelo.Stand;
import java.util.List;

public class VistaConsola {

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarStands(List<Stand> stands) {
        System.out.println("=========================================================================");
        System.out.println(" LISTA DE STANDS (Tabla: stands)");
        System.out.println("=========================================================================");
        System.out.printf("%-10s | %-12s | %-15s | %-15s | %-12s%n",
                "ID", "Tama\u00F1o (m2)", "Tarifa /m2", "Precio Total", "Estado");
        System.out.println("-------------------------------------------------------------------------");
        for (Stand s : stands) {
            System.out.printf("%-10s | %-12.2f | $%-14.2f | $%-14.2f | %-12s%n",
                    s.getId(), s.getMetrosCuadrados(), s.getCostoMetroCuadrado(),
                    s.getPrecioTotal(), s.getEstado());
        }
    }

    public void mostrarEditoriales(List<Editorial> editoriales) {
        System.out.println("=========================================================================");
        System.out.println(" LISTA DE EDITORIALES (Tabla: editoriales)");
        System.out.println("=========================================================================");
        System.out.printf("%-10s | %-25s | %-25s | %-12s%n",
                "ID", "Nombre Editorial", "Correo Contacto", "Tel\u00E9fono");
        System.out.println("-------------------------------------------------------------------------");
        for (Editorial e : editoriales) {
            System.out.printf("%-10s | %-25s | %-25s | %-12s%n",
                    e.getId(), e.getNombre(), e.getCorreoContacto(), e.getTelefono());
        }
    }

    public void mostrarReservaciones(List<Reservacion> reservaciones) {
        System.out.println("=====================================================================================");
        System.out.println(" LISTA DE RESERVACIONES (Tabla: reservaciones)");
        System.out.println("=====================================================================================");
        System.out.printf("%-10s | %-10s | %-12s | %-14s | %-14s | %-12s%n",
                "ID Res.", "ID Stand", "ID Editorial", "Fecha", "Estado Pago", "Monto Total");
        System.out.println("-------------------------------------------------------------------------------------");
        for (Reservacion r : reservaciones) {
            System.out.printf("%-10s | %-10s | %-12s | %-14s | %-14s | $%-11.2f%n",
                    r.getId(), r.getIdStand(), r.getIdEditorial(),
                    r.getFechaReservacion(), r.getEstadoPago(), r.getMontoTotal());
        }
    }
}
