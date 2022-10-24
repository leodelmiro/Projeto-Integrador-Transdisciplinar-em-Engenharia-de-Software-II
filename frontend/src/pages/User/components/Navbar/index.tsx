import { NavLink } from 'react-router-dom';
import './styles.scss';

const Navbar = () => (
    <nav className="admin-nav-container">
        <ul>
            <li>
                <NavLink to="/perfil/pedidos" className="admin-nav-item">
                    Meus Pedidos
                </NavLink>
            </li>
            <li>
                <NavLink to="/perfil/dados" className="admin-nav-item">
                    Meu Cadastro
                </NavLink>
            </li>
        </ul>
    </nav>
);

export default Navbar;