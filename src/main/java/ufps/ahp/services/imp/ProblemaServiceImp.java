package ufps.ahp.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufps.ahp.dao.ProblemaDAO;
import ufps.ahp.model.Alternativa;
import ufps.ahp.model.Criterio;
import ufps.ahp.model.Problema;
import ufps.ahp.services.ProblemaService;

import java.util.List;
@Service
public class ProblemaServiceImp implements ProblemaService {

    @Autowired
    ProblemaDAO problemaDAO;

    @Override
    public List<Problema> listar() {
        return problemaDAO.findAll();
    }

    @Override
    public Problema buscar(String idProblema) {
        return problemaDAO.getById(idProblema);
    }

    @Override
    public List<Criterio> criteriosPorProblema(String idProblema) {
        return problemaDAO.criteriorPorProblema(idProblema);
    }

    @Override
    public List<Alternativa> alternativasPorProblema(String idProblema) {
        return null;
    }

    @Override
    public void guardar(Problema p) {
        problemaDAO.save(p);
    }

    @Override
    public void eliminar(Problema p) {
        problemaDAO.delete(p);
    }
}
