import React, { useState } from 'react';
import { Link, useHistory, useLocation } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import ButtonIcon from 'core/components/ButtonIcon';
import './styles.scss'
import { makeLogin } from 'core/utils/request';
import { saveSessionData } from 'core/utils/auth';

type FormState = {
    username: string;
    password: string;
}

type LocationState = {
    from: string;
}

const Login = () => {

    const { register, handleSubmit, errors } = useForm<FormState>();
    const [hasError, setHasError] = useState(false);
    const history = useHistory();
    const location = useLocation<LocationState>();

    const { from } = location.state || { from: { pathname: "/produtos" } };

    const onSubmit = (data: FormState) => {
        makeLogin(data)
            .then(response => {
                setHasError(false);
                saveSessionData(response.data)
                history.replace(from)


            })
            .catch(() => {
                setHasError(true);
            })
    }

    return (
        <div className="login-container">
            <div className="auth-card">
                <h1 className="auth-card-title">
                    LOGIN
                </h1>
                {hasError && (
                    <div className="alert alert-danger mt-5">
                        Usuário ou senha inválidos!
                    </div>
                )}
                <form className="login-form" onSubmit={handleSubmit(onSubmit)}>
                    <div className="margin-bottom-30">
                        <input
                            name="username"
                            type="email"
                            className={`form-control input-base ${errors.username ? 'is-invalid' : ''}`}
                            placeholder="Email"
                            ref={register({
                                required: "Campo obrigatório",
                                pattern: {
                                    value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                                    message: "Email inválido"
                                }
                            })}
                        />
                        {errors.username && (
                            <div className="invalid-feedback d-block">
                                {errors.username.message}
                            </div>
                        )}
                    </div>
                    <div className="margin-bottom-30">
                        <input
                            name="password"
                            type="password"
                            className={`form-control input-base ${errors.password ? 'is-invalid' : ''}`}
                            placeholder="Senha"
                            ref={register({ required: "Campo obrigatório" })}
                        />
                        {errors.password && (
                            <div className="invalid-feedback d-block">
                                {errors.password.message}
                            </div>
                        )}
                    </div>
                    <div className="text-center">
                        <span className="not-registered">Não tem cadastro?</span>
                        <Link to="/auth/cadastro" className="login-link-register">
                            CADASTRAR
                        </Link>
                    </div>
                    <div className="login-submit">
                        <ButtonIcon text="logar" />
                    </div>
                </form>
            </div>
        </div>
    )
}

export default Login;