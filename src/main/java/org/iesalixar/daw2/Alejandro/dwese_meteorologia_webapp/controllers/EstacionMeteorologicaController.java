package org.iesalixar.daw2.Alejandro.dwese_meteorologia_webapp.controllers;


import jakarta.validation.Valid;
import org.iesalixar.daw2.Alejandro.dwese_meteorologia_webapp.dao.EstacionMeteorologicaDAO;
import org.iesalixar.daw2.Alejandro.dwese_meteorologia_webapp.entity.EstacionMeteorologica;
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
@RequestMapping("/estaciones")
public class EstacionMeteorologicaController {

    private static final Logger logger = LoggerFactory.getLogger(EstacionMeteorologicaController.class);

    @Autowired
    private EstacionMeteorologicaDAO estacionDAO;

    @GetMapping
    public String listEstaciones(Model model) {
        logger.info("Solicitando la lista de estaciones meteorológicas...");
        List<EstacionMeteorologica> listEstaciones = estacionDAO.listAllEstaciones();
        model.addAttribute("listEstaciones", listEstaciones);
        return "estacion";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        logger.info("Mostrando formulario para nueva estación.");
        model.addAttribute("estacionMeteorologica", new EstacionMeteorologica());
        return "estacion-form";
    }

    @PostMapping("/insert")
    public String insertEstacion(@Valid @ModelAttribute("estacionMeteorologica") EstacionMeteorologica estacion,
                                 BindingResult result, RedirectAttributes redirectAttributes) {
        logger.info("Insertando nueva estación con código {}", estacion.getCodigo());
        if (result.hasErrors()) {
            return "estacion-form";
        }
        if (estacionDAO.existsEstacionByCode(estacion.getCodigo())) {
            redirectAttributes.addFlashAttribute("errorMessage", "El código de la estación ya existe.");
            return "redirect:/estaciones/new";
        }
        estacionDAO.insertEstacion(estacion);
        redirectAttributes.addFlashAttribute("successMessage", "Estación insertada con éxito.");
        return "redirect:/estaciones";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") int id, Model model) {
        logger.info("Mostrando formulario de edición para estación ID {}", id);
        EstacionMeteorologica estacion = estacionDAO.getEstacionById(id);
        model.addAttribute("estacionMeteorologica", estacion);
        return "estacion-form";
    }

    @PostMapping("/update")
    public String updateEstacion(@Valid @ModelAttribute("estacionMeteorologica") EstacionMeteorologica estacion,
                                 BindingResult result, RedirectAttributes redirectAttributes) {
        logger.info("Actualizando estación ID {}", estacion.getId());
        if (result.hasErrors()) {
            return "estacion-form";
        }
        if (estacionDAO.existsEstacionByCodeAndNotId(estacion.getCodigo(), estacion.getId())) {
            redirectAttributes.addFlashAttribute("errorMessage", "El código ya existe para otra estación.");
            return "redirect:/estaciones/edit?id=" + estacion.getId();
        }
        estacionDAO.updateEstacion(estacion);
        redirectAttributes.addFlashAttribute("successMessage", "Estación actualizada con éxito.");
        return "redirect:/estaciones";
    }

    @PostMapping("/delete")
    public String deleteEstacion(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        logger.info("Eliminando estación ID {}", id);
        estacionDAO.deleteEstacion(id);
        redirectAttributes.addFlashAttribute("successMessage", "Estación eliminada con éxito.");
        return "redirect:/estaciones";
    }
}
