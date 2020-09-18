CREATE TABLE cliente
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    nome varchar(120) not null,
	nome_fantasia varchar(120),
	cnp varchar(14) not null,
	tipo_pessoa varchar(10) not null,
	data_nascimento date not null,
	email varchar(50) not null,
	endereco_id bigint not null,
	telefone_id bigint not null,
	usuario_id bigint not null,   	
	
    PRIMARY KEY (id),
	
	 CONSTRAINT fk_pessoa_endereco FOREIGN KEY (endereco_id)
        REFERENCES endereco (id) MATCH SIMPLE
        ON DELETE CASCADE,
        
    CONSTRAINT fk_pessoa_telefone FOREIGN KEY (telefone_id)
        REFERENCES telefone (id) MATCH SIMPLE
        ON DELETE CASCADE,
	
    CONSTRAINT fk_cliente_usuario FOREIGN KEY (usuario_id)
        REFERENCES usuario (id) MATCH SIMPLE,
        
    CONSTRAINT cnp_unico UNIQUE (cnp)
);