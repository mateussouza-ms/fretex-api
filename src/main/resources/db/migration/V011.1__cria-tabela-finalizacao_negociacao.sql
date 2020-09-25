CREATE TABLE finalizacao_negociacao
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
	valor_acordado decimal not null,
	percentual_taxa decimal not null,
	valor_taxa decimal not null,
	valor_total decimal not null,
	
	PRIMARY KEY (id)
);