CREATE TABLE Aluno (
 id_aluno INT NOT NULL AUTO_INCREMENT,
 nome VARCHAR(100) NOT NULL,
 cpf VARCHAR(11) NOT NULL,
 telefone VARCHAR(10),
 email VARCHAR(60),
 matricula VARCHAR(15) NOT NULL
);

ALTER TABLE Aluno ADD CONSTRAINT PK_Aluno PRIMARY KEY (id_aluno);


CREATE TABLE Equipamento (
 id_equipamento INT NOT NULL AUTO_INCREMENT,
 codigo VARCHAR(15),
 descricao VARCHAR(120)
);

ALTER TABLE Equipamento ADD CONSTRAINT PK_Equipamento PRIMARY KEY (id_equipamento);


CREATE TABLE Professor (
 id_professor INT NOT NULL AUTO_INCREMENT,
 nome VARCHAR(10) NOT NULL,
 cpf VARCHAR(11) NOT NULL,
 telefone VARCHAR(10),
 email VARCHAR(60),
 matricula VARCHAR(15) NOT NULL
);

ALTER TABLE Professor ADD CONSTRAINT PK_Professor PRIMARY KEY (id_professor);


CREATE TABLE Sala (
 id_sala INT NOT NULL AUTO_INCREMENT,
 codigo VARCHAR(10) NOT NULL,
 descricao VARCHAR(120),
 capacidade INT
);

ALTER TABLE Sala ADD CONSTRAINT PK_Sala PRIMARY KEY (id_sala);


