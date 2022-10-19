import React from 'react';
import './styles.scss';

type Props = {
    text: String
}

// TODO ON CLICK DO BOTÃO E NO CASO DE SER O BOTÃO DE COMPRA VERIFICAR SE O USUÁRIO ESTÁ LOGADO
const Button = ({ text } : Props) => (
    <div className="default-button-salvar">
        <button className="btn btn-primary">
            <h5>{text}</h5>
        </button>
    </div>
);

export default Button;