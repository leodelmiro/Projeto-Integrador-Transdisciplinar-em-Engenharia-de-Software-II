import { NavLink } from 'react-router-dom';
import './styles.scss';

const Navbar = () => (
    <nav className="admin-nav-container">
        <ul>
            <li>
                <NavLink to="/admin/produtos" className="admin-nav-item">
                    Produtos
                </NavLink>
            </li>
            <li>
                <NavLink to="/admin/categories" className="admin-nav-item">
                    Sabores
                </NavLink>
            </li>
        </ul>
    </nav>
);

export default Navbar;