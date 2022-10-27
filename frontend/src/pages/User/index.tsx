import PrivateRoute from 'core/components/Routes/PrivateRoute';
import { Switch } from 'react-router-dom';
import EditUser from './components/EditUser';
import Navbar from './components/Navbar';
import Pedidos from './components/Pedidos/List';
import './styles.scss';

const User = () => (
    <div className="admin-container">
        <Navbar />
        <div className="admin-content">
            <Switch>
                <PrivateRoute path="/perfil/pedidos" allowedRoutes={['ROLE_USUARIO']}>
                    <Pedidos />
                </PrivateRoute>
                <PrivateRoute path="/perfil/dados" allowedRoutes={['ROLE_USUARIO']}>
                    <EditUser/>
                </PrivateRoute>
            </Switch>
        </div>
    </div>
);

export default User;