CREATE TABLE parametros_config
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    limite_propostas integer
);
  
COMMENT ON COLUMN public.parametros_config.limite_propostas
    IS 'Quantidade limite de propostas que podem ser feitas para cada negociação';