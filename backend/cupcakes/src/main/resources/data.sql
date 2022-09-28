INSERT INTO tb_role (autoridade) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role (autoridade) VALUES ('ROLE_VENDEDOR');
INSERT INTO tb_role (autoridade) VALUES ('ROLE_USUARIO');

INSERT INTO tb_usuario(nome, cpf, email, password, ddd, numero, logradouro, bairro, cep, cidade, estado, criado_em)
VALUES ('Leonardo Admin', '27284825006', 'leo@email.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', '13', '999999999', 'Rua Teste', 'Bairro', '11740000', 'Itanhaém', 'SP', NOW());

INSERT INTO tb_usuario(nome, cpf, email, password, ddd, numero, logradouro, bairro, cep, cidade, estado, criado_em)
VALUES ('Leonardo Vendedor', '33728711624', 'leo2@email.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', '13', '999999999', 'Rua Teste', 'Bairro', '11740000', 'Itanhaém', 'SP', NOW());

INSERT INTO tb_usuario(nome, cpf, email, password, ddd, numero, logradouro, bairro, cep, cidade, estado, criado_em)
VALUES ('Leonardo Usuario', '48883169450', 'leo3@email.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', '13', '999999999', 'Rua Teste', 'Bairro', '11740000', 'Itanhaém', 'SP', NOW());

INSERT INTO tb_usuarios_role (usuario_id, role_id) VALUES (1, 1);
INSERT INTO tb_usuarios_role (usuario_id, role_id) VALUES (1, 2);
INSERT INTO tb_usuarios_role (usuario_id, role_id) VALUES (1, 3);

INSERT INTO tb_usuarios_role (usuario_id, role_id) VALUES (2, 2);
INSERT INTO tb_usuarios_role (usuario_id, role_id) VALUES (2, 3);

INSERT INTO tb_usuarios_role (usuario_id, role_id) VALUES (3, 3);

INSERT INTO tb_sabor (nome, criado_em) VALUES ('CHOCOLATE', NOW());
INSERT INTO tb_sabor (nome, criado_em) VALUES ('BAUNILHA', NOW());

INSERT INTO tb_produto (nome, quantidade, preco, descricao, criado_em) VALUES ('CUPCAKE DE CHOCOLATE', 1, 5.5, 'CUPCAKE GOSTO DE CHOCOLATE', NOW());
INSERT INTO tb_produto (nome, quantidade, preco, descricao, criado_em) VALUES ('CUPCAKE GOSTO DE BAUNILHA', 1, 5.5, 'CUPCAKE GOSTO DE BAUNILHA', NOW());
INSERT INTO tb_produto (nome, quantidade, preco, descricao, criado_em) VALUES ('CUPCAKE GOSTO DE CHOCOLATE E BAUNILHA', 1, 5.5, 'CUPCAKE GOSTO DE CHOCOLATE E BAUNILHA', NOW());

INSERT INTO tb_produto_sabor (produto_id, sabor_id) VALUES (1, 1);
INSERT INTO tb_produto_sabor (produto_id, sabor_id) VALUES (2, 2);
INSERT INTO tb_produto_sabor (produto_id, sabor_id) VALUES (3, 1);
INSERT INTO tb_produto_sabor (produto_id, sabor_id) VALUES (3, 2);

INSERT INTO tb_foto (url, criado_em, produto_id) VALUES ('https://www.receitadecupcake.com.br/wp-content/uploads/2012/05/Cupcake-chocolate.jpg', NOW(), 1);