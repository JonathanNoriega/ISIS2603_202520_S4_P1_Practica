package co.edu.uniandes.dse.parcial1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import jakarta.transaction.Transactional;

@DataJpaTest
@Transactional
@Import(MercanciaService.class)
@SpringBootTest
public class MercanciaServiceTest {
    @Autowired
    private MercanciaService mercanciaService;

    @Test
    void crearMercancia_Exito() {
        MercanciaEntity m = new MercanciaEntity();
        m.setNombre("Papel A4");
        m.setCodigoBarras("ABC123");
        m.setFechaRecepcion(LocalDate.now());
        m.setCantidadDisponible(100);

        MercanciaEntity guardada = mercanciaService.createMercancia(m);

        assertNotNull(guardada.getCodigoBarras());
    }

    @Test
    void crearMercancia_FallaPorFechaFutura() {
        MercanciaEntity m = new MercanciaEntity();
        m.setNombre("Lapicero");
        m.setCodigoBarras("XYZ789");
        m.setFechaRecepcion(LocalDate.now().plusDays(1));
        m.setCantidadDisponible(50);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            mercanciaService.createMercancia(m);
        });

        assertEquals("La fecha de recepción no puede ser posterior a la actual.", ex.getMessage());
    }
}
