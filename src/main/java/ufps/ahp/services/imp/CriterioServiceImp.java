package ufps.ahp.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufps.ahp.dao.CriterioDAO;
import ufps.ahp.model.Criterio;
import ufps.ahp.services.CriterioService;

import java.util.List;

@Service
public class CriterioServiceImp implements CriterioService {

    @Autowired
    CriterioDAO CriterioDAO;

    @Override
    public List<Criterio> listar() {
        return CriterioDAO.findAll();
    }

    @Override
    public Criterio buscar(int idCriterio) {
        return CriterioDAO.findById(idCriterio).orElse(null);
    }

    @Override
    public void guardar(Criterio ct) {
        CriterioDAO.save(ct);
    }

    @Override
    public void eliminar(Criterio a) {
        CriterioDAO.delete(a);
    }
}
