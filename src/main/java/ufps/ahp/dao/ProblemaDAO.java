package ufps.ahp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ufps.ahp.model.Criterio;
import ufps.ahp.model.Problema;

import java.util.List;

public interface ProblemaDAO extends JpaRepository<Problema, String> {
    @Query(value = "SELECT c FROM Problema p, Criterio c WHERE p.idProblema = :id and p.idProblema=c.problema")
    List<Criterio>  criteriorPorProblema(@Param("id") String id);

}
