package org.iesalixar.daw2.Alejandro.dwese_meteorologia_webapp.entity;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {

    private Long id;

    @NotEmpty(message = "El modelo no puede estar vacío")
    @Size(max = 50, message = "El modelo no puede tener más de 50 caracteres")
    private String modelo;

    @NotNull(message = "El estado activo no puede ser nulo")
    private Boolean activo; // Atributo tipo boolean visible

    @NotNull(message = "El rango de medición no puede ser nulo")
    private Integer rangoMedicion; // Atributo tipo número visible

    @NotNull(message = "La estación es obligatoria")
    private Long estacionId; // Campo para la relación JOIN
}