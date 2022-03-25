package ufps.ahp.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufps.ahp.dao.DecisorDAO;
import ufps.ahp.model.Decisor;
import ufps.ahp.services.DecisorService;

import java.util.List;

@Service
public class DecisorServiceImp implements DecisorService {

    @Autowired
    DecisorDAO decisorDAO;

    @Override
    public List<Decisor> listar() {
        return decisorDAO.findAll();
    }

    @Override
    public Decisor buscar(int idDecisor) {
        return decisorDAO.findById(idDecisor).orElse(null);
    }

    @Override
    public Decisor buscarPorEmail(String email) {
        return decisorDAO.findDecisorByEmail();
    }

    @Override
    public void guardar(Decisor decisor) {
        decisorDAO.save(decisor);
    }

    @Override
    public void eliminar(Decisor decisor) {
        decisorDAO.delete(decisor);
    }
}
