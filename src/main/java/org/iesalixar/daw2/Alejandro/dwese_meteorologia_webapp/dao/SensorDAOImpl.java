package org.iesalixar.daw2.Alejandro.dwese_meteorologia_webapp.dao;


import org.iesalixar.daw2.Alejandro.dwese_meteorologia_webapp.entity.Sensor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Repository
public class SensorDAOImpl implements SensorDAO {

    private static final Logger logger = LoggerFactory.getLogger(SensorDAOImpl.class);
    private final JdbcTemplate jdbcTemplate;

    public SensorDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Sensor> listAllSensores() {
        logger.info("Listando todos los sensores desde la base de datos.");
        String sql = "SELECT * FROM sensores";
        List<Sensor> listaSensor = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Sensor.class));
        return listaSensor;
    }

    @Override
    public void insertSensor(Sensor sensor) {
        logger.info("Insertando nuevo sensor con modelo: {}", sensor.getModelo());
        String sql = "INSERT INTO sensores (modelo, activo, rango_medicion, estacion_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, sensor.getModelo(), sensor.getActivo(), sensor.getRangoMedicion(), sensor.getEstacionId());
    }

    @Override
    public Sensor getSensorById(int id) {
        logger.info("Recuperando sensor por ID: {}", id);
        String sql = "SELECT * FROM sensores WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Sensor.class), id);
        } catch (Exception e) {
            logger.warn("No se encontró ningún sensor con ID: {}", id);
            return null;
        }
    }

    @Override
    public void updateSensor(Sensor sensor) {
        logger.info("Actualizando sensor con ID: {}", sensor.getId());
        String sql = "UPDATE sensores SET modelo = ?, activo = ?, rango_medicion = ?, estacion_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, sensor.getModelo(), sensor.getActivo(), sensor.getRangoMedicion(), sensor.getEstacionId(), sensor.getId());
    }

    @Override
    public void deleteSensor(int id) {
        logger.info("Eliminando sensor con ID: {}", id);
        String sql = "DELETE FROM sensores WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
