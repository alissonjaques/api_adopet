create table pets(
    id bigint not null auto_increment,
    ativo tinyint,
    abrigo_id bigint not null,
    nome varchar(100) not null,
    descricao varchar(100) not null,
    adotado tinyint,
    idade int,
    imagem varchar(400) not null,

    primary key(id),
    KEY `abrigo_id_idx` (`abrigo_id`),
    CONSTRAINT `abrigo_id` FOREIGN KEY (`abrigo_id`) REFERENCES `abrigos` (`id`)
);