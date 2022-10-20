import Pagination from 'core/components/Pagination';
import { SaborResponse } from 'core/types/Produto';
import { makePrivateRequest, makeRequest } from 'core/utils/request';
import SaborFilter from 'pages/Admin/components/Sabores/SaborFilter';
import { useCallback, useEffect, useState } from 'react';
import { toast } from 'react-toastify';
import CardLoader from '../Loaders/ProductCardLoader';
import NovoSabor from '../NovoSabor';
import SaborCard from '../SaborCard';

export type FilterForm = {
    nome?: string;
}

const Sabores = () => {
    const [sabor, setSabor] = useState<SaborResponse>();
    const [isLoading, setIsLoading] = useState(false);
    const [activePage, setActivePage] = useState(0);

    const getSabores = useCallback((filter?: FilterForm) => {
        const params = {
            pagina: activePage,
            saboresPorPagina: 12,
            nome: filter?.nome
        }

        setIsLoading(true);
        makeRequest({ url: '/sabores/paginado', params })
            .then(response => setSabor(response.data))
            .finally(() => {
                setIsLoading(false);
            });
    }, [activePage]);

    useEffect(() => {
        getSabores();
    }, [getSabores]);

    const onRemove = (productId: number) => {
        const confirm = window.confirm('Deseja realmente excluir este sabor?');

        if (confirm) {
            makePrivateRequest({ url: `/sabores/${productId}`, method: 'DELETE' })
                .then(() => {
                    toast.info('Sabor removido com sucesso!');
                    getSabores();
                })
                .catch(() => {
                    toast.error('Erro ao remover sabor!');
                });
        }
    }

    return (
        <div className="admin-products-list">
            <div className="filter-container">
                <NovoSabor />
                <SaborFilter onSearch={filter => getSabores(filter)} />
            </div>
            <div className="admin-list-container">
                {isLoading ? <CardLoader /> : (
                    sabor?.content.map(sabor => (
                        <SaborCard sabor={sabor} key={sabor.id} onRemove={onRemove} />
                    ))
                )}

                {sabor && (
                    <Pagination
                        totalPages={sabor.totalPages}
                        activePage={activePage}
                        onChange={page => setActivePage(page)}
                    />
                )}
            </div>
        </div>
    );
}

export default Sabores;