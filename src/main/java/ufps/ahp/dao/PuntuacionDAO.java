package ufps.ahp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ufps.ahp.model.Puntuacion;
import ufps.ahp.model.PuntuacionAlternativa;

public interface PuntuacionDAO extends JpaRepository<Puntuacion, Integer> {
}
