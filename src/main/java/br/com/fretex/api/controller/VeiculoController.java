package br.com.fretex.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fretex.api.model.VeiculoModel;
import br.com.fretex.api.model.input.VeiculoInput;
import br.com.fretex.api.util.Mapper;
import br.com.fretex.domain.exception.EntidadeNaoEncontradaException;
import br.com.fretex.domain.model.PrestadorServico;
import br.com.fretex.domain.model.Usuario;
import br.com.fretex.domain.model.Veiculo;
import br.com.fretex.domain.repository.PrestadorServicoRepository;
import br.com.fretex.domain.repository.UsuarioRepository;
import br.com.fretex.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios/{usuarioId}/perfil/prestador-servico/veiculos")
public class VeiculoController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PrestadorServicoRepository prestadorServicoRepository;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private Mapper mapper;
	
	@PostMapping
	public VeiculoModel adicionar(@PathVariable Long usuarioId,@Valid @RequestBody VeiculoInput veiculoInput) {
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));
		
		PrestadorServico prestadorServico = prestadorServicoRepository.findByUsuario(usuario)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não possui o perfil de prestador de serviço."));
		
		Veiculo veiculo = mapper.toEntity(veiculoInput, Veiculo.class);		
		
		veiculo = cadastroUsuarioService.adicionarVeiculo(prestadorServico, veiculo);
		
		return mapper.toModel(veiculo, VeiculoModel.class);
	}
	
	

}
