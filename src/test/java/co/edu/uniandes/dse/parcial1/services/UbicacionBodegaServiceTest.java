package co.edu.uniandes.dse.parcial1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;
import jakarta.transaction.Transactional;

@DataJpaTest
@Transactional
@Import(UbicacionBodegaService.class)
@SpringBootTest
public class UbicacionBodegaServiceTest {

 @Autowired
    private UbicacionBodegaService ubicacionBodegaService;

    @Test
    void crearUbicacion_Exito() {
        UbicacionBodegaEntity u = new UbicacionBodegaEntity();
        u.setNumeroEstante(5);
        u.setCanasta("A1");
        u.setPesoMaximo(200.0);

        UbicacionBodegaEntity guardada = UbicacionBodegaService.createUbicacionBodega(u);

        assertNotNull(guardada.getNumeroEstante();
    }

    @Test
    void crearUbicacion_FallaPorEstanteNegativo() {
        UbicacionBodegaEntity u = new UbicacionBodegaEntity();
        u.setNumeroEstante(-1);
        u.setCanasta("B2");
        u.setPesoMaximo(150.0);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            ubicacionService.createUbicacionBodega(u)(u);
        });

        assertEquals("El número de estante debe ser positivo.", ex.getMessage());
    }
}
