package modelo;

public class Stand {
    private String id;
    private double metrosCuadrados;
    private double costoMetroCuadrado;
    private String estado;

    public Stand(String id, double metrosCuadrados, double costoMetroCuadrado, String estado) {
        this.id = id;
        this.metrosCuadrados = metrosCuadrados;
        this.costoMetroCuadrado = costoMetroCuadrado;
        this.estado = estado;
    }

    public String getId() { return id; }
    public double getMetrosCuadrados() { return metrosCuadrados; }
    public double getCostoMetroCuadrado() { return costoMetroCuadrado; }
    public String getEstado() { return estado; }

    public double getPrecioTotal() {
        return metrosCuadrados * costoMetroCuadrado;
    }
}
