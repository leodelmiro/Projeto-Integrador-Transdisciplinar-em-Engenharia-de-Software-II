import React from 'react';
import { render, screen } from '@testing-library/react';
import Home from '..';
import { Router } from 'react-router-dom';
import history from 'core/utils/history';

test('should render Home', () => {
    render(
        <Router history={history}>
            <Home/>
        </Router>
    )

    const titleElement = screen.getByText('Conheça a melhor loja de cupcakes');
    const subTitleElement = screen.getByText('Conheça os melhores e mais deliciosos cupcakes disponíveis no mercado.');

    expect(titleElement).toBeInTheDocument();
    expect(subTitleElement).toBeInTheDocument();
    expect(screen.getByTestId('main-image')).toBeInTheDocument();
    expect(screen.getByText(/NAVEGUE AGORA PELO SITE/i)).toBeInTheDocument();
});