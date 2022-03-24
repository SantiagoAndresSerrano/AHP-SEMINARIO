package ufps.ahp.services;

import ufps.ahp.model.Alternativa;

import java.util.List;

public interface AlternativaService {
    public List<Alternativa> listar();
    public Alternativa buscar(int idAlternativa);
    public void guardar(Alternativa ct);
    public void eliminar(Alternativa a);
}
