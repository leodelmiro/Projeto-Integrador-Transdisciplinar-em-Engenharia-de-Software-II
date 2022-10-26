export type ProdutoResponse = {
    content: Produto[];
    totalPages: number;
}

export type Produto = {
    id: number;
    nome: string;
    descricao: string;
    preco: number;
    quantidade: number;
    sabores: Sabor[];
    foto: string;
}

export type SaborResponse = {
    content: Sabor[];
    totalPages: number;
}

export type Sabor = {
    id: number;
    nome: string;
}

export type PedidoResponse = {
    content: Pedido[];
    totalPages: number;
}

export type Pedido = {
    id: number;
    status: string;
    valor: number;
    produtos: ProdutoPedido[];
}

export type ProdutoPedido = {
    nome: string;
    quantidade: number;
}
