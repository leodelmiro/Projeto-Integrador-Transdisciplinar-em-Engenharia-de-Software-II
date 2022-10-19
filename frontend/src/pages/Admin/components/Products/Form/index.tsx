import React, { useEffect, useState } from 'react';
import { makePrivateRequest, makeRequest } from 'core/utils/request';
import { useForm, Controller } from 'react-hook-form';
import { toast } from 'react-toastify';
import { useHistory, useParams } from 'react-router-dom';
import BaseForm from '../../BaseForm';
import './styles.scss'
import { Sabor } from 'core/types/Produto';
import ReactSelect from 'react-select';

type FormState = {
    nome: string,
    preco: string,
    descricao: string,
    fotos: string[];
    sabores: Sabor[];       
}

type ParamsType = {
    productId: string
}

type FormEvent = React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>;

const Form = () => {
    const { register, handleSubmit, errors, setValue, control } = useForm<FormState>();
    const history = useHistory();
    const { productId } = useParams<ParamsType>();
    const [isLoadingSabores, setIsLoadingSabores] = useState(false);
    const [sabores, setSabores] = useState<Sabor[]>([]);
    const isEditing = productId !== 'create';
    const formTitle = isEditing ? 'Editar produto' : "cadastrar um produto";

    useEffect(() => {
        if(isEditing) {
            makeRequest({ url: `/products/${productId}` })
                .then(response => {
                    setValue('name', response.data.name);
                    setValue('price', response.data.price);
                    setValue('description', response.data.description);
                    setValue('imgUrl', response.data.imgUrl);
                    setValue('sabores', response.data.sabores)
            })
        }
    }, [productId, isEditing, setValue]);

    useEffect(() => {
        setIsLoadingSabores(true);
        makeRequest({url: '/sabores'})
            .then(response => setSabores(response.data.content))
            .finally(() => setIsLoadingSabores(false))
    }, []);

    const onSubmit = (data: FormState) => {
        makePrivateRequest({
            method: isEditing ? 'PUT' : 'POST', 
            url: isEditing ? `/produtos/${productId}` : '/produtos', 
            data 
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
                                name="name"
                                type="text" 
                                className="form-control input-base"
                                placeholder="Nome do produto"
                                ref={register({
                                    required: "Campo obrigatório",
                                    minLength: {value: 5, message: "O campo deve ter no mínimo 5 caracteres"},
                                    maxLength: {value: 60, message: "O campo deve ter no máximo 60 caracteres"}
                                })}
                                data-testid="name"
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
                                as={<ReactSelect/>}
                                name="sabores"
                                rules={{ required: true }}
                                control={control}
                                isLoading={isLoadingSabores}
                                options={sabores}
                                getOptionLabel={(option: Sabor) => option.nome}
                                getOptionValue={(option: Sabor) => String(option.id)} 
                                placeholder="Categorias"
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

                       <div className="margin-bottom-30">
                        <input
                                name="price"
                                type="number" 
                                className="form-control input-base"
                                placeholder="Preço"
                                ref={register({required: "Campo obrigatório"})}
                                data-testid="price"
                            />

                            {errors.preco && (
                                <div className="invalid-feedback d-block">
                                    {errors.preco.message}
                                </div>
                            )}
                       </div>

                       <div className="margin-bottom-30 ">
                        <input
                                name="imgUrl"
                                type="text" 
                                className="form-control input-base"
                                placeholder="Imagem do produto"
                                ref={register({required: "Campo obrigatório"})}
                                data-testid="imgUrl"
                            />

                            {errors.fotos && (
                                <div className="invalid-feedback d-block">
                                    {errors.fotos[0]?.message}
                                </div>
                            )}
                       </div>
                    
                    </div>
                    <div className="col-6">
                        <textarea
                            name="description"
                            className="form-control input-base"
                            placeholder="Descrição" 
                            cols={30} 
                            rows={10}
                            ref={register({required: "Campo obrigatório"})}
                            data-testid="description"
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