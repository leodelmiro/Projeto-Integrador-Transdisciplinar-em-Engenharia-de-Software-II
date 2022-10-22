import React from 'react';
import './styles.scss';

type Props = {
    text: String,
    onClick?: any,
    className: string
}

const Button = ({ text, onClick, className } : Props) => (
    <div className="default-button-salvar">
        <button className={className} onClick={() => onClick()}>
            <h5>{text}</h5>
        </button>
    </div>
);

export default Button;