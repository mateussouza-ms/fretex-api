package br.com.fretex.domain.service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.fretex.domain.exception.NegocioException;
import br.com.fretex.domain.model.RecuperacaoSenha;
import br.com.fretex.domain.model.Usuario;
import br.com.fretex.domain.repository.RecuperacaoSenhaRepository;


@Service
public class RecuperacaoSenhaService {

	@Autowired
	private RecuperacaoSenhaRepository recuperacaoSenhaRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void recuperarSenha(Usuario usuario) {
		RecuperacaoSenha recuperacaoSenha = new RecuperacaoSenha();
		recuperacaoSenha.setUsuario(usuario);
		
		Integer codigo = new Random().nextInt(999999);		
		recuperacaoSenha.setCodigo(codigo.toString());
		recuperacaoSenha.setValidade(OffsetDateTime.now().plus(Duration.ofHours(1)));
		recuperacaoSenha.setUtilizado(false);
		
		recuperacaoSenhaRepository.save(recuperacaoSenha);
		
		SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
		passwordResetEmail.setFrom("mateeussouzaa@gmail.com");
		passwordResetEmail.setTo("mateeus.ms@gmail.com");
		passwordResetEmail.setSubject("Fretex | Recuperação de Senha");
		passwordResetEmail.setText("Para recuperar sua senha, informe o seguinte código: " + codigo);
				
		try {
            mailSender.send(passwordResetEmail);
        } catch (MailException e) {
            e.printStackTrace();
            throw new NegocioException("Erro ao enviar email para o destinatário: " + usuario.getEmail());
        }
	}
	
	public void redefinirSenha(RecuperacaoSenha recuperacaoSenha) {
		
		if (!recuperacaoSenha.codigoValido()) {
			throw new NegocioException("Código de recuperação de senha inválido.");
		}
		
		recuperacaoSenha.setUtilizado(true);
		recuperacaoSenhaRepository.save(recuperacaoSenha);
		
	}

}
