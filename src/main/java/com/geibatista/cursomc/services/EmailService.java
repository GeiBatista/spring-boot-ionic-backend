package com.geibatista.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.geibatista.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
