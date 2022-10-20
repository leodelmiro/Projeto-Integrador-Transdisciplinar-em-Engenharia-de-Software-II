import { makePrivateRequest } from 'core/utils/request';
import { useState } from 'react';
import { toast } from 'react-toastify';
import './styles.scss';

const NovoSabor = () => {
    const [nome, setNome] = useState('');

    const createSabor = (nome: string) => {
        setNome(nome);

        const confirm = window.confirm('Deseja realmente adicionar este sabor?');

        if (confirm) {
            makePrivateRequest({ url: `/sabores`, method: 'POST', data: {"nome": nome} })
                .then(() => {
                    toast.info('Sabor adicionado com sucesso!');
                    window.location.reload();
                })
                .catch(() => {
                    toast.error('Erro ao adicionar sabor!');
                });
        }
    }

    const clearFilters = () => {
        setNome('');
    }

    const handleChangeName = (nome: string) => {
        setNome(nome);
    }

    return (
        <div className="card-base novo-sabor-container">
            <div className="input-novo-sabor">
                <input
                    value={nome}
                    type="text"
                    className="form-control"
                    placeholder="Novo Sabor"
                    onChange={event => handleChangeName(event.target.value)}
                />
            </div>
            <button
                className="btn btn-primary border-radius-10 ml-2"
                onClick={() => createSabor(nome)}
            >
                ADICIONAR
            </button>
            <button
                className="btn btn-outline-secondary border-radius-10 ml-2"
                onClick={clearFilters}
            >
                LIMPAR TEXTO
            </button>
        </div>
    )
}

export default NovoSabor;