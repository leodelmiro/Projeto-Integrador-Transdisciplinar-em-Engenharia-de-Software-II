import React from 'react';
import './styles.scss';

type Props = {
    text: String
}

// TODO ON CLICK DO BOTÃO
const Button = ({ text } : Props) => (
    <div className="default-button-salvar">
        <button className="btn btn-primary">
            <h5>{text}</h5>
        </button>
    </div>
);

export default Button;