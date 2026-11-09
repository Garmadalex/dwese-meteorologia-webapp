INSERT IGNORE INTO estaciones_meteorologicas (id, codigo, nombre, fecha_instalacion) VALUES
(1, 'EST-01', 'Estación Central Sevilla', '2022-03-15'),
(2, 'EST-02', 'Estación Costera Cádiz', '2023-07-20');

INSERT IGNORE INTO sensores (id, modelo, activo, rango_medicion, estacion_id) VALUES
(1, 'Termómetro Láser', TRUE, 50, 1),
(2, 'Pluviómetro Óptico', FALSE, 200, 1),
(3, 'Barómetro Digital', TRUE, 100, 2);