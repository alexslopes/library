create table student_level (
       usuario_id bigint not null,
       language_id integer not null,
       foreign key (language_id) references language(id),
       foreign key (usuario_id) references language(id),
       primary key (usuario_id, language_id)
);
