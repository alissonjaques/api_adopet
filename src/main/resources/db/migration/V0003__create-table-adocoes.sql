create table adocoes(
    id bigint not null auto_increment,
    ativo tinyint,
    pet_id bigint not null,
    tutor_id bigint not null,
    data datetime not null,

    primary key(id),
    KEY `pet_id_idx` (`pet_id`),
    CONSTRAINT `pet_id` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`id`),
    KEY `tutor_id_idx` (`tutor_id`),
    CONSTRAINT `tutor_id` FOREIGN KEY (`tutor_id`) REFERENCES `usuarios` (`id`)
);