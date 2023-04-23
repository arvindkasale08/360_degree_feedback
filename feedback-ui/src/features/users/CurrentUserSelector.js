import { useSelector, useDispatch } from 'react-redux'
import { selectAllUsers, addCurrentUser, fetchDirectReportings } from './usersSlice'
import { fetchMyFeedbacks, fetchReportingFeedbacks, fetchAssignedFeedbacks } from "../feedbacks/feedbackSlice";
import { useNavigate } from 'react-router-dom';

const CurrentUserSelector = () => {
    const users = useSelector(selectAllUsers)
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const userOptions = users.map(user => (
        <option key={user.id} value={user.id}>
            {user.username}
        </option>
    ))

    const onUserChange = (e) => {
        const id = e.target.value;
        const user = users.find(user => user.id === id);
        dispatch(addCurrentUser(user));
        dispatch(fetchMyFeedbacks(user.externalId));
        dispatch(fetchReportingFeedbacks(user.externalId));
        dispatch(fetchAssignedFeedbacks(user.externalId));
        dispatch(fetchDirectReportings(user.externalId));
        navigate('/')
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