INSERT INTO PRODUTO (ID, DESCRICAO, VALOR) VALUES (DEFAULT, 'Arroz', 20);
INSERT INTO PRODUTO (ID, DESCRICAO, VALOR) VALUES (DEFAULT, 'Feijão', 10);
INSERT INTO PRODUTO (ID, DESCRICAO, VALOR) VALUES (DEFAULT, 'Macarrão', 15);

INSERT INTO PESSOA_FISICA (ID, NOME, CPF) VALUES (1, 'João', '276.852.120-56');
INSERT INTO PESSOA_JURIDICA (ID, NOME, CNPJ) VALUES (2, 'Maria', '42.618.444/0001-35');

INSERT INTO VENDA (ID, DATA, COMPRADOR_ID) VALUES (1, '2023-10-10', 1);
INSERT INTO VENDA (ID, DATA, COMPRADOR_ID) VALUES (2, '2023-11-11', 2);

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