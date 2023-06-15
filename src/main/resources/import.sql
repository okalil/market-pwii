INSERT INTO PRODUTO (ID, DESCRICAO, VALOR) VALUES (DEFAULT, 'Arroz', 20);
INSERT INTO PRODUTO (ID, DESCRICAO, VALOR) VALUES (DEFAULT, 'Feijão', 10);
INSERT INTO PRODUTO (ID, DESCRICAO, VALOR) VALUES (DEFAULT, 'Macarrão', 15);

INSERT INTO ITEM_VENDA (QTD, PRODUTO_ID, VENDA_ID) VALUES (3, 1, 1);
INSERT INTO ITEM_VENDA (QTD, PRODUTO_ID, VENDA_ID) VALUES (2, 2, 1);
INSERT INTO ITEM_VENDA (QTD, PRODUTO_ID, VENDA_ID) VALUES (5, 1, 2);
INSERT INTO ITEM_VENDA (QTD, PRODUTO_ID, VENDA_ID) VALUES (4, 3, 2);
INSERT INTO ITEM_VENDA (QTD, PRODUTO_ID, VENDA_ID) VALUES (2, 2, 1);
INSERT INTO ITEM_VENDA (QTD, PRODUTO_ID, VENDA_ID) VALUES (1, 1, 2);
INSERT INTO ITEM_VENDA (QTD, PRODUTO_ID, VENDA_ID) VALUES (3, 3, 1);

INSERT INTO ESTADO (NOME) VALUES ('TO');

INSERT INTO CIDADE (NOME, ESTADO_ID) VALUES ('Palmas', 1);

INSERT INTO ENDERECO (bairro, cep, complemento, cidade_id, pessoa_id) VALUES ('Aureny IV', '77060040', 'Rua 20', 1, 1);
INSERT INTO ENDERECO (bairro, cep, complemento, cidade_id, pessoa_id) VALUES ('Aureny III', '77060080', 'Rua 10', 1, 2);

insert into role (nome) values ('ADMIN');
insert into role (nome) values ('USER');

insert into usuario (usuario, senha) values ('joao', '$2a$12$Gmcnb9zxhFjfPC3nM.1M0eYjw8716j/ifqOx0tZTp.Cm/5JWE9NPq');
insert into usuario (usuario, senha) values ('maria', '$2a$12$Gmcnb9zxhFjfPC3nM.1M0eYjw8716j/ifqOx0tZTp.Cm/5JWE9NPq');

insert into usuario_roles (usuarios_id, roles_id) values (1, 2);
insert into usuario_roles (usuarios_id, roles_id) values (2, 2);

INSERT INTO PESSOA_FISICA (ID, NOME, CPF, usuario_id) VALUES (1, 'João', '276.852.120-56', 1);
INSERT INTO PESSOA_JURIDICA (ID, NOME, CNPJ, usuario_id) VALUES (2, 'Maria', '42.618.444/0001-35', 2);

INSERT INTO VENDA (ID, DATA, COMPRADOR_ID) VALUES (1, '2023-10-10', 1);
INSERT INTO VENDA (ID, DATA, COMPRADOR_ID) VALUES (2, '2023-11-11', 2);