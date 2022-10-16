import React from 'react';
import ProductPrice from 'core/components/ProductPrice';
import { Produto } from 'core/types/Produto';
import './styles.scss';

type Props = {
    product: Produto;   
}

const ProductCard = ({product}: Props) => (
    <div className="card-base border-radius-10 product-card">
        <img src={product.fotos[0].url} alt={product.nome} className="product-card-image"/>
        <div className="product-info">
            <h6 className="product-name">
                {product.nome}
            </h6>
            <ProductPrice price={product.preco}/>
        </div>
    </div>
);

export default ProductCard;