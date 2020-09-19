CREATE TABLE prestador_servico
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
	usuario_id bigint not null,
	
    PRIMARY KEY (id),
	
    CONSTRAINT fk_prestador_servico_usuario FOREIGN KEY (usuario_id)
        REFERENCES usuario (id) MATCH SIMPLE
);