CREATE TABLE IF NOT EXISTS estaciones_meteorologicas (
     id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(10) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    fecha_instalacion DATE NOT NULL
    );

CREATE TABLE IF NOT EXISTS sensores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(50) NOT NULL,
    activo BOOLEAN NOT NULL,
    rango_medicion INT NOT NULL,
    estacion_id INT NOT NULL,
    CONSTRAINT fk_sensor_estacion FOREIGN KEY (estacion_id)
    REFERENCES estaciones_meteorologicas(id) ON DELETE CASCADE
    );