package co.edu.uniandes.dse.parcial1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.repositories.MercanciaRepository;
import co.edu.uniandes.dse.parcial1.repositories.UbicacionBodegaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MercanciaUbicacionBodegaService {

    @Autowired
    private MercanciaRepository mercanciaRepository;
    @Autowired
    private UbicacionBodegaRepository ubicacionBodegaRepository;

    //Asignar ubicacionBodega a mercancia
    @Transactional
    public UbicacionBodegaEntity addUbicacion(int numeroEstante, int codigoBarras) throws EntityNotFoundException {
        log.info("Inicia proceso de asignar una ubicacion con numeroEstante = {} a la mercancia con codigoBarras = {}", numeroEstante, codigoBarras);

        Optional<UbicacionBodegaEntity> ubicacionBodegaEntity = ubicacionBodegaRepository.findByNumeroEstante(numeroEstante);
        if (ubicacionBodegaEntity.isEmpty())
            throw new EntityNotFoundException("No se encontró la ubicacion con el numero de estante: " + numeroEstante);
       
        Optional<MercanciaEntity> mercanciaEntity = mercanciaRepository.findByCodigoBarras(codigoBarras);
        if (mercanciaEntity.isEmpty())
            throw new EntityNotFoundException("No se encontró la mercancia con el codigo de barras: " + codigoBarras);
        
        ubicacionBodegaEntity.get().setMercancia(mercanciaEntity.get());
        log.info("Termina proceso de asignar una ubicacion con numeroEstante = {} a la mercancia con codigoBarras = {}", numeroEstante, codigoBarras);
        return ubicacionBodegaEntity.get();
    }
}
