package ufps.ahp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ufps.ahp.model.PasswordResetToken;

public interface PasswordResetTokenDAO extends JpaRepository<PasswordResetToken, Integer> {
    PasswordResetToken findPasswordResetTokenByToken(String token);
}
