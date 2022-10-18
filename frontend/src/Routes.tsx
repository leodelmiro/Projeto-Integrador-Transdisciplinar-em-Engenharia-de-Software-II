import Catalog from 'pages/Catalog';
import ProductDetails from 'pages/Catalog/components/ProductDetails';
import React from 'react';
import { Route, Router, Switch } from 'react-router-dom';
import Navbar from './core/components/Navbar';
import history from './core/utils/history';
import Home from './pages/Home';

const Routes = () => (
    <Router history={history}>
        <Navbar />
        <Switch>
            <Route path="/" exact>
                <Home />
            </Route>
            <Route path="/produtos" exact>
                <Catalog/>
            </Route>
            <Route path="/produtos/:produtoId">
                <ProductDetails/>
            </Route>
        </Switch>
    </Router>
);

export default Routes;