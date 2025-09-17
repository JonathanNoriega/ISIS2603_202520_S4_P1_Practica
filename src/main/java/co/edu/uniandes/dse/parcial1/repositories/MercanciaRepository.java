package co.edu.uniandes.dse.parcial1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import java.util.Optional;


public interface MercanciaRepository extends JpaRepository<MercanciaEntity, Long> {
    Optional<MercanciaEntity> findByCodigoBarras(int codigoBarras);
}
