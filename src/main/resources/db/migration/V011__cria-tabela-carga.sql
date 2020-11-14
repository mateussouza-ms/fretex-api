CREATE TABLE carga
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
	cliente_id bigint not null,
	tipo_carga varchar(50) not null,
	peso decimal not null,
	endereco_retirada_id bigint not null,
	endereco_entrega_id bigint not null,
	observacoes varchar(120),
	data_cadastro timestamp not null,
	data_retirada timestamp,
	data_entrega timestamp,
	data_retirada_pretendida date,
	data_entrega_pretendida date,
	negocia_datas boolean,
	
    PRIMARY KEY (id),
	
	CONSTRAINT fk_carga_cliente FOREIGN KEY (cliente_id)
        REFERENCES cliente (id) MATCH SIMPLE,
	
	CONSTRAINT fk_carga_endereco_retirada FOREIGN KEY (endereco_retirada_id)
        REFERENCES endereco (id) MATCH SIMPLE,
	
	CONSTRAINT fk_carga_endereco_entrega FOREIGN KEY (endereco_entrega_id)
        REFERENCES endereco (id) MATCH SIMPLE
);