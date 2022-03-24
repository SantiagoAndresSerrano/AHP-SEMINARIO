package ufps.ahp.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ufps.ahp.security.model.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
    Usuario findUsuarioByEmail(String email);
}
