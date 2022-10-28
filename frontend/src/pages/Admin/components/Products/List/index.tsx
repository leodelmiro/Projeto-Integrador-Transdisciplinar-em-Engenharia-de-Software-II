import Pagination from 'core/components/Pagination';
import ProductFilters, { FilterForm } from 'core/components/ProductFilters';
import { ProdutoResponse } from 'core/types/Produto';
import { makePrivateRequest, makeRequest } from 'core/utils/request';
import { useCallback, useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { toast } from 'react-toastify';
import Card from '../Card';
import CardLoader from '../Loaders/ProductCardLoader';

const List = () => {
    const [productsResponse, setProductsResponse] = useState<ProdutoResponse>();
    const [isLoading, setIsLoading] = useState(false);
    const [activePage, setActivePage] = useState(0);
    const history = useHistory();


    const getProducts = useCallback((filter?: FilterForm) => {
        const params = {
            pagina: activePage,
            produtosPorPagina: 4,
            direction: 'DESC',
            orderBy: 'id',
            nome: filter?.nome,
            saborId: filter?.saborId,
            min: filter?.precoMin,
            max: filter?.precoMax,
        }

        setIsLoading(true);
        makeRequest({ url: '/produtos', params })
            .then(response => setProductsResponse(response.data))
            .finally(() => {
                setIsLoading(false);
            });
    }, [activePage]);

    useEffect(() => {
        getProducts();
    }, [getProducts]);

    const handleCreate = () => {
        history.push('/admin/produtos/cria')
    }


    const onRemove = (productId: number) => {
        const confirm = window.confirm('Deseja realmente excluir este produto?');

        if (confirm) {
            makePrivateRequest({ url: `/produtos/${productId}`, method: 'DELETE' })
                .then(() => {
                    toast.info('Produto removido com sucesso!');
                    getProducts();
                })
                .catch(() => {
                    toast.error('Erro ao remover produto!');
                });
        }
    }

    return (
        <div className="admin-products-list">
            <div className="filter-container">
                <button className="btn btn-primary btn-lg" onClick={handleCreate}>
                    ADICIONAR
                </button>
                <ProductFilters onSearch={filter => getProducts(filter)} />
            </div>
            <div className="admin-list-container">
                {isLoading ? <CardLoader /> : (
                    productsResponse?.content.map(product => (
                        <Card product={product} key={product.id} onRemove={onRemove} />
                    ))
                )}


                {productsResponse && (
                    <Pagination
                        totalPages={productsResponse.totalPages}
                        activePage={activePage}
                        onChange={page => setActivePage(page)}
                    />
                )}
            </div>
        </div>
    );
}

export default List;