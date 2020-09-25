CREATE TABLE parametros_config
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    limite_propostas integer,
    percentual_taxa decimal not null,
    
    PRIMARY KEY (id)
);
  
COMMENT ON COLUMN public.parametros_config.limite_propostas
    IS 'Quantidade limite de propostas que podem ser feitas para cada negociação';