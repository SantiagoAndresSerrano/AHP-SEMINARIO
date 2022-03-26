package ufps.ahp.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufps.ahp.dao.PuntuacionCriterioDAO;
import ufps.ahp.model.PuntuacionCriterio;
import ufps.ahp.services.PuntuacionCriterioServicio;

import java.util.List;
@Service
public class PuntuacionCriterioServicioImp implements PuntuacionCriterioServicio {

    @Autowired
    PuntuacionCriterioDAO puntuacionCriterioDAO;


    @Override
    public List<PuntuacionCriterio> listar() {
        return puntuacionCriterioDAO.findAll();
    }

    @Override
    public void agregarCriteriosPuntuacion() {
        puntuacionCriterioDAO.llenarPuntuacionCriterio();
    }

    @Override
    public PuntuacionCriterio buscar(int idPuntuacionCriterio) {
        return puntuacionCriterioDAO.findById(idPuntuacionCriterio).orElse(null);
    }

    @Override
    public void guardar(PuntuacionCriterio ct) {
        puntuacionCriterioDAO.save(ct);
    }

    @Override
    public void eliminar(PuntuacionCriterio a) {
        puntuacionCriterioDAO.delete(a);
    }
}
