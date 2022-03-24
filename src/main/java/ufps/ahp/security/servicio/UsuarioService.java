/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.ahp.security.servicio;

/**
 *
 * @author santi
 */

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ufps.ahp.security.dao.UsuarioRepository;
import ufps.ahp.security.model.Usuario;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public Usuario findByConfirmationToken(String token){ return usuarioRepository.findUsuarioByConfirmationToken(token);}

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    
     @Transactional(readOnly = true)
    public boolean getExisteEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }


    @Transactional
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    @Transactional
    public Usuario guardar(Usuario usuario) {
        usuarioRepository.save(usuario);
        return usuario;
    }


    @Transactional
    public void eliminar(int id) {
        usuarioRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> encontrar(int id) {
        return usuarioRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }
    
}