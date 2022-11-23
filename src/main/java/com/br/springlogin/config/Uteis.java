package com.br.springlogin.config;

import javax.servlet.http.HttpSession;

import com.br.springlogin.models.Usuario;

public class Uteis {

	public static Usuario validarSessao(HttpSession session) {
		
		Usuario usuarioLogado = null;
		
		try {
			usuarioLogado = (Usuario) session.getAttribute("sessao");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return usuarioLogado; 
	}
}
