CREATE DATABASE IF NOT EXISTS sisres_db;
USE sisres_db;
GRANT ALL ON sisres_db.* TO 'testuser'@'localhost';

CREATE TABLE Aluno (
 id_aluno INT NOT NULL AUTO_INCREMENT,
 nome VARCHAR(100) NOT NULL,
 cpf VARCHAR(14) NOT NULL,
 telefone VARCHAR(15),
 email VARCHAR(60),
 matricula VARCHAR(15) NOT NULL,
 PRIMARY KEY (id_aluno)
);


CREATE TABLE Equipamento (
 id_equipamento INT NOT NULL AUTO_INCREMENT,
 codigo VARCHAR(15) NOT NULL,
 descricao VARCHAR(120),
 PRIMARY KEY (id_equipamento)
);


CREATE TABLE Professor (
 id_professor INT NOT NULL AUTO_INCREMENT,
 nome VARCHAR(100) NOT NULL,
 cpf VARCHAR(14) NOT NULL,
 telefone VARCHAR(15),
 email VARCHAR(60),
 matricula VARCHAR(15) NOT NULL,
 PRIMARY KEY (id_professor)
);


CREATE TABLE Reserva_Equipamento (
 id_reserva_equipamento INT NOT NULL AUTO_INCREMENT,
 id_professor INT NOT NULL,
 id_equipamento INT NOT NULL,
 hora VARCHAR(5) NOT NULL,
 data VARCHAR(10) NOT NULL,
 PRIMARY KEY (id_reserva_equipamento)
);


CREATE TABLE Sala (
 id_sala INT NOT NULL AUTO_INCREMENT,
 codigo VARCHAR(10) NOT NULL,
 descricao VARCHAR(120),
 capacidade INT,
 PRIMARY KEY (id_sala)
);


CREATE TABLE Reserva_Sala_Aluno (
 id_reserva_sala_aluno INT NOT NULL AUTO_INCREMENT,
 id_aluno INT NOT NULL,
 id_sala INT NOT NULL,
 finalidade VARCHAR(150) NOT NULL,
 hora VARCHAR(5) NOT NULL,
 data VARCHAR(10) NOT NULL,
 cadeiras_reservadas INT NOT NULL,
 PRIMARY KEY (id_reserva_sala_aluno)
);


CREATE TABLE Reserva_Sala_Professor (
 id_reserva_sala_professor INT NOT NULL AUTO_INCREMENT,
 id_professor INT NOT NULL,
 id_sala INT NOT NULL,
 finalidade VARCHAR(150) NOT NULL,
 hora VARCHAR(5) NOT NULL,
 data VARCHAR(10) NOT NULL,
 PRIMARY KEY (id_reserva_sala_professor)
);


ALTER TABLE Reserva_Equipamento ADD CONSTRAINT FK_Reserva_Equipamento_0 FOREIGN KEY (id_professor) REFERENCES Professor (id_professor);
ALTER TABLE Reserva_Equipamento ADD CONSTRAINT FK_Reserva_Equipamento_1 FOREIGN KEY (id_equipamento) REFERENCES Equipamento (id_equipamento);


ALTER TABLE Reserva_Sala_Aluno ADD CONSTRAINT FK_Reserva_Sala_Aluno_0 FOREIGN KEY (id_aluno) REFERENCES Aluno (id_aluno);
ALTER TABLE Reserva_Sala_Aluno ADD CONSTRAINT FK_Reserva_Sala_Aluno_1 FOREIGN KEY (id_sala) REFERENCES Sala (id_sala);


ALTER TABLE Reserva_Sala_Professor ADD CONSTRAINT FK_Reserva_Sala_Professor_0 FOREIGN KEY (id_professor) REFERENCES Professor (id_professor);
ALTER TABLE Reserva_Sala_Professor ADD CONSTRAINT FK_Reserva_Sala_Professor_1 FOREIGN KEY (id_sala) REFERENCES Sala (id_sala);