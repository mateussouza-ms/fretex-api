CREATE TABLE usuario
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
	senha varchar(120) not null,
	situacao varchar(7) not null,   	
	
    PRIMARY KEY (id)
    
);