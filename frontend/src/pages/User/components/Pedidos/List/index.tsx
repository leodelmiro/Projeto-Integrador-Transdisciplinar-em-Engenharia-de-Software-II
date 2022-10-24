import Pagination from 'core/components/Pagination';
import { PedidoResponse } from 'core/types/Produto';
import { getAccessTokenDecoded } from 'core/utils/auth';
import { makePrivateRequest } from 'core/utils/request';
import { useCallback, useEffect, useState } from 'react';
import CardLoader from '../Loaders/ProductCardLoader';
import PedidoCard from '../PedidoCard';
import PedidoFilter from '../PedidoFilter';

export type FilterForm = {
    numero?: string;
}

const Pedidos = () => {
    const [pedidos, setPedidos] = useState<PedidoResponse>();
    const [isLoading, setIsLoading] = useState(false);
    const [activePage, setActivePage] = useState(0);

    const getPedidos = useCallback((filter?: FilterForm) => {
        const params = {
            pagina: activePage,
            pedidosPorPagina: 12,
            numero: parseInt(filter?.numero || "0")
        }

        setIsLoading(true);
        makePrivateRequest({ url: '/pedidos/usuarios/' + getAccessTokenDecoded().usuario_id, params })
            .then(response => setPedidos(response.data))
            .finally(() => {
                setIsLoading(false);
            });
    }, [activePage]);


    useEffect(() => {
        getPedidos();
    }, [getPedidos]);

    return (
        <div className="admin-products-list">
            <div className="filter-container">
                <PedidoFilter onSearch={filter => getPedidos(filter)} />
            </div>
            <div className="admin-list-container">
                {isLoading ? <CardLoader /> : (
                    pedidos?.content.map(pedido => (
                        <PedidoCard pedido={pedido} key={pedido.id} onClick={() => console.log("Ola")} />
                    ))
                )}

                {pedidos && (
                    <Pagination
                        totalPages={pedidos.totalPages}
                        activePage={activePage}
                        onChange={page => setActivePage(page)}
                    />
                )}
            </div>
        </div>
    );
}

export default Pedidos;