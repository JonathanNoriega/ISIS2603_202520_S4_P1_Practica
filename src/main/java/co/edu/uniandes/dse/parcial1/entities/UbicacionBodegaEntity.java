package co.edu.uniandes.dse.parcial1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

// UbicaciónBodega: número de estante,
// canasta donde se deposita la mercancía y peso máximo que resiste.
@Data
@Entity
public class UbicacionBodegaEntity extends BaseEntity{
   private int numeroEstante;
   private String canasta;
   private int pesoMaximo;

   @OneToMany
   private MercanciaEntity mercancia;
}
