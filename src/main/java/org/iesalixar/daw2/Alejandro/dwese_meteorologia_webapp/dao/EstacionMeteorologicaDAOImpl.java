package org.iesalixar.daw2.Alejandro.dwese_meteorologia_webapp.dao;


import org.iesalixar.daw2.Alejandro.dwese_meteorologia_webapp.entity.EstacionMeteorologica;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Repository
public class EstacionMeteorologicaDAOImpl implements EstacionMeteorologicaDAO {

    private static final Logger logger = LoggerFactory.getLogger(EstacionMeteorologicaDAOImpl.class);
    private final JdbcTemplate jdbcTemplate;

    public EstacionMeteorologicaDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EstacionMeteorologica> listAllEstaciones() {
        logger.info("Listando todas las estaciones meteorológicas desde la base de datos.");
        String sql = "SELECT * FROM estaciones_meteorologicas";
        List<EstacionMeteorologica> estacion = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EstacionMeteorologica.class));
        return estacion;
    }

    @Override
    public void insertEstacion(EstacionMeteorologica estacion) {
        logger.info("Insertando nueva estación con código: {}", estacion.getCodigo());
        String sql = "INSERT INTO estaciones_meteorologicas (codigo, nombre, fecha_instalacion) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, estacion.getCodigo(), estacion.getNombre(), estacion.getFechaInstalacion());
    }

    @Override
    public EstacionMeteorologica getEstacionById(int id) {
        logger.info("Recuperando estación por ID: {}", id);
        String sql = "SELECT * FROM estaciones_meteorologicas WHERE id = ?";
        try {
            EstacionMeteorologica estacion = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(EstacionMeteorologica.class), id);
            return estacion;
        } catch (Exception e) {
            logger.warn("No se encontró ninguna estación con ID: {}", id);
            return null;
        }
    }

    @Override
    public void updateEstacion(EstacionMeteorologica estacion) {
        logger.info("Actualizando estación con ID: {}", estacion.getId());
        String sql = "UPDATE estaciones_meteorologicas SET codigo = ?, nombre = ?, fecha_instalacion = ? WHERE id = ?";
        jdbcTemplate.update(sql, estacion.getCodigo(), estacion.getNombre(), estacion.getFechaInstalacion(), estacion.getId());
    }

    @Override
    public void deleteEstacion(int id) {
        logger.info("Eliminando estación con ID: {}", id);
        String sql = "DELETE FROM estaciones_meteorologicas WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsEstacionByCode(String codigo) {
        logger.info("Comprobando si existe estación con código: {}", codigo);
        String sql = "SELECT COUNT(*) FROM estaciones_meteorologicas WHERE UPPER(codigo) = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, codigo.toUpperCase());
        return count != null && count > 0;
    }

    @Override
    public boolean existsEstacionByCodeAndNotId(String codigo, Long id) {
        logger.info("Comprobando si existe estación con código: {} excluyendo ID: {}", codigo, id);
        String sql = "SELECT COUNT(*) FROM estaciones_meteorologicas WHERE UPPER(codigo) = ? AND id != ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, codigo.toUpperCase(), id);
        return count != null && count > 0;
    }
}