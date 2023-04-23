import { useSelector } from 'react-redux'
import { useParams, Link } from 'react-router-dom'
import { selectFeedbackById, selectReportingFeedbackById } from './feedbackSlice'
import RightNav from '../users/RightNav'

const ReportingFeedback = () => {

    const { feedbackId } = useParams()
    console.log(feedbackId)

    const feedback = useSelector((state) => selectReportingFeedbackById(state, feedbackId));
    console.log(feedback);

    const { requestor, subject, actor } = feedback;

    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-lg-8 col-xlg-9">
                    <div className="card">
                        <div className="card-body">
                            <h4 className="card-title">My Feedback</h4>
                            <div className="table-responsive">
                                <form className="form-horizontal form-material mx-2">
                                    <div className="form-group">
                                        <label className="col-md-12 mb-0">Feedback ID</label>
                                        <div className="col-md-12">
                                            <span placeholder="Johnathan Doe" className="form-control ps-0 form-control-line">{feedback.id}</span>
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label className="col-md-12 mb-0">Requestor Name</label>
                                        <div className="col-md-12">
                                            <span placeholder="Johnathan Doe" className="form-control ps-0 form-control-line">{requestor.firstName} {requestor.lastName}</span>
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label className="col-md-12 mb-0">Requestor Email</label>
                                        <div className="col-md-12">
                                            <span placeholder="Johnathan Doe" className="form-control ps-0 form-control-line">{requestor.email}</span>
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label className="col-md-12 mb-0">Subject Name</label>
                                        <div className="col-md-12">
                                            <span placeholder="Johnathan Doe" className="form-control ps-0 form-control-line">{subject.firstName} {subject.lastName}</span>
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label className="col-md-12 mb-0">Subject Email</label>
                                        <div className="col-md-12">
                                            <span placeholder="Johnathan Doe" className="form-control ps-0 form-control-line">{subject.email}</span>
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label className="col-md-12 mb-0">Actor Name</label>
                                        <div className="col-md-12">
                                            <span placeholder="Johnathan Doe" className="form-control ps-0 form-control-line">{actor.firstName} {actor.lastName}</span>
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label className="col-md-12 mb-0">Actor Email</label>
                                        <div className="col-md-12">
                                            <span placeholder="Johnathan Doe" className="form-control ps-0 form-control-line">{actor.email}</span>
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label className="col-md-12 mb-0">Feedback</label>
                                        <div className="col-md-12">
                                            <div placeholder="Johnathan Doe" >{feedback.data}</div>
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <div className="col-md-12">
                                            <Link to={`/reportingFeedback`}>Back to Direct Reporting Feedback</Link>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <RightNav />
            </div>
        </div>
    )
}

export default ReportingFeedback;