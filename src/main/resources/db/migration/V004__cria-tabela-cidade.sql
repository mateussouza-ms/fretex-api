CREATE TABLE cidade
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    nome varchar(50) NOT NULL,
	uf_sigla varchar(2) not null,
	
    PRIMARY KEY (id),
	
    CONSTRAINT fk_cidade_estado FOREIGN KEY (uf_sigla)
        REFERENCES uf (sigla) MATCH SIMPLE
);