CREATE TABLE negociacao_carga
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
	carga_id bigint not null,
	veiculo_id bigint not null,
	status varchar(25) not null,
	finalizacao_negociacao_id bigint,
	
    PRIMARY KEY (id),
	
	CONSTRAINT fk_negociacao_carga_carga FOREIGN KEY (carga_id)
        REFERENCES carga (id) MATCH SIMPLE,
	
	CONSTRAINT fk_negociacao_carga_veiculo FOREIGN KEY (veiculo_id)
        REFERENCES veiculo (id) MATCH SIMPLE,
        
    CONSTRAINT fk_negociacao_carga_finalizacao_negociacao FOREIGN KEY (finalizacao_negociacao_id)
        REFERENCES finalizacao_negociacao (id) MATCH SIMPLE
);