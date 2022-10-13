import React from 'react';
import { Route, Router, Switch } from 'react-router-dom';
import Navbar from './core/components/Navbar';
import history from './core/utils/history';
import Home from './pages/Home';

const Routes = () => (
    <Router history={history}>
        <Navbar />
        <Switch>
            <Route path="/">
                <Home />
            </Route>
        </Switch>
    </Router>
);

export default Routes;