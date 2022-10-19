import PrivateRoute from 'core/components/Routes/PrivateRoute';
import { Switch } from 'react-router-dom';
import Navbar from './components/Navbar';
import Products from './components/Products';
import './styles.scss';

const Admin = () => (
    <div className="admin-container">
        <Navbar />
        <div className="admin-content">
            <Switch>
                <PrivateRoute path="/admin/produtos" allowedRoutes={['ROLE_ADMIN', 'ROLE_VENDEDOR']}>
                    <Products />
                </PrivateRoute>
                <PrivateRoute path="/admin/users" allowedRoutes={['ROLE_ADMIN', 'ROLE_VENDEDOR']}>
                    <h1>Sabores</h1>
                </PrivateRoute>
            </Switch>
        </div>
    </div>
);

export default Admin;