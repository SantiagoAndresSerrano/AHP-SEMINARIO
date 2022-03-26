package ufps.ahp.services;

import ufps.ahp.model.Alternativa;
import ufps.ahp.model.PuntuacionAlternativaCriterio;

import java.util.List;

public interface PuntuacionAlternativaCriterioServicio {
    public List<PuntuacionAlternativaCriterio> listar();
    public PuntuacionAlternativaCriterio buscar(int idPuntuacionAlternativaCriterio);

    public void guardar(PuntuacionAlternativaCriterio ct);
    public void eliminar(PuntuacionAlternativaCriterio a);
}

