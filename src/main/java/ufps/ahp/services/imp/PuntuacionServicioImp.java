package ufps.ahp.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufps.ahp.dao.PuntuacionDAO;
import ufps.ahp.model.Puntuacion;
import ufps.ahp.services.PuntuacionServicio;

import java.util.List;
@Service
public class PuntuacionServicioImp implements PuntuacionServicio {

    @Autowired
    PuntuacionDAO puntuacionDAO;


    @Override
    public List<Puntuacion> listar() {
        return puntuacionDAO.findAll();
    }

    @Override
    public Puntuacion buscar(int idPuntuacion) {
        return puntuacionDAO.findById(idPuntuacion).orElse(null);
    }

    @Override
    public void guardar(Puntuacion ct) {
        puntuacionDAO.save(ct);
    }

    @Override
    public void eliminar(Puntuacion a) {
        puntuacionDAO.delete(a);
    }
}
