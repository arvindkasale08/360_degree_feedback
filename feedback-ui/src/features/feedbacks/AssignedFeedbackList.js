
import RightNav from "../users/RightNav";
import { useSelector, useDispatch } from "react-redux";
import { getCurrentUser } from "../users/usersSlice";
import { selectAssignedFeedbacks } from "../feedbacks/feedbackSlice";
import FeedbackExcerpt from "./AssignedFeedbackExcerpt";

const AssignedFeedbackList = () => {

    // get the current user from state
    const currentUser = useSelector(getCurrentUser);
    const assignedFeedbacks = useSelector(selectAssignedFeedbacks);
    console.log(assignedFeedbacks);
    let content;
    if (currentUser === null || currentUser === undefined) {
        content = <div className="no-data">
                <p>No Data Found. Select Different User.</p>
            </div>
        
    } else {
        const innerContent = assignedFeedbacks.map(feedback => <FeedbackExcerpt key={feedback.id} feedback={feedback} />);
        content = <table className="table user-table">
            <thead>
                <tr>
                    <th className="border-top-0">Id</th>
                    <th className="border-top-0">Requestor</th>
                    <th className="border-top-0">Subject</th>
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
                            <h4 className="card-title">Assigned Feedback</h4>
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

export default AssignedFeedbackList;