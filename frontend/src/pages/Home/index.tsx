import ButtonIcon from 'core/components/ButtonIcon';
import { Link } from 'react-router-dom';
import './styles.scss';

const Home = () => (
    <div className="home-container">
        <div className="home-content card-base border-radius-20">
            <div className="home-text">
                <h1 className="text-title">
                    Conheça a melhor <br/> 
                    loja de cupcakes
                </h1>
                <p className="text-subtitle">
                    Conheça os melhores e mais deliciosos   <br/> 
                    cupcakes disponíveis no mercado.
                </p>
                <Link to="/products" className="startSearchBtn">
                    <ButtonIcon text="inicie agora a sua busca"/>
                </Link>
            </div>
            <div className="col-6">
                <img src="../../core/assets/images/main-image.png" className="main-image" data-testid="main-image" alt="cozinheiro"/>
            </div>
        </div>
    </div>
);

export default Home;