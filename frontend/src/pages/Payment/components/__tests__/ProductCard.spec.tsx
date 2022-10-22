import React from 'react';
import { render, screen } from '@testing-library/react';
import ProductCard from '../ProductCard';
import { Produto } from 'core/types/Produto';

test('should render ProductCard', () => {

    const product = {
        name: 'computador',
        imgUrl: 'image.jpg',
        price: 40
    } as Produto;

    render(
        <ProductCard product={product}/>
    )
    
    expect(screen.getByText('computador')).toBeInTheDocument();
    expect(screen.getByAltText('computador')).toBeInTheDocument();
    expect(screen.getByText('R$')).toBeInTheDocument();
    expect(screen.getByText('40.00')).toBeInTheDocument();
});