package ufps.ahp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ufps.ahp.model.Problema;
import ufps.ahp.model.PuntuacionAlternativa;

public interface PuntuacionAlternativaDAO  extends JpaRepository<PuntuacionAlternativa, Integer> {
}
