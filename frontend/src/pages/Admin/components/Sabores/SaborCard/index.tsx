import { Sabor } from 'core/types/Produto';
import './styles.scss';

type Props = {
    sabor: Sabor;
    onRemove: (saborId: number) => void;
}

const SaborCard = ({ sabor, onRemove }: Props) => {
    return (
        <div className="card-base product-card-admin">
            <div className="col-9 py-3">
                <h3 className="card-content product-card-sabor-admin">
                    {sabor.nome}
                </h3>
            </div>
            <div className="product-card-sabor-excluir-admin col-3 pt-3 pr-5">
                <button
                    type="button"
                    className="btn btn-outline-danger btn-block border-radius-10"
                    onClick={() => onRemove(sabor.id)}
                >
                    EXCLUIR
                </button>
            </div>
        </div>
    )
}

export default SaborCard;