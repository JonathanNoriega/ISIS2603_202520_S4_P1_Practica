package co.edu.uniandes.dse.parcial1.services;

import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.UbicacionBodegaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UbicacionBodegaService {

    private UbicacionBodegaRepository ubicacionBodegaRepository;

    @Transactional
    public UbicacionBodegaEntity createUbicacionBodega(UbicacionBodegaEntity ubicacion) throws IllegalOperationException {
        log.info("Iniciando creación de ubicacion bodega: {}", ubicacion.getNombre());
        
        // Validación: numero de estante positivo
        if (ubicacion.getNumeroEstante() < 0) {
            throw new IllegalOperationException("El numero de estante debe ser positivo.");
        }

        return ubicacionBodegaRepository.save(ubicacion);
    }
}