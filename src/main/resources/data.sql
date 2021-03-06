/**
  Utilizado pelo o H2 para criar o banco de dados
 */
drop table if exists estudante;
create table if not exists estudante(
  id int primary key auto_increment,
  nome varchar(255),
  email varchar(255),
  matricula varchar(255) unique not null,
  curso varchar(255),
  telefone varchar(20)
);

insert into estudante (nome,email,telefone,matricula,curso)
values ('Xawoy','xawoy@tms.com.br','7777','123456','Engenharia da Computação'),
       ('Furae','furae@tms.com.br','7778','678912','Ciência da Computação'),
       ('Fupuy','fupuy@tms.com.br','7779','321654','Engenharia da Computação'),
       ('Kuer','kuer@tms.com.br','7780','654987','Análise de Dados'),
       ('Blias','blias@tms.com.br','7781','666131','Ciência da Computação');