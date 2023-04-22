import { Outlet } from 'react-router-dom';
import Header from './Header';
import LeftNav from './LeftNav';

const Layout = () => {
    return (
        <>
            <Header />
            <LeftNav />
            <div className="page-wrapper">
                <Outlet />
            </div>
        </>
    )
}

export default Layout