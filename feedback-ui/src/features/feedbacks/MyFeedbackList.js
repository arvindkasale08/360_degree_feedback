
import RightNav from "../users/RightNav";
import { useSelector, useDispatch } from "react-redux";
import { getCurrentUser } from "../users/usersSlice";
import { selectMyFeedbacks } from "../feedbacks/feedbackSlice";
import FeedbackExcerpt from "./MyFeedbackExcerpt";

const MyFeedbackList = () => {

    // get the current user from state
    const currentUser = useSelector(getCurrentUser);
    const myFeedbacks = useSelector(selectMyFeedbacks);
    const dispatch = useDispatch();
    let content;
    if (currentUser === null || currentUser === undefined) {
        content = <div className="no-data">
                <p>No Data Found. Select Different User.</p>
            </div>
        
    } else {
        const { externalId } = currentUser;
        const innerContent = myFeedbacks.map(feedback => <FeedbackExcerpt key={feedback.id} feedback={feedback} />);
        content = <table className="table user-table">
            <thead>
                <tr>
                    <th className="border-top-0">Id</th>
                    <th className="border-top-0">Requestor</th>
                    <th className="border-top-0">Actor</th>
                    <th className="border-top-0">Status</th>
                    <th className="border-top-0">Action</th>
                </tr>
            </thead>
            <tbody>
                { innerContent }
            </tbody>
        </table>
    }
    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-lg-8 col-xlg-9">
                    <div className="card">
                        <div className="card-body">
                            <h4 className="card-title">My Feedback</h4>
                            <div className="table-responsive">
                                { content }
                            </div>
                        </div>
                    </div>
                </div>

                <RightNav />
            </div>
        </div>
    )
}

export default MyFeedbackList;