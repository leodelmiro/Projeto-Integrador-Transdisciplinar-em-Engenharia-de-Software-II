import PrivateRoute from 'core/components/Routes/PrivateRoute';
import { Switch } from 'react-router-dom';
import Navbar from './components/Navbar';
import Pedidos from './components/Pedidos/List';
import './styles.scss';

const User = () => (
    <div className="admin-container">
        <Navbar />
        <div className="admin-content">
            <Switch>
                <PrivateRoute path="/perfil/pedidos" allowedRoutes={['ROLE_ADMIN', 'ROLE_VENDEDOR', 'ROLE_USUARIO']}>
                    <Pedidos />
                </PrivateRoute>
                <PrivateRoute path="/perfil/dados" allowedRoutes={['ROLE_ADMIN', 'ROLE_VENDEDOR', 'ROLE_USUARIO']}>
                    <h1>Dados</h1>
                </PrivateRoute>
            </Switch>
        </div>
    </div>
);

export default User;