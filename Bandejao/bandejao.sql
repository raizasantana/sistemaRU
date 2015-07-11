create database bandejao;

use bandejao;

create table departamento(
id int primary key auto_increment,
nome varchar(100),
sigla varchar(5));

create table curso(
id int primary key auto_increment,
nome varchar(100),
sigla varchar(5),
id_departamento int,
foreign key(id_departamento) references departamento(id));

create table refeicao(
id int primary key auto_increment,
turno varchar(20),
descricao varchar(255),
opcao_vegetariana varchar(255),
tipo varchar(30));

create table consumidor(
id int primary key auto_increment,
nome varchar(255),
matricula int not null,
ano_ingresso int,
sexo varchar(20),
titulo varchar(30),
cpf varchar(11),
id_curso int,
id_departamento int,
foreign key(id_curso) references curso(id),
foreign key(id_departamento) references departamento(id));

create table ticket(
id int primary key auto_increment,
id_consumidor int,
valor float,
id_refeicao int,
pago int,
foreign key(id_consumidor) references consumidor(id),
foreign key(id_refeicao) references refeicao(id));
