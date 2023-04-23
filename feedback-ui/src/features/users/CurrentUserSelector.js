import { useSelector } from 'react-redux'
import { selectAllUsers } from './usersSlice'

const CurrentUserSelector = () => {
    const users = useSelector(selectAllUsers)

    const userOptions = users.map(user => (
        <option key={user.id} value={user.id}>
            {user.username}
        </option>
    ))

    const onUserChange = async (e) => {
        console.log(e.target.value);
    }

    return (
        <ul className="navbar-nav me-auto mt-md-0 ">
            <li className="nav-item search-box">
                <span className="nav-link text-muted"><i className="ti-search"></i>
                    <select onChange={onUserChange}>
                        <option>Select User</option>
                        {userOptions}
                    </select>
                </span>
            </li>
        </ul>
    )
}

export default CurrentUserSelector