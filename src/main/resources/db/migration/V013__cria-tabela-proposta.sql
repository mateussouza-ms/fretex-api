CREATE TABLE proposta
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
	negociacao_carga_id bigint not null,
	valor numeric not null,
	justificativa varchar(120),
	aceita boolean,
	usuario_responsavel_id bigint not null,
	data_criacao timestamp not null,
	
    PRIMARY KEY (id),
	
	CONSTRAINT fk_proposta_negociacao_carga FOREIGN KEY (negociacao_carga_id)
        REFERENCES negociacao_carga (id) MATCH SIMPLE,
	
	CONSTRAINT fk_proposta_usuario FOREIGN KEY (usuario_responsavel_id)
        REFERENCES usuario (id) MATCH SIMPLE	
);