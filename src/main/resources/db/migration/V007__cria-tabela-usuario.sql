CREATE TABLE usuario
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    nome varchar(120) not null,
	nome_fantasia varchar(120),
	cnp varchar(14) not null,
	tipo_pessoa varchar(10) not null,
	data_nascimento date,
	email varchar(50) not null,
	endereco_id bigint,
	telefone_id bigint not null,
	senha varchar(120) not null,
	situacao varchar(7) not null,  	
	
    PRIMARY KEY (id),
    
    CONSTRAINT fk_cliente_endereco FOREIGN KEY (endereco_id)
        REFERENCES endereco (id) MATCH SIMPLE
        ON DELETE CASCADE,
        
    CONSTRAINT fk_cliente_telefone FOREIGN KEY (telefone_id)
        REFERENCES telefone (id) MATCH SIMPLE
        ON DELETE CASCADE,
        
    CONSTRAINT cnp_unico_usuario UNIQUE (cnp)
    
);