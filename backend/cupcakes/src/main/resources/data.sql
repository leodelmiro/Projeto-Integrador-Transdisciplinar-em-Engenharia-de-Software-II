INSERT INTO tb_role (autoridade) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role (autoridade) VALUES (' ');
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

INSERT INTO tb_sabor (nome, criado_em) VALUES ('Chocolate', NOW());
INSERT INTO tb_sabor (nome, criado_em) VALUES ('Baunilha', NOW());
INSERT INTO tb_sabor (nome, criado_em) VALUES ('Nutella', NOW());

INSERT INTO tb_produto (nome, quantidade, preco, descricao, criado_em) VALUES ('Cupcake de Baunilha', 1, 5.5, 'Cupcake de Baunilha', NOW());
INSERT INTO tb_produto (nome, quantidade, preco, descricao, criado_em) VALUES ('Cupcake de Chocolate', 1, 5.5, 'Cupcake de Chocolate', NOW());
INSERT INTO tb_produto (nome, quantidade, preco, descricao, criado_em) VALUES ('Cupcake de Nutella', 1, 5.5, 'Cupcake de Nutella', NOW());

INSERT INTO tb_produto_sabor (produto_id, sabor_id) VALUES (1, 1);
INSERT INTO tb_produto_sabor (produto_id, sabor_id) VALUES (2, 2);
INSERT INTO tb_produto_sabor (produto_id, sabor_id) VALUES (3, 3);

INSERT INTO tb_foto (url, criado_em, produto_id) VALUES ('https://cdn-icons-png.flaticon.com/512/3173/3173443.png', NOW(), 1);
INSERT INTO tb_foto (url, criado_em, produto_id) VALUES ('https://cdn-icons-png.flaticon.com/512/3173/3173443.png', NOW(), 2);
INSERT INTO tb_foto (url, criado_em, produto_id) VALUES ('https://cdn-icons-png.flaticon.com/512/3173/3173443.png', NOW(), 3);