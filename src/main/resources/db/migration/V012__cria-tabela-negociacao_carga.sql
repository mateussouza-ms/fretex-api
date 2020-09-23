CREATE TABLE negociacao_carga
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
	carga_id bigint not null,
	veiculo_id bigint not null,
	status varchar(25) not null,
	
    PRIMARY KEY (id),
	
	CONSTRAINT fk_negciacao_carga_carga FOREIGN KEY (carga_id)
        REFERENCES carga (id) MATCH SIMPLE,
	
	CONSTRAINT fk_negciacao_carga_veiculo FOREIGN KEY (veiculo_id)
        REFERENCES veiculo (id) MATCH SIMPLE	
);