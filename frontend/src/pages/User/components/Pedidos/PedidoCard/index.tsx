import { formatPrice } from 'core/components/ProductPrice';
import { Pedido } from 'core/types/Produto';
import { Link } from 'react-router-dom';
import './styles.scss';

type Props = {
    pedido: Pedido;
    onClick?: any;
}

const PedidoCard = ({ pedido, onClick }: Props) => {
    const cancelado = () => {
        if (pedido.status === "CANCELADO") {
            return <button
                type="button"
                className="btn btn-outline-danger btn-block border-radius-10"
                disabled
            >
                CANCELADO
            </button>
        }
    }

    const pago = () => {
        if (pedido.status === "PAGO") {
            return <button
                type="button"
                className="btn btn-outline-success btn-block border-radius-10"
                disabled
            >
                CONFIRMADO
            </button>
        }
    }

    const aguardandoPagamento = () => {
        if (pedido.status === "AGUARDANDO_PAGAMENTO") {
            return <Link to={"/pedidos/" + pedido.id}>
                <button
                    type="button"
                    className="btn btn-outline-warning btn-block border-radius-10"
                    onClick={() => onClick()}
                >
                    PAGAR
                </button>
            </Link>
        }
    }

    return (
        <div className="card-base product-card-admin">
            <div className="col-9 py-3">
                <h3 className="card-content pedido-card-admin">
                    Pedido#{pedido.id} - {pedido.produtos[0].nome}, {pedido.produtos[0].quantidade}
                </h3>
            </div>
            <span className="d-flex align-items-center product-price-pedidos-container">
                <div className="product-price-container">
                    <span className="pedido-product-currency">R$</span>
                    <h3 className="pedido-product-price">
                        {formatPrice(pedido.valor)}
                    </h3>
                </div>
            </span>
            <div className="pedido-card col-2 pr-7">
                {pago()}
                {cancelado()}
                {aguardandoPagamento()}
            </div>
        </div>
    )
}

export default PedidoCard;