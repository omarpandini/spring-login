package com.br.springlogin.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.springlogin.config.Uteis;
import com.br.springlogin.models.Usuario;

@Controller
public class MainController {	

	
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
		Usuario usuario = new Usuario(login, senha,null);
		
		session.setAttribute("sessao", usuario);	

		
		Usuario usuarioLogado = null;
		
		try {
			usuarioLogado = (Usuario)session.getAttribute("sessao");
		} catch (Exception e) {
			System.out.println("Sessão encerrada");
			return "index";
		}
				
		
		model.addAttribute("usuario", usuarioLogado);
		return "main";
	}
	
	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}

}
