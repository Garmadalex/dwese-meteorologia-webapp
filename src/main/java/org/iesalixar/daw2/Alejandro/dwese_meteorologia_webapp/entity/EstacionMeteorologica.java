package org.iesalixar.daw2.Alejandro.dwese_meteorologia_webapp.entity;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstacionMeteorologica {

    private Long id;

    @NotEmpty(message = "El código no puede estar vacío")
    @Size(max = 10, message = "El código no puede tener más de 10 caracteres")
    private String codigo;

    @NotEmpty(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @NotNull(message = "La fecha de instalación no puede ser nula")
    private LocalDate fechaInstalacion; // Atributo tipo fecha visible

    public EstacionMeteorologica(String codigo, String nombre, LocalDate fechaInstalacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaInstalacion = fechaInstalacion;
    }
}