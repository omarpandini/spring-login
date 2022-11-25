package com.br.springlogin.controllers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.springlogin.config.Uteis;
import com.br.springlogin.models.Usuario;
import com.br.springlogin.repository.UsuarioRepository;

@Controller
public class MainController {	
	
	@Autowired
	UsuarioRepository repo;
	
	@GetMapping(value = "/main")
	public String main(Model model, HttpSession session) {		
		
		Usuario usuarioLogado = Uteis.validarSessao(session);				
		
		if(usuarioLogado == null) {
			System.out.println("Sessão encerrada");
			return "index";
		}
		
		model.addAttribute("usuario", usuarioLogado);
		return "main";
	}
	
	@PostMapping(value = "/main")
	public String main(Model model, HttpSession session, String login, String senha) {
		
		String senhaCripto = Uteis.criptografa(senha);	
		
		Uteis.msg("senha: "+senhaCripto);
		
		Usuario usuarioLogin = repo.findByLogin(login);			
		
		if(usuarioLogin != null && senhaCripto.equals(usuarioLogin.getSenha())) {
			System.out.println("Login OK senha: " + usuarioLogin.getSenha());
			System.out.println("senha cripto " +senhaCripto );
		}else {
			System.out.println("Senha ou usuário inválidos");	
			return "index";
		}
				
		session.setAttribute("sessao", usuarioLogin);							
		
		model.addAttribute("usuario", usuarioLogin);
		return "main";
	}
	
	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}

}
