package ufps.ahp.services;

import ufps.ahp.model.Alternativa;
import ufps.ahp.model.Criterio;
import ufps.ahp.model.Problema;

import java.util.List;

public interface ProblemaService {
    public List<Problema> listar();
    public Problema buscar(String idProblema);
    public List<Criterio> criteriosPorProblema(String idProblema);
    public List<Alternativa> alternativasPorProblema(String idProblema);
    public void guardar(Problema p);
    public void eliminar(Problema p);
}
