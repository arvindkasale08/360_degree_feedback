
import UserProfile from "../users/UserProfile";

const MyFeedbackList = () => {

    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-lg-8 col-xlg-9">
                    <div className="card">
                        <div className="card-body">
                            <h4 className="card-title">My Feedback</h4>
                            <div className="table-responsive">
                                <table className="table user-table">
                                    <thead>
                                        <tr>
                                            <th className="border-top-0">#</th>
                                            <th className="border-top-0">First Name</th>
                                            <th className="border-top-0">Last Name</th>
                                            <th className="border-top-0">Username</th>
                                            <th className="border-top-0">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>Deshmukh</td>
                                            <td>Prohaska</td>
                                            <td>@Genelia</td>
                                            <td><a href="">View</a></td>
                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>Deshmukh</td>
                                            <td>Gaylord</td>
                                            <td>@Ritesh</td>
                                            <td><a href="">View</a></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <UserProfile />
            </div>
        </div>
    )
}

export default MyFeedbackList;