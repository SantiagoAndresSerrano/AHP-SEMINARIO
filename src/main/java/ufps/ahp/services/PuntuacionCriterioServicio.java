package ufps.ahp.services;

import ufps.ahp.model.PuntuacionCriterio;

import java.util.List;

public interface PuntuacionCriterioServicio {
    public List<PuntuacionCriterio> listar();
    public void agregarCriteriosPuntuacion();
    public PuntuacionCriterio buscar(int idPuntuacionCriterio);
    public void guardar(PuntuacionCriterio ct);
    public void eliminar(PuntuacionCriterio a);
}
