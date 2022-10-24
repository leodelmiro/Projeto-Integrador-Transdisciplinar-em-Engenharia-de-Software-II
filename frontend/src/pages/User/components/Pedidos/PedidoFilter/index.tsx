import { useState } from 'react';
import { ReactComponent as SearchIcon } from '../../../../../core/assets/images/search-icon.svg';
import './styles.scss';

export type FilterForm = {
    numero?: string;
}

type Props = {
    onSearch: (filter: FilterForm) => void;
}

const PedidoFilter = ({ onSearch }: Props) => {
    const [numero, setNumero] = useState('');

    const handleChangeName = (numero: string) => {
        setNumero(numero);

        onSearch({ numero: numero })
    }

    const clearFilters = () => {
        setNumero('');

        onSearch({ numero: '' });
    }

    return (
        <div className="card-base sabor-filters-container">
            <div className="input-search">
                <input
                    value={numero}
                    type="text"
                    className="form-control"
                    placeholder="Pesquisar Pedido"
                    onChange={event => handleChangeName(event.target.value)}
                />
                <SearchIcon />
            </div>
            <button
                className="btn btn-outline-secondary border-radius-10 ml-2"
                onClick={clearFilters}
            >
                LIMPAR FILTRO
            </button>
        </div>
    )
}

export default PedidoFilter;