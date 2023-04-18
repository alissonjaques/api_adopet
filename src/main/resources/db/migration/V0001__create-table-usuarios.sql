create table usuarios(
    id bigint not null auto_increment,
    ativo tinyint,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    telefone varchar(20) not null,
    sobre varchar(10000),
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,

    primary key(id)
);