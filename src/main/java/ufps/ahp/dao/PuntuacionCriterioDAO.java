package ufps.ahp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ufps.ahp.model.PuntuacionAlternativa;
import ufps.ahp.model.PuntuacionCriterio;

public interface PuntuacionCriterioDAO extends JpaRepository<PuntuacionCriterio, Integer> {
    @Transactional
    @Modifying
    @Query(value =
                    "insert " +
                            "into puntuacion_criterio (criterio1_id, criterio2_id, problema) SELECT c1.id_criterio, c2.id_criterio, c1.problema from criterio c1, criterio c2 where c1.problema = c2.problema and c1.id_criterio<=c2.id_criterio",
            nativeQuery = true)
    void llenarPuntuacionCriterio();
}
