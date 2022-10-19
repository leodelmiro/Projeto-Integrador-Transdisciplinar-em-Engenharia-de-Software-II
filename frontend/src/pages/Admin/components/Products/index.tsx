import React from 'react';
import { Route, Switch } from 'react-router-dom';
import Form from './Form';
import List from './List';

const Products = () => {
    
    return (
        <div>
            <Switch>
                <Route path="/admin/produtos" exact>
                    <List/>
                </Route>
                <Route path="/admin/produtos/:productId">
                    <Form/>
                </Route>
            </Switch>
        </div>
    );
}

export default Products;