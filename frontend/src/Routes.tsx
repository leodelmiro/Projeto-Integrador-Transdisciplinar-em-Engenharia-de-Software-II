import Admin from 'pages/Admin';
import Auth from 'pages/Auth';
import Catalog from 'pages/Catalog';
import ProductDetails from 'pages/Catalog/components/ProductDetails';
import OrderPayment from 'pages/Payment/components/OrderPayment';
import User from 'pages/User';
import { Redirect, Route, Router, Switch } from 'react-router-dom';
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
                <Catalog />
            </Route>
            <Route path="/produtos/:produtoId">
                <ProductDetails />
            </Route>
            <Route path="/pedidos/:pedidoId">
                <OrderPayment />
            </Route>
            <Redirect from="/auth" to="/auth/login" exact />
            <Route path="/auth">
                <Auth />
            </Route>
            <Redirect from="/admin" to="/admin/produtos" exact />
            <Route path="/admin">
                <Admin />
            </Route>
            <Redirect from="/perfil" to="/perfil/pedidos" exact />
            <Route path="/perfil">
                <User />
            </Route>
        </Switch>
    </Router>
);

export default Routes;