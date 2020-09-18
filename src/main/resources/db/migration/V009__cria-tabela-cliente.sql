CREATE TABLE cliente
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    pessoa_cnp bigint not null,
	usuario_id bigint not null,   	
	
    PRIMARY KEY (id),
	
    CONSTRAINT fk_cliente_usuario FOREIGN KEY (usuario_id)
        REFERENCES usuario (id) MATCH SIMPLE,
        
    CONSTRAINT fk_cliente_pessoa FOREIGN KEY (pessoa_cnp)
        REFERENCES pessoa (id) MATCH SIMPLE
);