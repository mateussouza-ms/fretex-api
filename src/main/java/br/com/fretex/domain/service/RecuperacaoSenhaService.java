package br.com.fretex.domain.service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
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
	private EnvioEmailService envioEmailService;

	public void recuperarSenha(Usuario usuario) {
		RecuperacaoSenha recuperacaoSenha = new RecuperacaoSenha();
		recuperacaoSenha.setUsuario(usuario);

		Integer codigo = new Random().nextInt(999999);
		while (codigo < 100000) {
			codigo = new Random().nextInt(999999);
		}

		recuperacaoSenha.setCodigo(codigo.toString());
		recuperacaoSenha.setValidade(OffsetDateTime.now().plus(Duration.ofHours(1)));
		recuperacaoSenha.setUtilizado(false);

		recuperacaoSenhaRepository.save(recuperacaoSenha);

		envioEmailService.enviar(new EnvioEmailService.Mensagem(
				"Recuperação de Senha - Fretex <sistemafretex@gmail.com>", Arrays.asList(usuario.getEmail()),
				"Fretex | Recuperação de Senha", "Para recuperar sua senha, informe o seguinte código: " + codigo));
	}

	public void redefinirSenha(RecuperacaoSenha recuperacaoSenha) {

		if (!recuperacaoSenha.codigoValido()) {
			throw new NegocioException("Código de recuperação de senha inválido.");
		}

		recuperacaoSenha.setUtilizado(true);
		recuperacaoSenhaRepository.save(recuperacaoSenha);

	}

}
