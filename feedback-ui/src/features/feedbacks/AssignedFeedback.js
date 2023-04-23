import { useState } from "react";
import { useSelector, useDispatch } from 'react-redux'
import { useParams, Link } from 'react-router-dom'
import { finalizeFeedback, selectAssignedFeedbackById } from './feedbackSlice'
import RightNav from '../users/RightNav'
import { useNavigate } from "react-router-dom";

const AssignedFeedback = () => {
    const dispatch = useDispatch()
    const navigate = useNavigate()
    const { feedbackId } = useParams()
    console.log(feedbackId)

    const feedback = useSelector((state) => selectAssignedFeedbackById(state, feedbackId));
    console.log(feedback);

    const { requestor, subject, actor } = feedback;

    const [data, setData] = useState('')
    const onDataChanged = e => setData(e.target.value)

    const canSave = [data].every(Boolean);

    const onSaveFeedbackClicked = () => {
        if (canSave) {
            try {
                dispatch(finalizeFeedback({ body: data, id: feedbackId })).unwrap()
                navigate('/assignedFeedback')
            } catch (err) {
                console.error('Failed to finalize the feedback', err)
            } finally {
            }
        }

    }

    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-lg-8 col-xlg-9">
                    <div className="card">
                        <div className="card-body">
                            <h4 className="card-title">Assigned Feedback</h4>
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
                                        <label htmlFor='' className="col-md-12 mb-0">Feedback</label>
                                        <div className="col-md-12">
                                            <textarea id="data" name="data" rows="5" className="form-control ps-0 form-control-line" onChange={onDataChanged}></textarea>
                                        </div>
                                    </div>
                                    <div className="col-sm-12 d-flex">
                                        <button className="btn btn-success mx-auto mx-md-0 text-white" onClick={onSaveFeedbackClicked} disabled={!canSave}>Save Feedback</button>
                                    </div>
                                    <div className="form-group">
                                        <div className="col-md-12">
                                            <Link to={`/myFeedback`}>Back to My Feedback</Link>
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

export default AssignedFeedback;