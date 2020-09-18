CREATE TABLE endereco
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
   	cep varchar(8) not null,
	logradouro varchar(120) not null,
	numero varchar(20) not null,
	complemento varchar(120),
	cidade_id bigint not null,
	latitude decimal,
	longitude decimal,
	
    PRIMARY KEY (id),
	
    CONSTRAINT fk_endereco_cidade FOREIGN KEY (cidade_id)
        REFERENCES cidade (id) MATCH SIMPLE        
);