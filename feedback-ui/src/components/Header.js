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
                    <ul className="navbar-nav me-auto mt-md-0 ">

                        <li className="nav-item search-box">
                            <a className="nav-link text-muted" href=""><i className="ti-search"></i></a>
                        </li>
                    </ul>
                    <ul className="navbar-nav">
                        <li className="nav-item dropdown">
                            <a className="nav-link dropdown-toggle text-muted waves-effect waves-dark" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <img src="../assets/images/users/1.jpg" alt="user" className="profile-pic me-2" />Markarn Doe
                            </a>
                            <ul className="dropdown-menu" aria-labelledby="navbarDropdown"></ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
    )
}

export default Header