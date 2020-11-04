CREATE TABLE recuperacao_senha
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
	usuario_id bigint not null,
	codigo varchar(6) not null,
	validade timestamp not null,
	utilizado boolean not null,
	
    PRIMARY KEY (id),
	
    CONSTRAINT fk_recuperacao_senha_usuario FOREIGN KEY (usuario_id)
        REFERENCES usuario (id) MATCH SIMPLE
);