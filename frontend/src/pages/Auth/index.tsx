import React from 'react';
import { Route, Switch } from 'react-router-dom';
import Login from './Login';
import './styles.scss';



const Auth = () => (
    <div className="card-base border-radius-20 auth-container">
        <div className="auth-content">
            <Switch>
                <Route path="/auth/login">
                    <Login/>
                </Route>
                <Route path="/auth/register">
                    <h1>Register</h1>
                </Route>
            </Switch>
        </div>
    </div>
);

export default Auth;