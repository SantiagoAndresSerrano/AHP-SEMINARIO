package ufps.ahp.services;

import ufps.ahp.model.Alternativa;
import ufps.ahp.model.Criterio;

import java.util.List;

public interface CriterioService {
    public List<Criterio> listar();
    public Criterio buscar(int idCriterio);
    public void guardar(Criterio ct);
    public void eliminar(Criterio a);
}
