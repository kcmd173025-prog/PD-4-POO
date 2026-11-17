-- Esquema de Base de Datos SQLite para Sistema de Stands (PD4)

CREATE TABLE IF NOT EXISTS stands (
    id TEXT PRIMARY KEY,
    metros_cuadrados REAL NOT NULL,
    costo_metro_cuadrado REAL NOT NULL,
    estado TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS editoriales (
    id TEXT PRIMARY KEY,
    nombre TEXT NOT NULL,
    correo_contacto TEXT NOT NULL,
    telefono TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS reservaciones (
    id TEXT PRIMARY KEY,
    id_stand TEXT NOT NULL,
    id_editorial TEXT NOT NULL,
    fecha_reservacion TEXT NOT NULL,
    estado_pago TEXT NOT NULL,
    monto_total REAL NOT NULL,
    FOREIGN KEY (id_stand) REFERENCES stands(id),
    FOREIGN KEY (id_editorial) REFERENCES editoriales(id)
);
