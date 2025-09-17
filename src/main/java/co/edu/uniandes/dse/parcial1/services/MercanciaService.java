package co.edu.uniandes.dse.parcial1.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.MercanciaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MercanciaService {
    @Autowired
    private MercanciaRepository mercanciaRepository;

    @Transactional
    public MercanciaEntity createMercancia(MercanciaEntity mercancia) throws IllegalOperationException{

        log.info("Iniciando creación de mercancia: {}", mercancia.getNombre());
        
        // Validación: nombre vacío 
        if (mercancia.getNombre() == null || mercancia.getNombre().trim().isEmpty()) {
            throw new IllegalOperationException("El nombre de la mercancia no puede estar vacío");
        }

        // Validación: codigoBarras duplicado
        List<MercanciaEntity> existentes = mercanciaRepository.findByCodigoBarras(mercancia.getCodigoBarras());
        if (!existentes.isEmpty()) {
            throw new IllegalOperationException("Ya existe una mercancia con el codigo de barras: " + mercancia.getCodigoBarras());
        }

        // Revisar fecha de recepcion

        LocalDateTime endDateTime = mercancia.getFechaRecepcion();
        Duration duration = Duration.between(LocalDateTime.now(), endDateTime);
        if(duration.isNegative()){
            throw new IllegalOperationException("La fecha de recepción de la mercancía es posterior a la actual.");
        }

        return mercanciaRepository.save(mercancia);
    }
}
