package ufps.ahp.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufps.ahp.dao.PuntuacionAlternativaCriterioDAO;
import ufps.ahp.dao.PuntuacionAlternativaDAO;
import ufps.ahp.model.PuntuacionAlternativa;
import ufps.ahp.model.PuntuacionAlternativaCriterio;
import ufps.ahp.services.PuntuacionAlternativaCriterioServicio;
import ufps.ahp.services.PuntuacionAlternativaServicio;

import java.util.List;
@Service
public class PuntuacionAlternativaServicioImp implements PuntuacionAlternativaServicio {

    @Autowired
    PuntuacionAlternativaDAO puntuacionAlternativaDAO;

    @Override
    public List<PuntuacionAlternativa> listar() {
        return puntuacionAlternativaDAO.findAll();
    }

    @Override
    public PuntuacionAlternativa buscar(int idPuntuacionAlternativa) {
        return puntuacionAlternativaDAO.findById(idPuntuacionAlternativa).orElse(null);
    }

    @Override
    public void guardar(PuntuacionAlternativa ct) {
        puntuacionAlternativaDAO.save(ct);
    }

    @Override
    public void eliminar(PuntuacionAlternativa a) {
        puntuacionAlternativaDAO.delete(a);
    }
}