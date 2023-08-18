create table role (
       id integer,
        name varchar(255),
        primary key (id)
);

create table usuario_role (
       usuario_id bigint not null,
        role_id integer not null,
        foreign key (role_id) references role(id),
        foreign key (usuario_id) references usuario(id)
);

insert into role values (1, 'ROLE_ADMIN');
insert into role values (2, 'ROLE_STUDENT')