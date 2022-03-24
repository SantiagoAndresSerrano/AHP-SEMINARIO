package ufps.ahp.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufps.ahp.dao.PasswordResetTokenDAO;
import ufps.ahp.model.PasswordResetToken;
import ufps.ahp.services.PasswordResetTokenService;

import java.util.List;

@Service
public class PasswordResetTokenImp implements PasswordResetTokenService {

    @Autowired
    PasswordResetTokenDAO passwordResetTokenDAO;

    @Override
    public List<PasswordResetToken> listar() {
        return passwordResetTokenDAO.findAll();
    }

    @Override
    public PasswordResetToken buscarToken(String token) {
        return passwordResetTokenDAO.findPasswordResetTokenByToken(token);
    }

    @Override
    public void guardar(PasswordResetToken ct) {
        passwordResetTokenDAO.save(ct);
    }

    @Override
    public void eliminar(PasswordResetToken ct) {
        passwordResetTokenDAO.delete(ct);
    }
}
