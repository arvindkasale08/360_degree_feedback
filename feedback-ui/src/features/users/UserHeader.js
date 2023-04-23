
const UserHeader = () => {
    return (
        <ul className="navbar-nav">
            <li className="nav-item dropdown">
                <a className="nav-link dropdown-toggle text-muted waves-effect waves-dark" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="../assets/images/users/1.jpg" alt="user" className="profile-pic me-2" />Markarn Doe
                </a>
                <ul className="dropdown-menu" aria-labelledby="navbarDropdown"></ul>
            </li>
        </ul>
    )
}

export default UserHeader;