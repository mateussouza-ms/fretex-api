CREATE TABLE veiculo
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
	prestador_servico_id bigint not null,
	peso_maximo decimal,
	outras_caracteristicas text,
	
    PRIMARY KEY (id),
	
	CONSTRAINT fk_veiculo_prestador_servico FOREIGN KEY (prestador_servico_id)
        REFERENCES prestador_servico (id) MATCH SIMPLE
        ON DELETE CASCADE      
);