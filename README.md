# Gerenciador
Microserviço utilizado para fazer a gestão de pessoas e departamentos em um banco Postgreesql. 
É necessário criar o modelo de tabelas abaixo antes de usar a aplicação

```sql
--create
CREATE TABLE departamento (
  id SERIAL UNIQUE PRIMARY KEY,
  titulo TEXT NOT NULL
);


-- insert
INSERT INTO departamento (titulo) VALUES ('Financeiro');
INSERT INTO departamento (titulo) VALUES ('Comercial');
INSERT INTO departamento (titulo) VALUES ('Desenvolvimento');

-- create
CREATE TABLE pessoas (
  id SERIAL UNIQUE PRIMARY KEY,
  nome TEXT NOT NULL,
  id_departamento INTEGER NOT NULL,
     CONSTRAINT fk_pessoa
      FOREIGN KEY(id_departamento) 
	  REFERENCES departamento(id)
);

-- insert
INSERT INTO pessoas (nome, id_departamento) VALUES ('Camila', 1);
INSERT INTO pessoas (nome, id_departamento) VALUES ('Pedro', 2);
INSERT INTO pessoas (nome, id_departamento) VALUES ('Fabiano', 3);
INSERT INTO pessoas (nome, id_departamento) VALUES ('Raquel', 3);
INSERT INTO pessoas (nome, id_departamento) VALUES ('Patricia', 3);
INSERT INTO pessoas (nome, id_departamento) VALUES ('Joaquim', 1);


-- create
CREATE TABLE tarefas (
  id SERIAL UNIQUE PRIMARY KEY,
  titulo TEXT NOT NULL,
  descricao TEXT,
  prazo date,
  id_departamento INTEGER NOT NULL,
  duracao INTEGER,
  id_pessoa INTEGER,
  finalizado BOOLEAN,
     CONSTRAINT fk_tarefa_departamento
      FOREIGN KEY(id_departamento) 
	  REFERENCES departamento(id),
	       CONSTRAINT fk_tarefa_pessoa
      FOREIGN KEY(id_pessoa) 
	  REFERENCES pessoas(id)
);

ALTER SEQUENCE tarefas_id_seq RESTART WITH 1000;

INSERT INTO tarefas (titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) VALUES ('Validar NF Janeiro', 'Validar notas recebidas no mês de Janeiro', '2022-02-15', 1, 14, 1, true);
INSERT INTO tarefas (titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) VALUES ('Bug 352', 'Corrigir bug 352 na versão 1.25', '2022-05-10', 3, 25, null, false);
INSERT INTO tarefas (titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) VALUES ('Liberação da versão 1.24', 'Disponibilizar pacote para testes', '2022-02-02', 3, 2, 3, false);
INSERT INTO tarefas (titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) VALUES ('Reunião A', 'Reunião com cliente A para apresentação do produto', '2022-02-05', 2, 5, null, false);
INSERT INTO tarefas (titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) VALUES ('Reunião final', 'Fechamento contrato', '2022-03-28', 2, 6, null, false);
INSERT INTO tarefas (titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) VALUES ('Pagamento 01/2022', 'Realizar pagamento dos fornecedores', '2022-01-31', 1, 6, 1, true);
INSERT INTO tarefas (titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) VALUES ('Bug 401', 'Corrigir bug 401 na versão 1.20', '2022-02-01', 3, 2, 4, true);
INSERT INTO tarefas (titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) VALUES ('Bug 399', 'Corrigir bug 399 na versão 1.20', '2022-01-28', 3, 6, 5, true);
INSERT INTO tarefas (titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) VALUES ('Reunião B', 'Reunião com cliente B para apresentação do produto', '2022-01-31', 2, 5, 2, true);
INSERT INTO tarefas (titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) VALUES ('Validar NF Fevereiro', 'Validar notas recebidas no mês de Fevereiro', '2022-03-15', 1, 14, 6, false);
```
