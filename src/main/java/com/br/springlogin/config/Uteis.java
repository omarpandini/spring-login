package com.br.springlogin.config;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
	
	public static String criptografa(String senha) {
		
		MessageDigest algorithm;
		String senhaCripto = null;
		try {
			
			algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

	        System.out.println(messageDigest);
	        
	        BigInteger bigInt = new BigInteger(1,messageDigest);
	        
	        senhaCripto = bigInt.toString(16);
	        
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return senhaCripto;
        
	}
	
	public static void msg(String msg) {
		System.out.println(msg);
	}
}
