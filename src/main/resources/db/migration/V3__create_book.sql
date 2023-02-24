create table language (
       id integer generated by default as identity,
       description varchar(10) not null,
       primary key (id),
       unique(description)
);

create table level (
       id integer generated by default as identity,
       description varchar(255) not null,
       language_id integer not null,
       primary key (id),
       foreign key (language_id) references language(id)
);

create table book (
       id integer generated by default as identity,
       description varchar(255) not null,
       level_id integer not null,
       content varchar,
       primary key (id),
       foreign key (level_id) references level(id)
);

insert into language (description) values('Inglês');