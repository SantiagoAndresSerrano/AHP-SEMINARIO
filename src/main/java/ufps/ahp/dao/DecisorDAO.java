package ufps.ahp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ufps.ahp.model.Decisor;

public interface DecisorDAO extends JpaRepository<Decisor, Integer> {
    Decisor findDecisorByEmail(String email);
}
