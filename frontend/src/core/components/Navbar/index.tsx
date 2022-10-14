import React, { useEffect, useState } from 'react';
import { Link, NavLink, useLocation } from 'react-router-dom';
import { getAccessTokenDecoded, logout } from 'core/utils/auth';
import './styles.scss';

import menu from '../../assets/images/menu.svg';

const Navbar = () => {
    const [drawerActive, setDrawerActive] = useState(false);
    const [currentUser, setCurrentUser] = useState('');
    const location = useLocation();

    useEffect(() => {
        const currentUserData = getAccessTokenDecoded();
        setCurrentUser(currentUserData.usuario_nome);
    }, [location]);

    const handleLogout = (event: React.MouseEvent<HTMLAnchorElement, MouseEvent>) => {
        event.preventDefault();
        logout();
    }

    return (
        <nav className="bg-primary main-nav">
            <Link to="/" className="nav-logo-text">
                <h4>CupcakeStore</h4>
            </Link>
            <button className="menu-mobile-btn" type="button" onClick={() => setDrawerActive(!drawerActive)}>
                <img src={menu} alt="Mobile Menu" />
            </button>

            <div className={drawerActive ? "menu-mobile-container" : "menu-container"}>
                <ul className="main-menu">
                    <li>
                        <NavLink to="/" exact className="nav-link" onClick={() => setDrawerActive(false)}>
                            HOME
                        </NavLink>
                    </li>
                    <li>
                        <NavLink to="/produtos" className="nav-link" onClick={() => setDrawerActive(false)}>
                            CATALÓGO
                        </NavLink>
                    </li>
                    <li>
                        <NavLink to="/perfil" className="nav-link" onClick={() => setDrawerActive(false)}>
                            PERFIL
                        </NavLink>
                    </li>
                    <li>
                        <NavLink to="/admin" className="nav-link" onClick={() => setDrawerActive(false)}>
                            ADMIN
                        </NavLink>
                    </li>
                    {
                        drawerActive && (
                            <li>
                                {
                                    currentUser && (
                                        <a href="#logout" className="nav-link active d-inline">
                                            {`LOGOUT - ${currentUser}`}
                                        </a>
                                    )
                                }
                            </li>
                        )
                    }
                    {
                        drawerActive && (
                            <>
                                {
                                !currentUser && (
                                    <li>
                                      <Link to="/auth/login" className="nav-link active">LOGIN</Link>  
                                    </li>
                                )
                                }
                            </>
                        )
                    }
                </ul>
            </div>
            <div className="user-info-dnone text-right">
                {currentUser && (
                    <>
                        {currentUser}
                        <a
                            href="#logout"
                            className="nav-link active d-inline"
                            onClick={(e) => {
                                setDrawerActive(false);
                                handleLogout(e);
                            }}
                        >
                            LOGOUT
                        </a>
                    </>
                )}
                {!currentUser && (
                    <Link to="/auth/login" className="nav-link active" onClick={() => setDrawerActive(false)}>
                        LOGIN
                    </Link>
                )}
            </div>
        </nav>
    )
};

export default Navbar;