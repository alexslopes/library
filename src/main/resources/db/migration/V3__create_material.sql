create table language (
       id integer generated by default as identity,
       description varchar(10) not null,
       primary key (id),
       unique(description)
);

create table book (
       id integer generated by default as identity,
       description varchar(255) not null,
       language_id integer not null,
       primary key (id),
       foreign key (language_id) references language(id)
);

create table material (
       id integer generated by default as identity,
       description varchar(255) not null,
       book_id integer not null,
       file binary not null,
       primary key (id),
       foreign key (book_id) references book(id)
);

insert into language (description) values('Inglês');