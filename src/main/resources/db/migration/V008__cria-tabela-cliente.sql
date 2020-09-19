CREATE TABLE cliente
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
	usuario_id bigint not null,   	
	
    PRIMARY KEY (id),
	
    CONSTRAINT fk_cliente_usuario FOREIGN KEY (usuario_id)
        REFERENCES usuario (id) MATCH SIMPLE   
);