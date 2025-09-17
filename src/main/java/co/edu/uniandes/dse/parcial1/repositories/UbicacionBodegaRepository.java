package co.edu.uniandes.dse.parcial1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;

public interface UbicacionBodegaRepository extends JpaRepository<UbicacionBodegaEntity, Long> {
        Optional<UbicacionBodegaEntity> findByNumeroEstante(int numeroEstante);
}
