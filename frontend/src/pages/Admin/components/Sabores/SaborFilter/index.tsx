import { useState } from 'react';
import { ReactComponent as SearchIcon } from '../../../../../core/assets/images/search-icon.svg';
import './styles.scss';

export type FilterForm = {
    nome?: string;
}

type Props = {
    onSearch: (filter: FilterForm) => void;
}

const SaborFilter = ({ onSearch }: Props) => {
    const [nome, setNome] = useState('');

    const handleChangeName = (nome: string) => {
        setNome(nome);

        onSearch({ nome: nome })
    }

    const clearFilters = () => {
        setNome('');

        onSearch({ nome: '' });
    }

    return (
        <div className="card-base sabor-filters-container">
            <div className="input-search">
                <input
                    value={nome}
                    type="text"
                    className="form-control"
                    placeholder="Pesquisar Sabor"
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

export default SaborFilter;