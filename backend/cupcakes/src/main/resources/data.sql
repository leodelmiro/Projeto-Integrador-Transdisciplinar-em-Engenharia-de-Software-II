INSERT INTO tb_role (autoridade) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role (autoridade) VALUES ('ROLE_VENDEDOR');
INSERT INTO tb_role (autoridade) VALUES ('ROLE_USUARIO');

INSERT INTO tb_sabor (nome, criado_em) VALUES ('CHOCOLATE', NOW());

INSERT INTO tb_produto (nome, quantidade, preco, descricao, criado_em) VALUES ('CUPCAKE DE CHOCOLATE', 1, 5.5, 'CUPCAKE GOSTO DE CHOCOLATE', NOW());

INSERT INTO tb_produto_sabor (produto_id, sabor_id) VALUES (1, 1);

INSERT INTO tb_foto (url, criado_em, produto_id) VALUES ('https://www.receitadecupcake.com.br/wp-content/uploads/2012/05/Cupcake-chocolate.jpg', NOW(), 1);