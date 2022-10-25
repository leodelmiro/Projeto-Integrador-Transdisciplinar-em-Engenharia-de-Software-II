import { Route, Switch } from 'react-router-dom';
import Login from './Login';
import Register from './Register';
import './styles.scss';



const Auth = () => (
    <div className="card-base border-radius-20 auth-container">
        <div className="auth-content">
            <Switch>
                <Route path="/auth/login">
                    <Login />
                </Route>
                <Route path="/auth/cadastro">
                    <Register />
                </Route>
            </Switch>
        </div>
    </div>
);

export default Auth;