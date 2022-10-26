import { Sabor } from 'core/types/Produto';
import { makePrivateRequest, makeRequest } from 'core/utils/request';
import React, { useEffect, useState } from 'react';
import { Controller, useForm } from 'react-hook-form';
import { useHistory, useParams } from 'react-router-dom';
import ReactSelect from 'react-select';
import { toast } from 'react-toastify';
import BaseForm from '../../BaseForm';
import './styles.scss';

type FormState = {
    nome: string,
    preco: string,
    quantidade: string,
    descricao: string,
    foto: string;
    sabores: Sabor[];
}

type ParamsType = {
    productId: string
}

const Form = () => {
    const { register, handleSubmit, errors, setValue, control } = useForm<FormState>();
    const history = useHistory();
    const { productId } = useParams<ParamsType>();
    const [isLoadingSabores, setIsLoadingSabores] = useState(false);
    const [sabores, setSabores] = useState<Sabor[]>([]);
    const isEditing = productId !== 'cria';
    const formTitle = isEditing ? 'Editar produto' : "cadastrar um produto";

    useEffect(() => {
        if (isEditing) {
            makeRequest({ url: `/produtos/${productId}` })
                .then(response => {
                    setValue('nome', response.data.nome);
                    setValue('preco', response.data.preco);
                    setValue('quantidade', response.data.quantidade);
                    setValue('descricao', response.data.descricao);
                    setValue('fotos', response.data.foto);
                    setValue('sabores', response.data.sabores)
                })
        }
    }, [productId, isEditing, setValue]);

    useEffect(() => {
        setIsLoadingSabores(true);
        makeRequest({ url: '/sabores' })
            .then(response => setSabores(response.data))
            .finally(() => setIsLoadingSabores(false))
    }, []);

    const onSubmit = (data: FormState) => {
        makePrivateRequest({
            method: isEditing ? 'PUT' : 'POST',
            url: isEditing ? `/produtos/${productId}` : '/produtos',
            data: {
                nome: data.nome,
                preco: data.preco,
                quantidade: data.quantidade,
                descricao: data.descricao,
                foto: { url: data.foto },
                sabores: data.sabores.map(sabor => sabor.id)
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
        <form onSubmit={handleSubmit(onSubmit)}>
            <BaseForm
                title={formTitle}
            >
                <div className="row">
                    <div className="col-6">
                        <div className="margin-bottom-30">
                            <input
                                name="nome"
                                type="text"
                                className="form-control input-base"
                                placeholder="Nome do produto"
                                ref={register({
                                    required: "Campo obrigatório",
                                    minLength: { value: 5, message: "O campo deve ter no mínimo 5 caracteres" }
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
                            <label htmlFor="sabores" className="d-none">Sabores</label>
                            <Controller
                                as={<ReactSelect />}
                                name="sabores"
                                rules={{ required: true }}
                                control={control}
                                isLoading={isLoadingSabores}
                                options={sabores}
                                getOptionLabel={(option: Sabor) => option.nome}
                                getOptionValue={(option: Sabor) => String(option.id)}
                                placeholder="Sabores"
                                classNamePrefix="sabores-select"
                                inputId="sabores"
                                defaultValue=""
                                isMulti
                            />

                            {errors.sabores && (
                                <div className="invalid-feedback d-block">
                                    Campo obrigatório
                                </div>
                            )}
                        </div>

                        <div className='row'>

                            <div className="margin-bottom-30 mr-3 ml-3 input-dividido">
                                <input
                                    name="preco"
                                    type="number"
                                    className="form-control input-base"
                                    placeholder="Preço"
                                    ref={register({
                                        required: "Campo obrigatório",
                                        min: { value: 0.1, message: "O Valor deve ser positivo" }
                                    })}
                                    data-testid="preco"
                                />

                                {errors.preco && (
                                    <div className="invalid-feedback d-block">
                                        {errors.preco.message}
                                    </div>
                                )}
                            </div>

                            <div className="margin-bottom-30 mr-2 input-dividido">
                                <input
                                    name="quantidade"
                                    type="number"
                                    className="form-control input-base"
                                    placeholder="Quantidade"
                                    ref={register({ 
                                        required: "Campo obrigatório",
                                        min: { value: 1, message: "O Valor deve ser positivo" }
                                    })}
                                    data-testid="quantidade"
                                />

                                {errors.quantidade && (
                                    <div className="invalid-feedback d-block">
                                        {errors.quantidade.message}
                                    </div>
                                )}
                            </div>
                        </div>

                        <div className="margin-bottom-30 ">
                            <input
                                name="foto"
                                type="text"
                                className="form-control input-base"
                                placeholder="Imagem do produto"
                                ref={register({ 
                                    required: "Campo obrigatório",
                                    pattern: {
                                        value: /^(http|https):\/\//,
                                        message: "Campo deve ter padrão de link"
                                    }
                                })}
                                data-testid="foto"
                            />

                            {errors.foto && (
                                <div className="invalid-feedback d-block">
                                    {errors.foto?.message}
                                </div>
                            )}
                        </div>

                    </div>
                    <div className="col-6">
                        <textarea
                            name="descricao"
                            className="form-control input-base pb-3"
                            placeholder="Descrição"
                            cols={30}
                            rows={10}
                            ref={register({ required: "Campo obrigatório" })}
                            data-testid="descricao"
                        />

                        {errors.descricao && (
                            <div className="invalid-feedback d-block">
                                {errors.descricao.message}
                            </div>
                        )}
                    </div>
                </div>
            </BaseForm>
        </form>
    );
}

export default Form;