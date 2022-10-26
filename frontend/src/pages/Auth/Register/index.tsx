import { ReactComponent as ArrowIcon } from 'core/assets/images/arrow.svg';
import Button from 'core/components/Button';
import { makePrivateRequest, makeRequest } from 'core/utils/request';
import { useForm } from 'react-hook-form';
import { Link, useHistory } from 'react-router-dom';
import { toast } from 'react-toastify';
import './styles.scss';

type FormState = {
    email: string;
    nome: string;
    password: string;
    cpf: string;
    cep: string;
    uf: string;
    cidade: string;
    logradouro: string;
    bairro: string;
    numero: string;
    ddd: string;
    numeroTelefone: string;
}

const Register = () => {
    const { register, handleSubmit, errors, setValue, control } = useForm<FormState>();
    const history = useHistory();

    const onSubmit = (data: FormState) => {
        makeRequest({
            method: 'POST',
            url: '/usuarios',
            data: {
                email: data.email,
                nome: data.nome,
                password: data.password,
                cpf: data.cpf,
                endereco: {
                    logradouro: data.logradouro,
                    numero: data.numero,
                    bairro: data.bairro,
                    cep: data.cep,
                    cidade: data.cidade,
                    estado: data.uf,
                },
                telefone: {
                    ddd: data.ddd,
                    numero: data.numeroTelefone
                },
                roles: [3]
            }
        })
            .then(() => {
                toast.info('Usuário cadastrado com sucesso!');
                history.push('/admin/produtos')
            })
            .catch(() => {
                toast.error('Erro ao cadastrar usuário!');
            });
    }

    return (
        <div className="register-container">
            <Link to="/auth/login" className="register-goback">
                <ArrowIcon className="icon-goback" />
                <h1 className="text-goback">Voltar</h1>
            </Link>
            <div>
                <form onSubmit={handleSubmit(onSubmit)}>
                    <div className="register-content-container">
                        <div className="col-6 register-card">
                            <h1 className="register-session-title">
                                Dados
                            </h1>
                            <div className="margin-bottom-30">
                                <input
                                    name="email"
                                    type="text"
                                    className="form-control input-base"
                                    placeholder="Email"
                                    ref={register({
                                        required: "Campo obrigatório",
                                        pattern: {
                                            value: /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/i,
                                            message: "Email precisa ter formato válido"
                                        }
                                    })}
                                    data-testid="email"
                                />

                                {errors.email && (
                                    <div className="invalid-feedback d-block">
                                        {errors.email.message}
                                    </div>
                                )}

                            </div>

                            <div className="margin-bottom-30">
                                <input
                                    name="password"
                                    type="password"
                                    className="form-control input-base"
                                    placeholder="Senha"
                                    ref={register({
                                        required: "Campo obrigatório",
                                        minLength: { value: 5, message: "O campo deve ter no mínimo 5 caracteres" }
                                    })}
                                    data-testid="password"
                                />

                                {errors.password && (
                                    <div className="invalid-feedback d-block">
                                        {errors.password.message}
                                    </div>
                                )}
                            </div>

                            <div className="margin-bottom-30">
                                <input
                                    name="nome"
                                    type="text"
                                    className="form-control input-base"
                                    placeholder="Nome"
                                    ref={register({
                                        required: "Campo obrigatório",
                                        minLength: { value: 3, message: "O campo deve ter no mínimo 3 caracteres" }
                                    })}
                                    data-testid="nome"
                                />

                                {errors.nome && (
                                    <div className="invalid-feedback d-block">
                                        {errors.nome.message}
                                    </div>
                                )}

                            </div>

                            <div className="margin-bottom-30">
                                <input
                                    name="cpf"
                                    type="text"
                                    className="form-control input-base"
                                    placeholder="CPF"
                                    ref={register({
                                        required: "Campo obrigatório",
                                        pattern: {
                                            value: /([0-9]{2}[\.]?[0-9]{3}[\.]?[0-9]{3}[\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\.]?[0-9]{3}[\.]?[0-9]{3}[-]?[0-9]{2})/i,
                                            message: "CPF precisa ter formato válido"
                                        }
                                    })}
                                    data-testid="cpf"
                                />

                                {errors.cpf && (
                                    <div className="invalid-feedback d-block">
                                        {errors.cpf.message}
                                    </div>
                                )}
                            </div>

                            <div className="margin-bottom-30">
                                <div className="d-flex">

                                    <div className="input-duplo-segundo-input">
                                        <input
                                            name="ddd"
                                            type="text"
                                            className="form-control input-base mr-2"
                                            placeholder="DDD"
                                            ref={register({
                                                required: "Campo obrigatório",
                                                pattern: {
                                                    value: /[1-9]{2}/i,
                                                    message: "DDD precisa ter formato válido"
                                                }
                                            })}
                                            data-testid="ddd"
                                        />

                                        {errors.ddd && (
                                            <div className="invalid-feedback d-block">
                                                {errors.ddd.message}
                                            </div>
                                        )}
                                    </div>
                                    <div className="input-duplo">
                                        <input
                                            name="numeroTelefone"
                                            type="text"
                                            className="form-control input-base"
                                            placeholder="Número Telefone"
                                            ref={register({
                                                required: "Campo obrigatório",
                                                pattern: {
                                                    value: /[0-9]{8}|[0-9]{9}$/i,
                                                    message: "Telefone precisa ter formato válido"
                                                }
                                            })}
                                            data-testid="numeroTelefone"
                                        />

                                        {errors.numeroTelefone && (
                                            <div className="invalid-feedback d-block">
                                                {errors.numeroTelefone.message}
                                            </div>
                                        )}
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="col-6 register-card">
                            <h1 className="register-session-title">
                                Endereço
                            </h1>
                            <div className="margin-bottom-30">

                                <div className="d-flex">

                                    <div className="input-duplo">
                                        <input
                                            name="cep"
                                            type="text"
                                            className="form-control input-base"
                                            placeholder="CEP"
                                            ref={register({
                                                required: "Campo obrigatório",
                                                pattern: {
                                                    value: /^[0-9]{5}-[0-9]{3}$|^[0-9]{8}$/i,
                                                    message: "CEP precisa ter formato válido"
                                                }
                                            })}
                                            data-testid="cep"
                                        />

                                        {errors.cep && (
                                            <div className="invalid-feedback d-block">
                                                {errors.cep.message}
                                            </div>
                                        )}
                                    </div>
                                    <div className="input-duplo-segundo-input">
                                        <input
                                            name="uf"
                                            type="text"
                                            className="form-control input-base ml-3 mr-2"
                                            placeholder="UF"
                                            ref={register({
                                                required: "Campo obrigatório",
                                                pattern: {
                                                    value: /AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO/i,
                                                    message: "UF precisa ser válida"
                                                }
                                            })}
                                            data-testid="uf"
                                        />

                                        {errors.uf && (
                                            <div className="invalid-feedback d-block ml-3">
                                                {errors.uf.message}
                                            </div>
                                        )}
                                    </div>
                                </div>
                            </div>
                            <div className="margin-bottom-30">
                                <input
                                    name="cidade"
                                    type="text"
                                    className="form-control input-base"
                                    placeholder="Cidade"
                                    ref={register({
                                        required: "Campo obrigatório",
                                        minLength: { value: 3, message: "O campo deve ter no mínimo 3 caracteres" }
                                    })}
                                    data-testid="cidade"
                                />

                                {errors.cidade && (
                                    <div className="invalid-feedback d-block">
                                        {errors.cidade.message}
                                    </div>
                                )}
                            </div>
                            <div className="margin-bottom-30">
                                <input
                                    name="logradouro"
                                    type="text"
                                    className="form-control input-base"
                                    placeholder="Rua/Avenida"
                                    ref={register({
                                        required: "Campo obrigatório",
                                        minLength: { value: 3, message: "O campo deve ter no mínimo 3 caracteres" }
                                    })}
                                    data-testid="logradouro"
                                />

                                {errors.logradouro && (
                                    <div className="invalid-feedback d-block">
                                        {errors.logradouro.message}
                                    </div>
                                )}
                            </div>

                            <div className="margin-bottom-30">
                                <div className="d-flex">
                                    <div className="input-duplo">
                                        <input
                                            name="bairro"
                                            type="text"
                                            className="form-control input-base"
                                            placeholder="Bairro"
                                            ref={register({
                                                required: "Campo obrigatório",
                                                minLength: { value: 3, message: "O campo deve ter no mínimo 3 caracteres" }
                                            })}
                                            data-testid="bairro"
                                        />

                                        {errors.bairro && (
                                            <div className="d-flex invalid-feedback d-block">
                                                {errors.bairro.message}
                                            </div>
                                        )}
                                    </div>
                                    <div className="input-duplo-segundo-input">
                                        <input
                                            name="numero"
                                            type="text"
                                            className="form-control input-base ml-3 mr-2"
                                            placeholder="Número"
                                            ref={register({
                                                required: "Campo obrigatório",
                                                minLength: { value: 1, message: "O campo deve ter no mínimo 1 caracteres" }
                                            })}
                                            data-testid="numero"
                                        />

                                        {errors.numero && (
                                            <div className="invalid-feedback d-block ml-3">
                                                {errors.numero.message}
                                            </div>
                                        )}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="button-right mt-4">
                        <Button text="CADASTRAR" className="btn btn-primary" />
                    </div>
                </form>
            </div>
        </div>
    );
};

export default Register;