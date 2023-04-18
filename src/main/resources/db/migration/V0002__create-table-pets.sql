create table pets(
    id bigint not null auto_increment,
    ativo tinyint,
    usuario_id bigint not null,
    nome varchar(100) not null,
    descricao varchar(100) not null,
    adotado tinyint,
    idade int,
    imagem varchar(400) not null,

    primary key(id),
    KEY `usuario_id_idx` (`usuario_id`),
    CONSTRAINT `usuario_id` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
);