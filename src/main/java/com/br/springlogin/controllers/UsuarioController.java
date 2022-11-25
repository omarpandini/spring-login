package com.br.springlogin.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String listar(HttpSession session,Model model) {
		
		Usuario usuarioLogado = Uteis.validarSessao(session);				
		
		if(usuarioLogado == null) {
			System.out.println("Sessão encerrada");
			return "index";
		}
		
	    List<Usuario>usuarios = repo.findAll(Sort.by(Sort.Direction.ASC, "login"));
	    
	    model.addAttribute("usuarios", usuarios);
		
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
	
	@GetMapping(value = "excluir/{id}")
	public String excluir(@PathVariable Long id) {
		System.out.println("Excluindo usuário: " + id);
		repo.deleteById(id);
		return "redirect:/usuario/listar";
	}
	
	@GetMapping(value = "editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		System.out.println("Editando usuário: " + id);
		Optional<Usuario> usuario = repo.findById(id);
		
		if(usuario.isPresent()) {
			model.addAttribute("usuario", usuario);
		}
		
		return "usuario/cadastro";
	}

}
