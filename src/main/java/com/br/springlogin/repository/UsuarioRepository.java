package com.br.springlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.springlogin.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
