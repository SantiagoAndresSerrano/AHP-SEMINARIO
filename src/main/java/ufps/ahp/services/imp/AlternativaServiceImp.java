package ufps.ahp.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufps.ahp.dao.AlternativaDAO;
import ufps.ahp.model.Alternativa;
import ufps.ahp.services.AlternativaService;

import java.util.List;

@Service
public class AlternativaServiceImp implements AlternativaService {

    @Autowired
    AlternativaDAO alternativaDAO;

    @Override
    public List<Alternativa> listar() {
        return alternativaDAO.findAll();
    }

    @Override
    public Alternativa buscar(int idAlternativa) {
        return alternativaDAO.findById(idAlternativa).orElse(null);
    }

    @Override
    public void guardar(Alternativa ct) {
        alternativaDAO.save(ct);
    }

    @Override
    public void eliminar(Alternativa a) {
        alternativaDAO.delete(a);
    }
}
