INSERT INTO persons
select (select nextval('hibernate_sequence')), 'Maria','Silva', 'maria@gmail.com', '333.333.333-33', '33.333.333-3', '1983-03-03';

INSERT INTO persons
select (select nextval('hibernate_sequence')), 'João','Santos', 'joao@gmail.com', '222.222.222-22', '22.222.222-2', '1982-02-02';