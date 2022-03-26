package ufps.ahp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ufps.ahp.model.PuntuacionAlternativaCriterio;

public interface PuntuacionAlternativaCriterioDAO  extends JpaRepository<PuntuacionAlternativaCriterio, Integer> {
    @Transactional
    @Modifying
    @Query(value =
            "insert " +
                    "into puntuacion_criterio_dao (alternativa1_id, alternativa2_id, problema) " +
                    "SELECT a1.id_alternativa, a2.id_alternativa, a1.problema " +
                    "from alternativa a1, alternativa a2 " +
                    "where a1.problema = a2.problema and a1.id_criterio<=a2.id_criterio",
            nativeQuery = true)
    void llenarPuntuacionCriterioAlternativa();
}
