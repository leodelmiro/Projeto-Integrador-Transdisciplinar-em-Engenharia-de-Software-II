import { ReactComponent as ArrowIcon } from 'core/assets/images/arrow.svg';
import Button from 'core/components/Button';
import history from 'core/utils/history';
import { makePrivateRequest } from 'core/utils/request';
import { useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { toast } from 'react-toastify';
import BoletoImage from '../../../../core/assets/images/boleto.svg';
import CartaoImage from '../../../../core/assets/images/cartao.svg';
import PixImage from '../../../../core/assets/images/pix.svg';
import './styles.scss';


type ParamsType = {
    pedidoId: string;
}

const OrderPayment = () => {
    const { pedidoId } = useParams<ParamsType>();
    const [isLoading, setIsLoading] = useState(false);

    const pagarPix = () => {
        const confirm = window.confirm('Deseja realmente confirmar pagamento com pix?');

        if (confirm) {
            makePrivateRequest({
                method: 'POST',
                url: '/pedidos/' + pedidoId + '/pagar/pix'
            }).then(() => {
                toast.info('Pedido pago com sucesso!');
                history.push('/perfil/pedidos')
            })
                .catch(() => {
                    toast.error('Erro ao finalizar pedido!');
                });
        }
    }

    const pagarBoleto = () => {
        const confirm = window.confirm('Deseja realmente confirmar pagamento com boleto?');

        if (confirm) {
            makePrivateRequest({
                method: 'POST',
                url: '/pedidos/' + pedidoId + '/pagar/boleto'
            }).then(() => {
                toast.info('Pedido pago com sucesso!');
                history.push('/perfil/pedidos')
            })
                .catch(() => {
                    toast.error('Erro ao finalizar pedido!');
                });
        }
    }

    const pagarCartao = () => {
        const confirm = window.confirm('Deseja realmente confirmar pagamento com cartão?');

        if (confirm) {
            makePrivateRequest({
                method: 'POST',
                url: '/pedidos/' + pedidoId + '/pagar/cartao'
            }).then(() => {
                toast.info('Pedido pago com sucesso!');
                history.push('/perfil/pedidos')
            })
                .catch(() => {
                    toast.error('Erro ao finalizar pedido!');
                });
        }
    }

    return (
        <div className="product-details-container">
            <div className="card-base border-radius-20 product-details">
                <Link to="/produtos" className="product-details-goback">
                    <ArrowIcon className="icon-goback" />
                    <h1 className="text-goback">Voltar</h1>
                </Link>
                <div>
                    <div className="card-base product-card-payment align-items-center">
                        <div className="text-center border-right py-3">
                            <img
                                src={PixImage}
                                alt="pix"
                                className="product-card-image-payment"
                            />
                        </div>
                        <div className="col-9 py-3">
                            <h3 className="card-content product-card-name-payment">
                                PIX
                            </h3>
                        </div>
                        <div className="col-3 pt-3">
                            <Button text={"PAGAR"} className={"btn btn-primary"} onClick={() => pagarPix()} />
                        </div>
                    </div>
                </div>
                <div>
                    <div className="card-base product-card-payment align-items-center">
                        <div className="text-center border-right py-3">
                            <img
                                src={BoletoImage}
                                alt="boleto"
                                className="product-card-image-payment"
                            />
                        </div>
                        <div className="col-9 py-3">
                            <h3 className="card-content product-card-name-payment">
                                BOLETO
                            </h3>
                        </div>
                        <div className="col-3 pt-3">
                            <Button text={"PAGAR"} className={"btn btn-primary"} onClick={() => pagarBoleto()} />
                        </div>
                    </div>
                </div>
                <div>
                    <div className="card-base product-card-payment align-items-center">
                        <div className="text-center border-right py-3">
                            <img
                                src={CartaoImage}
                                alt="cartão"
                                className="product-card-image-payment"
                            />
                        </div>
                        <div className="col-9 py-3">
                            <h3 className="card-content product-card-name-payment">
                                CARTÃO
                            </h3>
                        </div>
                        <div className="col-3 pt-3">
                            <Button text={"PAGAR"} className={"btn btn-primary"} onClick={() => pagarCartao()} />
                        </div>
                    </div>
                </div>
                <div className="button-right">
                    <Button text={"CANCELAR"} className={"btn btn-outline-danger"}></Button>
                </div>
            </div>
        </div>
    );
};

export default OrderPayment;