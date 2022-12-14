import ProductPrice from 'core/components/ProductPrice';
import { Produto } from 'core/types/Produto';
import { Link } from 'react-router-dom';
import './styles.scss';

type Props = {
    product: Produto;
    onRemove: (productId: number) => void;
}

const Card = ({ product: produto, onRemove }: Props) => {
    return (
        <div className="card-base product-card-admin">
            <div className="text-center border-right py-3">
                <img
                    src={produto.foto}
                    alt={produto.nome}
                    className="product-card-image-admin"
                />
            </div>
            <div className="col-8 py-3">
                <h3 className="card-content product-card-name-admin">
                    {produto.nome}
                </h3>
                <span className='product-price-admin-container'>
                    <ProductPrice price={produto.preco} />
                </span>
                <div>
                    <p className="mr-2 product-card-description-admin">
                        {produto.descricao}
                    </p>
                </div>
            </div>
            <div className="col-3 pt-3 pr-5">
                <Link
                    to={`/admin/produtos/${produto.id}`}
                    type="button"
                    className="btn btn-outline-secondary btn-block border-radius-10 mb-3"
                >
                    EDITAR
                </Link>
                <button
                    type="button"
                    className="btn btn-outline-danger btn-block border-radius-10"
                    onClick={() => onRemove(produto.id)}
                >
                    EXCLUIR
                </button>
            </div>
        </div>
    )
}

export default Card;