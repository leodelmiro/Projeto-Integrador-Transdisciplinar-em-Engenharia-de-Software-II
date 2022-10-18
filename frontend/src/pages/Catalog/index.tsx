import React, { useCallback, useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import ProductCard from './components/ProductCard';
import { makeRequest } from 'core/utils/request';
import { ProdutoResponse } from 'core/types/Produto';
import ProductCardLoader from './components/Loaders/ProductCardLoader';
import Pagination from 'core/components/Pagination';
import ProductFilters, { FilterForm } from 'core/components/ProductFilters';
import './styles.scss';

const Catalog = () => {
    const [productsResponse, setProductsResponse] = useState<ProdutoResponse>();
    const [isLoading, setIsLoading] = useState(false);
    const [activePage, setActivePage] = useState(0);

    const getProducts = useCallback((filter?: FilterForm) => {
        const params = {
            pagina: activePage,
            produtosPorPagina: 12,
            nome: filter?.nome,
            saborId: filter?.saborId,
            min: filter?.precoMin,
            max: filter?.precoMax,
        }

        setIsLoading(true);
        makeRequest({url: '/produtos', params})
            .then(response => setProductsResponse(response.data))
            .finally(() => {
                setIsLoading(false);
            });
    }, [activePage]);

    useEffect( () => {
        getProducts();
    }, [getProducts]);

    return (
        <div className="catalog-container">
            <div className="filter-container">
                <h1 className="catalog-title">
                    Catálogo de produtos
                </h1>
                <ProductFilters onSearch={filter => getProducts(filter)}/>
            </div>
            <div className="catalog-products">
                {isLoading ? <ProductCardLoader/> : (
                    productsResponse?.content.map(product => (
                        <Link to={`/produtos/${product.id}`} key={product.id}>
                            <ProductCard product={product}/>
                        </Link>
                    ))
                )}
            </div>
            {productsResponse && (
                <Pagination 
                    totalPages={productsResponse.totalPages}
                    activePage={activePage}
                    onChange={page => setActivePage(page)}
                />
            )}
        </div>
    );
}

export default Catalog;