import { Sabor } from 'core/types/Produto';
import { makeRequest } from 'core/utils/request';
import React, { useEffect, useState } from 'react';
import Select from 'react-select';
import { ReactComponent as SearchIcon } from '../../assets/images/search-icon.svg'
import './styles.scss';

export type FilterForm = {
    nome?: string;
    saborId?: number;
    precoMin?: number;
    precoMax?: number;
}

type Props = {
    onSearch: (filter: FilterForm) => void;
}

const ProductFilters = ({onSearch}: Props) => {
    const [isLoadingSabores, setIsLoadingSabores] = useState(false);
    const [sabores, setSabores] = useState<Sabor[]>([]);
    const [nome, setNome] = useState('');
    const [sabor, setSabor] = useState<Sabor>();
    const [precoMin, setPrecoMin] = useState<number>();
    const [precoMax, setPrecoMax] = useState<number>();
    
    useEffect(() => {
        setIsLoadingSabores(true);
        makeRequest({url: '/sabores'})
            .then(response => setSabores(response.data.content))
            .finally(() => setIsLoadingSabores(false))
    }, []);

    const handleChangeName = (nome: string) => {
        setNome(nome);

        onSearch({nome: nome, saborId: sabor?.id, precoMin: precoMin, precoMax: precoMax})
    }

    const handleChangeSabor = (sabor: Sabor) => {
        setSabor(sabor);

        onSearch({nome: nome, saborId: sabor?.id, precoMin: precoMin, precoMax: precoMax})
    }

    const handleChangePrecoMin = (precoMin: number) => {
        setPrecoMin(precoMin);

        onSearch({nome: nome, saborId: sabor?.id, precoMin: precoMin, precoMax: precoMax})
    }

    const handleChangePrecoMax = (precoMax: number) => {
        setPrecoMax(precoMax);

        onSearch({nome: nome, saborId: sabor?.id, precoMin: precoMin, precoMax: precoMax})
    }

    const clearFilters = () => {
        setSabor(undefined);
        setNome('');

        onSearch({nome: '', saborId: undefined, precoMin: undefined, precoMax: undefined});
    }

    return (
        <div className="card-base product-filters-container">
            <div className="input-search">
                <input
                    value={nome}
                    type="text"
                    className="form-control"
                    placeholder="Pesquisar Produto"
                    onChange={event => handleChangeName(event.target.value)}
                />
                <SearchIcon />
            </div>
            <Select
                name="sabores"
                key={`select-${sabor?.id}`}
                value={sabor}
                isLoading={isLoadingSabores}
                options={sabores}
                getOptionLabel={(option: Sabor) => option.nome}
                getOptionValue={(option: Sabor) => String(option.id)}
                placeholder="Sabores"
                className="filter-select-container"
                classNamePrefix="product-sabores-select"
                inputId="sabores"
                onChange={value => handleChangeSabor(value as Sabor)}
                isClearable
            />
            <div className="input-search search-preco-min">
                <input
                    value={precoMin}
                    type="number"
                    className="form-control"
                    placeholder="Valor mínimo"
                    onChange={event => handleChangePrecoMin(event.target.valueAsNumber)}
                />
                <SearchIcon />
            </div>
            <div className="input-search search-preco-max">
                <input
                    value={precoMax}
                    type="number"
                    className="form-control"
                    placeholder="Valor máximo"
                    onChange={event => handleChangePrecoMax(event.target.valueAsNumber)}
                />
                <SearchIcon />
            </div>
            <button 
                className="btn btn-outline-secondary border-radius-10"
                onClick={clearFilters}
            >
                LIMPAR FILTRO
            </button>
        </div>
    )
}

export default ProductFilters;