package co.edu.uniandes.dse.parcial1.entities;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
// Mercancía: nombre,
//  código de barras, fecha de recepción, cantidad disponible.
@Data
@Entity
public class MercanciaEntity extends BaseEntity{
   
    private String nombre;
    private String codigoBarras;
    private LocalDateTime fechaRecepcion;
    private int cantidadDisponible;

    @ManyToOne
    private UbicacionBodegaEntity ubicacionBodegaEntity;
}
