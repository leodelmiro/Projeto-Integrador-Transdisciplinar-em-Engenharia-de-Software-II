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
    fotos: string[];
}

export type SaborResponse = {
    content: Sabor[];
    totalPages: number;
}

export type Sabor = {
    id: number;
    nome: string;
}