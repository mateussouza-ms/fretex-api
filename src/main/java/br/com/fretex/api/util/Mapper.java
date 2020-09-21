package br.com.fretex.api.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

	@Autowired
	private ModelMapper modelMapper;

	public <D> D toModel(Object objetoEntidade, Class<D> classeModelo) {
		return modelMapper.map(objetoEntidade, classeModelo);
	}

	public <D> D toEntity(Object objetoModelo, Class<D> classeEntidade) {
		return modelMapper.map(objetoModelo, classeEntidade);
	}

	public <D> List<D> toCollectionModel(List<?> listaEntidade, Class<D> classeModelo) {

		return listaEntidade.stream()
				.map(entidade -> toModel(entidade, classeModelo))
				.collect(Collectors.toList());
	}
	
	
	public <D> List<D> toCollectionEntity(List<?> listaModelo, Class<D> classeEntidade) {

		return listaModelo.stream()
				.map(modelo -> toEntity(modelo, classeEntidade))
				.collect(Collectors.toList());
	}
}
