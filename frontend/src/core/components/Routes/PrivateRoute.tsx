import { isAllowedByRole, isAuthenticated, Role } from 'core/utils/auth';
import { ReactElement } from 'react';
import { Redirect, Route } from 'react-router-dom';

type Props = {
  children: ReactElement;
  path: string;
  allowedRoutes?: Role[];
}

const PrivateRoute = ({ children, path, allowedRoutes }: Props) => {
  return (
    <Route
      path={path}
      render={({ location }) => {
        if (!isAuthenticated()) {
          return (
            <Redirect
              to={{
                pathname: "/auth/login",
                state: { from: location }
              }}
            />
          )
        } else if (isAuthenticated() && !isAllowedByRole(allowedRoutes)) {
          return (
            <Redirect to={{ pathname: "/produtos" }} />
          )
        }
        return children;
      }}
    />
  );
}

export default PrivateRoute;

