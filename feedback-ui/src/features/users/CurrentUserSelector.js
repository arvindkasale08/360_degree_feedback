import { useSelector, useDispatch } from 'react-redux'
import { selectAllUsers, addCurrentUser } from './usersSlice'

const CurrentUserSelector = () => {
    const users = useSelector(selectAllUsers)
    const dispatch = useDispatch();

    const userOptions = users.map(user => (
        <option key={user.id} value={user.id}>
            {user.username}
        </option>
    ))

    const onUserChange = (e) => {
        const id = e.target.value;
        const user = users.find(user => user.id === id);
        dispatch(addCurrentUser(user));
    }

    return (
        <ul className="navbar-nav me-auto mt-md-0 ">
            <li className="nav-item search-box">
                <span className="nav-link text-muted"><i className="ti-search"></i>
                    <select className="header-select" onChange={onUserChange}>
                        <option>Select User</option>
                        {userOptions}
                    </select>
                </span>
            </li>
        </ul>
    )
}

export default CurrentUserSelector