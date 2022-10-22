import { ReactComponent as ArrowIcon } from 'core/assets/images/arrow.svg';
import Button from 'core/components/Button';
import ProductPrice from 'core/components/ProductPrice';
import { Produto } from 'core/types/Produto';
import { getAccessTokenDecoded } from 'core/utils/auth';
import history from 'core/utils/history';
import { makePrivateRequest, makeRequest } from 'core/utils/request';
import { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { toast } from 'react-toastify';
import ProductDescriptionLoader from '../Loaders/ProductDescriptionLoader';
import ProductInfoLoader from '../Loaders/ProductInfoLoader';
import './styles.scss';


type ParamsType = {
    produtoId: string;
}

const ProductDetails = () => {
    const { produtoId } = useParams<ParamsType>();
    const [product, setProduct] = useState<Produto>();
    const [isLoading, setIsLoading] = useState(false);

    const createOrder = (usuario_id: number, produto_id: string) => {
        const confirm = window.confirm('Deseja realmente realizar pedido?');

        if (confirm) {
            makePrivateRequest({
                method: 'POST',
                url: '/pedidos',
                data: {
                    "usuario_id": usuario_id,
                    "produtos": [
                        {
                            "id": parseInt(produto_id),
                            "quantidade": 1
                        }
                    ]
                }
            }).then((result) => {
                toast.info('Pedido realizado com sucesso!');
                history.push('/pedidos/'+ result.data.id)
            })
                .catch(() => {
                    toast.error('Erro ao realizar pedido!');
                });
        }
    }

    useEffect(() => {
        setIsLoading(true);
        makeRequest({ url: `/produtos/${produtoId}` })
            .then(response => setProduct(response.data))
            .finally(() => setIsLoading(false));
    }, [produtoId]);

    return (
        <div className="product-details-container">
            <div className="card-base border-radius-20 product-details">
                <Link to="/produtos" className="product-details-goback">
                    <ArrowIcon className="icon-goback" />
                    <h1 className="text-goback">Voltar</h1>
                </Link>
                <div className="product-details-info">
                    <div className="product-container-info">
                        {isLoading ? <ProductInfoLoader /> : (
                            <>
                                <div className="product-details-card text-center">
                                    <img src={product?.fotos[0]} alt={product?.nome} className="product-details-image" />
                                </div>
                                <div className="product-info-fields">
                                    <h1 className="product-details-name">
                                        {product?.nome}
                                    </h1>
                                    {product?.preco && <ProductPrice price={product?.preco} />}
                                    <span className='product-sabor-nome-container'><p className='product-sabor-nome'>{product?.sabores[0].nome}</p></span>
                                    <span className='product-quantidade-container'>
                                        <h4 className='product-quantidade-titulo'>Quantidade:</h4>
                                        <p className='product-quantidade-numero'>{product?.quantidade}</p>
                                    </span>
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
                <div className="button-right">
                    <Button text="COMPRAR" className="btn btn-primary" onClick={() => createOrder(getAccessTokenDecoded().usuario_id, produtoId)} />
                </div>
            </div>
        </div>
    );
};

export default ProductDetails;