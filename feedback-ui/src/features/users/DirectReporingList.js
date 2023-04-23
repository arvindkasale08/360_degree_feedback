import { useSelector, useDispatch } from "react-redux";
import { getCurrentUser, getDirectReportings, fetchDirectReportings } from "../users/usersSlice";
import ReportingExcerpt from "../users/ReportingExcerpt"; 
import { Link } from "react-router-dom";

const DirectReportingList = () => {
    const dispatch = useDispatch();
    // get the current user from state
    const currentUser = useSelector(getCurrentUser);
    // get the reportings for a user
    const directReporting = useSelector(getDirectReportings);
    let content;
    if (currentUser === null || currentUser === undefined) {
        content = <div className="card">
            <div className="card-body bg-info">
                <h4 className="text-white card-title">Direct Reportings</h4>
                <h6 className="card-subtitle text-white mb-0 op-5">Select a user to see direct reportings </h6>
            </div>
        </div>
    } else {
        const name = currentUser.profile.firstName + " " + currentUser.profile.lastName;
        const { externalId } = currentUser;
        let innerContent;

        if (directReporting == null || directReporting[0] === undefined) {
            innerContent =
                <a href="#" className="d-flex align-items-center">
                    <div className="mail-contnet">
                        <h5 className="mb-0">No Direct Reporting Found</h5> <span className="mail-desc"></span>
                    </div>
                </a>
        } else {
            innerContent = directReporting.map(reporting => <ReportingExcerpt key={reporting.id} reporting={reporting} />)
        }
        content = <div className="card">
            <div className="card-body bg-info">
                <h4 className="text-white card-title">Direct Reportings</h4>
                <h6 className="card-subtitle text-white mb-0 op-5">Employees Reporting to {name} </h6>
            </div>
            <div className="card-body">
                <div className="message-box contact-box">
                    <h2 className="add-ct-btn"><Link to={`/requestFeedback`} title="Request Feedback" type="button" className="btn btn-circle btn-lg btn-success waves-effect waves-dark">+</Link>
                    </h2>
                    <div className="message-widget contact-widget">
                        {innerContent}
                    </div>
                </div>
            </div>
        </div>
    }

    return (
        <>
            {content}
        </>
    )
}

export default DirectReportingList;