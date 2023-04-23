
import { useSelector } from 'react-redux'
import { getCurrentUser } from './usersSlice';

const UserHeader = () => {

    // get the current user from state
    const currentUser = useSelector(getCurrentUser);
    let content;
    if (currentUser == null) {
        content = <a className="nav-link dropdown-toggle text-muted waves-effect waves-dark" href="">Select a User</a>
    } else {
        const name = currentUser.profile.firstName + " " + currentUser.profile.lastName;
        const imageUrl = currentUser.profile.imageUrl;
        content = <a className="nav-link dropdown-toggle text-muted waves-effect waves-dark" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            <img src={imageUrl} alt="user" className="profile-pic me-2" />{name}
        </a>;
    }

    return (
        <ul className="navbar-nav">
            <li className="nav-item dropdown">
                {content}
                <ul className="dropdown-menu" aria-labelledby="navbarDropdown"></ul>
            </li>
        </ul>
    )
}

export default UserHeader;