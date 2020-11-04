package br.com.fretex.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fretex.api.model.PrestadorServicoModel;
import br.com.fretex.api.model.input.PrestadorServicoInput;
import br.com.fretex.api.util.Mapper;
import br.com.fretex.domain.exception.EntidadeNaoEncontradaException;
import br.com.fretex.domain.model.PrestadorServico;
import br.com.fretex.domain.model.Usuario;
import br.com.fretex.domain.model.Veiculo;
import br.com.fretex.domain.repository.PrestadorServicoRepository;
import br.com.fretex.domain.repository.UsuarioRepository;
import br.com.fretex.domain.service.CadastroUsuarioService;

@RestController
public class PrestadorServicoController {	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private PrestadorServicoRepository prestadorServicoRepository;
	
	@Autowired
	private Mapper mapper;
	
	@PostMapping("/usuarios/{usuarioId}/perfil/prestador-servico")
	public PrestadorServicoModel adicionar(@PathVariable Long usuarioId,@Valid @RequestBody PrestadorServicoInput prestadorServicoInput) {
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));
		
		PrestadorServico prestadorServico = mapper.toEntity(prestadorServicoInput, PrestadorServico.class);
		
		prestadorServico.setUsuario(usuario);
		
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		Veiculo veiculo = mapper.toEntity(prestadorServicoInput.getVeiculo(),  Veiculo.class);
		veiculo.setPrestadorServico(prestadorServico);
		veiculos.add(veiculo);
		prestadorServico.setVeiculos(veiculos);
		
		prestadorServico = (PrestadorServico) cadastroUsuarioService.adicionarPerfil(prestadorServico);
		
		return mapper.toModel(prestadorServico, PrestadorServicoModel.class);
	}
	
	@GetMapping("/usuarios/{usuarioId}/perfil/prestador-servico")
	public PrestadorServicoModel buscar(@PathVariable Long usuarioId) {
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado."));
		
		PrestadorServico prestadorServico = prestadorServicoRepository.findByUsuario(usuario)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não possui o perfil de prestador de serviço."));

		return mapper.toModel(prestadorServico, PrestadorServicoModel.class);
	}
	
	@GetMapping("/prestadores-servico")
	public List<PrestadorServicoModel> listar() {
		var prestadores = prestadorServicoRepository.findAll();

		return mapper.toCollectionModel(prestadores, PrestadorServicoModel.class);
	}

}
