package ufps.ahp.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufps.ahp.dao.PuntuacionAlternativaCriterioDAO;
import ufps.ahp.model.PuntuacionAlternativaCriterio;
import ufps.ahp.services.PuntuacionAlternativaCriterioServicio;

import java.util.List;
@Service
class PuntuacionAlternativaCriterioServiceImp implements PuntuacionAlternativaCriterioServicio {

    @Autowired
    PuntuacionAlternativaCriterioDAO PuntuacionAlternativaCriterioDAO;

    @Override
    public List<PuntuacionAlternativaCriterio> listar() {
        return PuntuacionAlternativaCriterioDAO.findAll();
    }

    @Override
    public PuntuacionAlternativaCriterio buscar(int idPuntuacionAlternativaCriterio) {
        return PuntuacionAlternativaCriterioDAO.getById(idPuntuacionAlternativaCriterio);
    }

    @Override
    public void guardar(PuntuacionAlternativaCriterio p) {
        PuntuacionAlternativaCriterioDAO.save(p);
    }

    @Override
    public void eliminar(PuntuacionAlternativaCriterio p) {
        PuntuacionAlternativaCriterioDAO.delete(p);
    }
}