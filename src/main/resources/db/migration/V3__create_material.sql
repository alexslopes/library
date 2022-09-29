create table language (
       id integer generated by default as identity,
       description varchar(10) not null,
       identification varchar(10) not null,
       primary key (id),
       unique(identification)
);

create table book (
       id integer generated by default as identity,
       description varchar(255) not null,
       language_id integer not null,
       foreign key (language_id) references language(id)
);

insert into language (description, identification) values('Inglês', 'ingles');