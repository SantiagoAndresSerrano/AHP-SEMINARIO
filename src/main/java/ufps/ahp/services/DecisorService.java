package ufps.ahp.services;

import ufps.ahp.model.Decisor;

import java.util.List;

public interface DecisorService{

    public List<Decisor> listar();
    public Decisor buscar(int idDecisor);
    public Decisor buscarPorEmail(String email);
    public void guardar(Decisor decisor);
    public void eliminar(Decisor decisor);

}
