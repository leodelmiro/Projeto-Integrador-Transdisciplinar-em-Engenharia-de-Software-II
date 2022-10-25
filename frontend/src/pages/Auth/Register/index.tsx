import { ReactComponent as ArrowIcon } from 'core/assets/images/arrow.svg';
import Button from 'core/components/Button';
import { makePrivateRequest } from 'core/utils/request';
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
        makePrivateRequest({
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
                toast.info('Produto cadastrado com sucesso!');
                history.push('/admin/produtos')
            })
            .catch(() => {
                toast.error('Erro ao salvar produto!');
            });
    }

    return (
        <div>
            <Link to="/produtos" className="product-details-goback">
                <ArrowIcon className="icon-goback" />
                <h1 className="text-goback">Voltar</h1>
            </Link>
            <div>
                <form onSubmit={handleSubmit(onSubmit)}>
                    <div className="row">
                        <div className="col-6">
                            <div className="margin-bottom-30">
                                <input
                                    name="email"
                                    type="text"
                                    className="form-control input-base"
                                    placeholder="Email do usuário"
                                    ref={register({
                                        required: "Campo obrigatório",
                                        minLength: { value: 5, message: "O campo deve ter no mínimo 5 caracteres" },
                                        maxLength: { value: 60, message: "O campo deve ter no máximo 60 caracteres" }
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
                                    placeholder="Senha do usuário"
                                    ref={register({
                                        required: "Campo obrigatório",
                                        minLength: { value: 5, message: "O campo deve ter no mínimo 5 caracteres" },
                                        maxLength: { value: 60, message: "O campo deve ter no máximo 60 caracteres" }
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
                                    placeholder="Nome do usuário"
                                    ref={register({
                                        required: "Campo obrigatório",
                                        minLength: { value: 5, message: "O campo deve ter no mínimo 5 caracteres" },
                                        maxLength: { value: 60, message: "O campo deve ter no máximo 60 caracteres" }
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
                                    placeholder="CPF do usuário"
                                    ref={register({
                                        required: "Campo obrigatório",
                                        minLength: { value: 5, message: "O campo deve ter no mínimo 5 caracteres" },
                                        maxLength: { value: 60, message: "O campo deve ter no máximo 60 caracteres" }
                                    })}
                                    data-testid="cpf"
                                />

                                {errors.cpf && (
                                    <div className="invalid-feedback d-block">
                                        {errors.cpf.message}
                                    </div>
                                )}
                            </div>
                        </div>
                        <div className="col-6">
                            <div className="margin-bottom-30">
                                <div className="row">
                                    <input
                                        name="cep"
                                        type="text"
                                        className="form-control input-base"
                                        placeholder="CEP do usuário"
                                        ref={register({
                                            required: "Campo obrigatório",
                                            minLength: { value: 5, message: "O campo deve ter no mínimo 5 caracteres" },
                                            maxLength: { value: 60, message: "O campo deve ter no máximo 60 caracteres" }
                                        })}
                                        data-testid="cep"
                                    />

                                    {errors.cep && (
                                        <div className="invalid-feedback d-block">
                                            {errors.cep.message}
                                        </div>
                                    )}
                                    <input
                                        name="uf"
                                        type="text"
                                        className="form-control input-base"
                                        placeholder="UF do usuário"
                                        ref={register({
                                            required: "Campo obrigatório",
                                            minLength: { value: 5, message: "O campo deve ter no mínimo 5 caracteres" },
                                            maxLength: { value: 60, message: "O campo deve ter no máximo 60 caracteres" }
                                        })}
                                        data-testid="uf"
                                    />

                                    {errors.uf && (
                                        <div className="invalid-feedback d-block">
                                            {errors.uf.message}
                                        </div>
                                    )}
                                </div>
                            </div>
                            <div className="margin-bottom-30">
                                <input
                                    name="cidade"
                                    type="text"
                                    className="form-control input-base"
                                    placeholder="Cidade do usuário"
                                    ref={register({
                                        required: "Campo obrigatório",
                                        minLength: { value: 5, message: "O campo deve ter no mínimo 5 caracteres" },
                                        maxLength: { value: 60, message: "O campo deve ter no máximo 60 caracteres" }
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
                                    placeholder="Logradouro do usuário"
                                    ref={register({
                                        required: "Campo obrigatório",
                                        minLength: { value: 5, message: "O campo deve ter no mínimo 5 caracteres" },
                                        maxLength: { value: 60, message: "O campo deve ter no máximo 60 caracteres" }
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
                                <div className="row">
                                    <input
                                        name="bairro"
                                        type="text"
                                        className="form-control input-base"
                                        placeholder="Bairro do usuário"
                                        ref={register({
                                            required: "Campo obrigatório",
                                            minLength: { value: 5, message: "O campo deve ter no mínimo 5 caracteres" },
                                            maxLength: { value: 60, message: "O campo deve ter no máximo 60 caracteres" }
                                        })}
                                        data-testid="bairro"
                                    />

                                    {errors.bairro && (
                                        <div className="invalid-feedback d-block">
                                            {errors.bairro.message}
                                        </div>
                                    )}
                                    <input
                                        name="numero"
                                        type="text"
                                        className="form-control input-base"
                                        placeholder="Número do usuário"
                                        ref={register({
                                            required: "Campo obrigatório",
                                            minLength: { value: 5, message: "O campo deve ter no mínimo 5 caracteres" },
                                            maxLength: { value: 60, message: "O campo deve ter no máximo 60 caracteres" }
                                        })}
                                        data-testid="numero"
                                    />

                                    {errors.numero && (
                                        <div className="invalid-feedback d-block">
                                            {errors.numero.message}
                                        </div>
                                    )}
                                </div>
                            </div>
                            <div className="margin-bottom-30">
                                <div className="row">

                                    <input
                                        name="ddd"
                                        type="text"
                                        className="form-control input-base"
                                        placeholder="DDD do usuário"
                                        ref={register({
                                            required: "Campo obrigatório",
                                            minLength: { value: 5, message: "O campo deve ter no mínimo 5 caracteres" },
                                            maxLength: { value: 60, message: "O campo deve ter no máximo 60 caracteres" }
                                        })}
                                        data-testid="ddd"
                                    />

                                    {errors.ddd && (
                                        <div className="invalid-feedback d-block">
                                            {errors.ddd.message}
                                        </div>
                                    )}
                                    <input
                                        name="numeroTelefone"
                                        type="text"
                                        className="form-control input-base"
                                        placeholder="Número Telefone do usuário"
                                        ref={register({
                                            required: "Campo obrigatório",
                                            minLength: { value: 5, message: "O campo deve ter no mínimo 5 caracteres" },
                                            maxLength: { value: 60, message: "O campo deve ter no máximo 60 caracteres" }
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
                    <div className="button-right">
                        <Button text="CADASTRAR" className="btn btn-primary" />
                    </div>
                </form>
            </div>
        </div>
    );
};

export default Register;