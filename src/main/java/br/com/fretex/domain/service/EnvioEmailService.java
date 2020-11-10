package br.com.fretex.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.fretex.domain.exception.NegocioException;

@Service
@PropertySource("classpath:env/mail.properties")
public class EnvioEmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;

	public void enviar(Mensagem mensagem) {
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

			simpleMailMessage.setFrom(mensagem.getRemetente());

			if (env.getProperty("mail.remetente-teste").isBlank()) {
				simpleMailMessage
						.setTo(mensagem.getDestinatarios().toArray(new String[mensagem.getDestinatarios().size()]));
			} else {
				simpleMailMessage.setTo(env.getProperty("mail.remetente-teste"));
			}

			simpleMailMessage.setSubject(mensagem.getAssunto());
			simpleMailMessage.setText(mensagem.getCorpo());

			javaMailSender.send(simpleMailMessage);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("Não foi possível enviar e-mail.");
		}
	}

	public static class Mensagem {

		private String remetente;

		private List<String> destinatarios;

		private String assunto;

		private String corpo;

		public Mensagem(String remetente, List<String> destinatarios, String assunto, String corpo) {
			this.remetente = remetente;
			this.destinatarios = destinatarios;
			this.assunto = assunto;
			this.corpo = corpo;
		}

		public String getRemetente() {
			return remetente;
		}

		public void setRemetente(String remetente) {
			this.remetente = remetente;
		}

		public List<String> getDestinatarios() {
			return destinatarios;
		}

		public void setDestinatarios(List<String> destinatarios) {
			this.destinatarios = destinatarios;
		}

		public String getAssunto() {
			return assunto;
		}

		public void setAssunto(String assunto) {
			this.assunto = assunto;
		}

		public String getCorpo() {
			return corpo;
		}

		public void setCorpo(String corpo) {
			this.corpo = corpo;
		}
	}
}
