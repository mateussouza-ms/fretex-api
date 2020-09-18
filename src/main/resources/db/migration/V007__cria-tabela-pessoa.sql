CREATE TABLE pessoa
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
   	nome varchar(120) not null,
	nome_fantasia varchar(120),
	cnp varchar(14) not null,
	tipo varchar(10)not null,
	data_nascimento date not null,
	endereco_id bigint not null,
	email varchar(50) not null,
	telefone_id bigint not null,
	
    PRIMARY KEY (id),
    
    CONSTRAINT fk_pessoa_endereco FOREIGN KEY (endereco_id)
        REFERENCES endereco (id) MATCH SIMPLE
        ON DELETE CASCADE,
        
    CONSTRAINT fk_pessoa_telefone FOREIGN KEY (telefone_id)
        REFERENCES telefone (id) MATCH SIMPLE
        ON DELETE CASCADE,
    
    CONSTRAINT cnp_unico UNIQUE (cnp)
);