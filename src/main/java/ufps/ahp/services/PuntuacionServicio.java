package ufps.ahp.services;

import ufps.ahp.model.Puntuacion;
import ufps.ahp.model.PuntuacionCriterio;

import java.util.List;

public interface PuntuacionServicio {
    public List<Puntuacion> listar();
    public Puntuacion buscar(int idPuntuacion);
    public void guardar(Puntuacion ct);
    public void eliminar(Puntuacion a);
}
