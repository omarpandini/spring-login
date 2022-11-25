package com.br.springlogin.models;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.br.springlogin.config.Uteis;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "login_usuario")
	@Size(min = 3,message = "Erro: o login deve conter no mínimo 3 caracteres")
	private String login;
	
	@Column(name = "senha_usuario")
	@Size(min = 6,message = "Erro: A senha deve conter no mínimo 6 caracteres")
	private String senha;
	
	@Column(name = "nome_usuario")
	@Size(min = 4, message = "Erro: Nome deve conter no mínimo 4 caracteres")
	private String nome;
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login.toUpperCase().trim();
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {            
		this.senha = Uteis.criptografa(senha);
	}		
		
		
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if(nome != null) {
			this.nome = nome.toUpperCase().trim();
		}
	}
	
	public Usuario(String login, String senha, String nome) {
		super();
		setLogin(login);
		setSenha(senha);
		setNome(nome);
	}
	
	public Usuario() {
		super();
	}
	
	

}
