package ufps.ahp.services;

import ufps.ahp.model.PuntuacionAlternativa;
import ufps.ahp.model.PuntuacionAlternativaCriterio;

import java.util.List;

public interface PuntuacionAlternativaServicio {
    public List<PuntuacionAlternativa> listar();
    public PuntuacionAlternativa buscar(int idPuntuacionAlternativa);
    public void guardar(PuntuacionAlternativa ct);
    public void eliminar(PuntuacionAlternativa a);
}
