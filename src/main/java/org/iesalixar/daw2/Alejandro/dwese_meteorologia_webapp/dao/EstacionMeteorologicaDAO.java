package org.iesalixar.daw2.Alejandro.dwese_meteorologia_webapp.dao;


import org.iesalixar.daw2.Alejandro.dwese_meteorologia_webapp.entity.EstacionMeteorologica;
import java.util.List;

public interface EstacionMeteorologicaDAO {
    List<EstacionMeteorologica> listAllEstaciones();
    void insertEstacion(EstacionMeteorologica estacion);
    EstacionMeteorologica getEstacionById(int id);
    void updateEstacion(EstacionMeteorologica estacion);
    void deleteEstacion(int id);
    boolean existsEstacionByCode(String codigo);
    boolean existsEstacionByCodeAndNotId(String codigo, Long id);
}
