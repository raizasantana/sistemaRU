create database bandejao;

use bandejao;

create table departamento(
id_departamento int primary key auto_increment,
nome varchar(100),
sigla varchar(5));

create table curso(
id_curso int primary key auto_increment,
nome varchar(100),
sigla varchar(5),
id_departamento int,
foreign key(id_departamento) references departamento(id_departamento));

create table refeicao(
id_refeicao int primary key auto_increment,
turno varchar(20),
opcao_vegetariana varchar(400));