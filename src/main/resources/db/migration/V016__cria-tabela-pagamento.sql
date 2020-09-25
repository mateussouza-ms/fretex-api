CREATE TABLE pagamento
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
	finalizacao_negociacao_id bigint not null,
	data_pagamento timestamp not null,
	valor_pago numeric not null,
	forma_pagamento varchar(30) not null,
	numero_parcelas int not null,
	status varchar(30) not null,
	
    PRIMARY KEY (id),
	
	CONSTRAINT fk_pagamento_finalizacao_negociacao FOREIGN KEY (finalizacao_negociacao_id)
        REFERENCES finalizacao_negociacao (id) MATCH SIMPLE
);