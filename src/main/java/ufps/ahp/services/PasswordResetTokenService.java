package ufps.ahp.services;

import ufps.ahp.model.PasswordResetToken;

import java.util.List;

public interface PasswordResetTokenService {
    public List<PasswordResetToken> listar();
    public PasswordResetToken buscarToken(String token);
    public void guardar(PasswordResetToken ct);
    public void eliminar(PasswordResetToken ct);
}
