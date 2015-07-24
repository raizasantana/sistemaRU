drop database bandejao;

create database bandejao;

use bandejao;

create table DEPARTAMENTO(
id int primary key auto_increment,
nome varchar(100),
sigla varchar(5));

create table CURSO(
id int primary key auto_increment,
nome varchar(100),
sigla varchar(5),
id_departamento int,
foreign key(id_departamento) references DEPARTAMENTO(id));

create table REFEICAO(
id int primary key auto_increment,
turno varchar(20),
descricao varchar(255),
opcao_vegetariana varchar(255),
tipo varchar(30));

create table CONSUMIDOR(
id int primary key auto_increment,
nome varchar(255),
matricula int not null,
ano_ingresso int,
sexo varchar(20),
titulo varchar(30),
cpf varchar(11),
id_curso int,
id_departamento int,
foreign key(id_curso) references CURSO(id),
foreign key(id_departamento) references DEPARTAMENTO(id));

create table TICKET(
id int primary key auto_increment,
id_consumidor int,
valor float,
id_refeicao int,
pago int,
foreign key(id_consumidor) references CONSUMIDOR(id),
foreign key(id_refeicao) references REFEICAO(id));


insert into DEPARTAMENTO values(1, "Departamento 1", "DP1");
insert into DEPARTAMENTO values(2, "Departamento 2", "DP2");
insert into CURSO values(1, "Ciencia da Computacao", "CComp",1);
insert into REFEICAO values(1, "MANHA","REFEICAO 1", "","DESJEJUM");
insert into REFEICAO values(2, "TARDE","REFEICAO 2", "", "ALMOCO");
insert into REFEICAO values(3, "NOITE","REFEICAO 2", "", "JANTAR");

insert into CONSUMIDOR values(1, "RAIZA", 1234, 2011, "FEMININO", "", "124355534", 1, NULL);
insert into CONSUMIDOR values(2, "MAISA", 345, 2011, "FEMININO", "", "124355333", NULL, 1);

insert into TICKET values(1,1,1,2,1);
insert into TICKET values(2,1,1,3,1);
insert into TICKET values(3,2,6,3,1);