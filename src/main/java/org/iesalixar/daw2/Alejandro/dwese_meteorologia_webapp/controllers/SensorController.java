package org.iesalixar.daw2.Alejandro.dwese_meteorologia_webapp.controllers;




import jakarta.validation.Valid;
import org.iesalixar.daw2.Alejandro.dwese_meteorologia_webapp.dao.EstacionMeteorologicaDAOImpl;
import org.iesalixar.daw2.Alejandro.dwese_meteorologia_webapp.dao.SensorDAO;
import org.iesalixar.daw2.Alejandro.dwese_meteorologia_webapp.entity.Sensor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/sensores")
public class SensorController {

    private static final Logger logger = LoggerFactory.getLogger(SensorController.class);

    @Autowired
    private SensorDAO sensorDAO;

    @Autowired
    private EstacionMeteorologicaDAOImpl estacionDAO;

    @GetMapping
    public String listSensores(Model model) {
        List<Sensor> listSensores = sensorDAO.listAllSensores();
        model.addAttribute("listSensores", listSensores);
        return "sensor";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("sensor", new Sensor());
        model.addAttribute("listEstaciones", estacionDAO.listAllEstaciones());
        return "sensor-form";
    }

    @PostMapping("/insert")
    public String insertSensor(@Valid @ModelAttribute("sensor") Sensor sensor,
                               BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("listEstaciones", estacionDAO.listAllEstaciones());
            return "sensor-form";
        }
        sensorDAO.insertSensor(sensor);
        redirectAttributes.addFlashAttribute("successMessage", "Sensor insertado con éxito.");
        return "redirect:/sensores";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") int id, Model model) {
        Sensor sensor = sensorDAO.getSensorById(id);
        model.addAttribute("sensor", sensor);
        model.addAttribute("listEstaciones", estacionDAO.listAllEstaciones());
        return "sensor-form";
    }

    @PostMapping("/update")
    public String updateSensor(@Valid @ModelAttribute("sensor") Sensor sensor,
                               BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("listEstaciones", estacionDAO.listAllEstaciones());
            return "sensor-form";
        }
        sensorDAO.updateSensor(sensor);
        redirectAttributes.addFlashAttribute("successMessage", "Sensor actualizado con éxito.");
        return "redirect:/sensores";
    }

    @PostMapping("/delete")
    public String deleteSensor(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        sensorDAO.deleteSensor(id);
        redirectAttributes.addFlashAttribute("successMessage", "Sensor eliminado con éxito.");
        return "redirect:/sensores";
    }
}