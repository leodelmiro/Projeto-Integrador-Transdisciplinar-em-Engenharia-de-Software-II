import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { ReactComponent as ArrowIcon } from 'core/assets/images/arrow.svg';
import ProductPrice from 'core/components/ProductPrice';
import { Produto } from 'core/types/Produto';
import { makeRequest } from 'core/utils/request';
import ProductDescriptionLoader from '../Loaders/ProductDescriptionLoader';
import ProductInfoLoader from '../Loaders/ProductInfoLoader';
import './styles.scss';

type ParamsType = {
    productId: string;
}

const ProductDetails = () => {
    const { productId } = useParams<ParamsType>();
    const [product, setProduct] = useState<Produto>();
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        setIsLoading(true);
        makeRequest({ url: `/produtos/${productId}` })
            .then(response => setProduct(response.data))
            .finally(() => setIsLoading(false));
    }, [productId]);

    return (
        <div className="product-details-container">
            <div className="card-base border-radius-20 product-details">
                <Link to="/products" className="product-details-goback">
                    <ArrowIcon className="icon-goback" />
                    <h1 className="text-goback">Voltar</h1>
                </Link>
                <div className="product-details-info">
                    <div className="product-container-info">
                        {isLoading ? <ProductInfoLoader /> : (
                            <>
                                <div className="product-details-card text-center">
                                    <img src={product?.fotos[0].url} alt={product?.nome} className="product-details-image" />
                                </div>
                                <div className="product-info-fields">
                                    <h1 className="product-details-name">
                                        {product?.nome}
                                    </h1>
                                    {product?.preco && <ProductPrice price={product?.preco} />}
                                </div>
                            </>
                        )}
                    </div>
                    <div className="product-details-card">
                        {isLoading ? <ProductDescriptionLoader /> : (
                            <>
                                <h1 className="product-description-title">
                                    Descrição do produto
                                </h1>
                                <p className="product-description-text">
                                    {product?.descricao}
                                </p>
                            </>
                        )}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ProductDetails;