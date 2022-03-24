package ufps.ahp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ufps.ahp.model.PuntuacionAlternativa;
import ufps.ahp.model.PuntuacionCriterio;

public interface PuntuacionCriterioDAO extends JpaRepository<PuntuacionCriterio, Integer> {
}
