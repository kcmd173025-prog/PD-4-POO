package modelo;

public class Editorial {
    private String id;
    private String nombre;
    private String correoContacto;
    private String telefono;

    public Editorial(String id, String nombre, String correoContacto, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correoContacto = correoContacto;
        this.telefono = telefono;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreoContacto() { return correoContacto; }
    public String getTelefono() { return telefono; }
}
