CREATE TABLE telefone
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
   	ddd varchar(2) not null,
	numero varchar(9) not null,
	tipo varchar(11),
	
    PRIMARY KEY (id)        
);