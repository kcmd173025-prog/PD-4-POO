package modelo;

public class Reservacion {
    private String id;
    private String idStand;
    private String idEditorial;
    private String fechaReservacion;
    private String estadoPago;
    private double montoTotal;

    public Reservacion(String id, String idStand, String idEditorial, String fechaReservacion, String estadoPago, double montoTotal) {
        this.id = id;
        this.idStand = idStand;
        this.idEditorial = idEditorial;
        this.fechaReservacion = fechaReservacion;
        this.estadoPago = estadoPago;
        this.montoTotal = montoTotal;
    }

    public String getId() { return id; }
    public String getIdStand() { return idStand; }
    public String getIdEditorial() { return idEditorial; }
    public String getFechaReservacion() { return fechaReservacion; }
    public String getEstadoPago() { return estadoPago; }
    public double getMontoTotal() { return montoTotal; }
}
