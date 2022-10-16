import jwtDecode from 'jwt-decode';
import history from './history';
export const CLIENT_ID = process.env.REACT_APP_CLIENT_ID ?? 'cupcake';
export const CLIENT_SECRET = process.env.REACT_APP_CLIENT_SECRET ?? 'cupcake123';

type LoginResponse = {
    access_token: string;
    token_type: string;
    expires_in: number;
    scope: string;
    usuario_nome: string;
}

export type Role = 'ROLE_VENDEDOR' | 'ROLE_ADMIN' | 'ROLE_USUARIO';

type AccessToken = {
    exp: number;
    usuario_nome: string;
    roles: Role[];
}

export const saveSessionData = (loginResponse: LoginResponse) => {
    localStorage.setItem('authData', JSON.stringify(loginResponse));
}

export const getSessionData = () => {
    const sessionData = localStorage.getItem('authData') ?? '{}';
    const parsedSessionData =JSON.parse(sessionData);

    return parsedSessionData as LoginResponse;
}

export const getAccessTokenDecoded = () => {
    const sessionData = getSessionData();

    try {
        const tokenDecoded = jwtDecode(sessionData.access_token);
        return tokenDecoded as AccessToken;
    } catch(error) {
        return {} as AccessToken;
    }
}

export const isTokenValid = () => {
    const { exp } = getAccessTokenDecoded();

    return Date.now() <= exp * 1000;
}

export const isAuthenticated = () => {
    const sessionData = getSessionData();

    return sessionData.access_token && isTokenValid();
}

export const isAllowedByRole = (routeRoles: Role[] = []) => {
    if (routeRoles.length === 0) {
        return true
    }

    const { roles } = getAccessTokenDecoded();
    
    return routeRoles.some(role => roles?.includes(role));
}

export const logout = () => {
    localStorage.removeItem('authData');
    history.replace('/auth/login')
}