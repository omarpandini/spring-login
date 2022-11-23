package com.br.springlogin.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.springlogin.config.Uteis;
import com.br.springlogin.models.Usuario;
import com.br.springlogin.repository.UsuarioRepository;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repo;
	
	@GetMapping(value = "/cadastro")
	public String cadastro(HttpSession session, Model model) {
		
		Usuario usuarioLogado = Uteis.validarSessao(session);
		
		model.addAttribute("usuario", new Usuario());
		
		if(usuarioLogado == null) {
			System.out.println("Sessão encerrada");
			return "index";
		}
		return "usuario/cadastro";
	}
	
	@GetMapping(value = "/listar")
	public String listar(HttpSession session) {
		
		Usuario usuarioLogado = Uteis.validarSessao(session);				
		
		if(usuarioLogado == null) {
			System.out.println("Sessão encerrada");
			return "index";
		}
		
		return "usuario/listar";
	}
	
	@PostMapping(value = "/salvar")
	public String salvar( HttpSession session, @Valid Usuario usuario, BindingResult result ,  RedirectAttributes attr, Model model) {
		
		Usuario usuarioLogado = Uteis.validarSessao(session);	
		
		if(usuarioLogado == null) {
			System.out.println("Sessão encerrada");
			return "index";
		}	
		
		if(result.hasErrors()) {
			System.out.println("Teve erros no formulário");
			//attr.addFlashAttribute("mensagemErro", "Verificar erros no formulário");
			model.addAttribute("mensagemErro", "Verificar erros no formulário");
			return "usuario/cadastro";
		}
		
		repo.save(usuario);	
		
		attr.addFlashAttribute("mensagemOk", "Usuário Salvo Com Sucesso!");
		
		return "redirect:/usuario/cadastro";
	}

}
