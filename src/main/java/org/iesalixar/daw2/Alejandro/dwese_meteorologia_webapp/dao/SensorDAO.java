package org.iesalixar.daw2.Alejandro.dwese_meteorologia_webapp.dao;


import org.iesalixar.daw2.Alejandro.dwese_meteorologia_webapp.entity.Sensor;
import java.util.List;

public interface SensorDAO {
    List<Sensor> listAllSensores();
    void insertSensor(Sensor sensor);
    Sensor getSensorById(int id);
    void updateSensor(Sensor sensor);
    void deleteSensor(int id);
}
