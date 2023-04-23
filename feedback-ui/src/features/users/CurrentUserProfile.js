import { useSelector } from "react-redux";
import { getCurrentUser } from "../users/usersSlice";

const CurrentUserSelector = () => {

    // get the current user from state
    const currentUser = useSelector(getCurrentUser);

    let content;
    if (currentUser === null) {
        content = <div className="card-body little-profile text-center">
            <div className="pro-img"><img src="../assets/images/users/4.jpg" alt="user" /></div>
            <h3 className="mb-0">No User Selected</h3>
        </div>
    } else {
        const name = currentUser.profile.firstName + " " + currentUser.profile.lastName;
        const designation = currentUser.profile.designation;
        const businessUnit = currentUser.profile.businessUnit;
        const email = currentUser.profile.email;
        const imageUrl = currentUser.profile.imageUrl;
        content = <>
            <div className="pro-img"><img src={imageUrl} alt="user" /></div>
            <h3 className="mb-0">{ name }</h3>
            <p>{ designation }, {businessUnit}</p>
            <a href="" className="mt-2 waves-effect waves-dark btn btn-primary btn-md btn-rounded">View</a>
            <div className="row text-center mt-3">
                <div className="col-lg-4 col-md-4 mt-3">
                    <h3 className="mb-0 font-light">5</h3><small>Direct Reportings</small>
                </div>
                <div className="col-lg-4 col-md-4 mt-3">
                    <h3 className="mb-0 font-light">3</h3><small>Requested Feedbacks</small>
                </div>
                <div className="col-lg-4 col-md-4 mt-3">
                    <h3 className="mb-0 font-light">7</h3><small>Feedbacks Given</small>
                </div>
            </div>
        </>
    }



    return (
        <div className="card">
            <img className="card-img-top" src="../assets/images/background/profile-bg.jpg" alt="Card image cap" />
            <div className="card-body little-profile text-center">
                { content }
            </div>
        </div>
    )
}

export default CurrentUserSelector;