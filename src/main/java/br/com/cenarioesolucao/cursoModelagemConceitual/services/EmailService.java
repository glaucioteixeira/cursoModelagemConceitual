package br.com.cenarioesolucao.cursoModelagemConceitual.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.cenarioesolucao.cursoModelagemConceitual.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
