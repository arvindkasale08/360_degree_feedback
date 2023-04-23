import CurrentUserSelector from "../features/users/CurrentUserSelector"
import UserHeader from "../features/users/UserHeader"
const Header = () => {

    return (
        <header className="topbar" data-navbarbg="skin6">
            <nav className="navbar top-navbar navbar-expand-md navbar-dark">
                <div className="navbar-header" data-logobg="skin6">
                    <a className="navbar-brand ms-4" href="index.html">
                        <b className="logo-icon">
                            <img src="../assets/images/logo-light-icon.png" alt="homepage" className="dark-logo" />

                        </b>
                        <span className="logo-text">
                            <span className="dark-logo">360 FEEDBACK</span>

                        </span>
                    </a>
                    <a className="nav-toggler waves-effect waves-light text-white d-block d-md-none"
                        href=""><i className="ti-menu ti-close"></i></a>
                </div>
                <div className="navbar-collapse collapse" id="navbarSupportedContent" data-navbarbg="skin5">
                    <ul className="navbar-nav d-lg-none d-md-block ">
                        <li className="nav-item">
                            <a className="nav-toggler nav-link waves-effect waves-light text-white "
                                href=""><i className="ti-menu ti-close"></i></a>
                        </li>
                    </ul>
                    <CurrentUserSelector />
                    <UserHeader />
                </div>
            </nav>
        </header>
    )
}

export default Header