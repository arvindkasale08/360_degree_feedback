import RightNav from "../users/RightNav";
import { useNavigate, Link } from "react-router-dom";
import { useDispatch, useSelector } from 'react-redux'
import { useState } from "react";
import { searchSubjectUsers, searchActorUsers, getCurrentUser, clearSubjectSearch, clearActorSearch } from "../users/usersSlice";
import { initializeFeedback } from "./feedbackSlice";
import SearchSubjectDD from "../users/SearchSubjectDD";
import SearchActorDD from "../users/SearchActorDD";

const RequestFeedback = () => {

    const navigate = useNavigate()
    const dispatch = useDispatch()
    // get the current user from state
    const currentUser = useSelector(getCurrentUser);

    const [subject, setSubject] = useState('')
    const [subjectId, setSubjectId] = useState('')
    const [actor, setActor] = useState('')
    const [actorId, setActorId] = useState('')

    const onSubjectChanged = e => {
        const searchText = e.target.value;
        setSubject(searchText)
        try {
            dispatch(searchSubjectUsers({ searchText })).unwrap()
        } catch (err) {
            console.error('Failed to search for users', err)
        } finally {
        }

    }

    const onActorChanged = e => {
        const searchText = e.target.value;
        setActor(searchText)
        try {
            dispatch(searchActorUsers({ searchText })).unwrap()
        } catch (err) {
            console.error('Failed to search for users', err)
        } finally {
        }

    }

    const canSave = [subjectId, actorId].every(Boolean)

    const onSaveFeedbackClicked = () => {
        if (canSave) {
            try {
                dispatch(initializeFeedback({ actorId, subjectId, requestorId: currentUser.externalId })).unwrap()
                navigate('/')
            } catch (err) {
                console.error('Failed to finalize the feedback', err)
            } finally {
            }
        }

    }

    const selectLiSubject = (e) => {
        const name = e.target.getAttribute('data-name')
        const id = e.target.getAttribute('data-id')
        const externalId = e.target.getAttribute('data-externalid')
        console.log(name, id, externalId)
        // set the name
        setSubject(name)
        setSubjectId(externalId)
        document.getElementById("subjectSearch").value = name
        dispatch(clearSubjectSearch())
    }

    const selectLiActor = (e) => {
        const name = e.target.getAttribute('data-name')
        const id = e.target.getAttribute('data-id')
        const externalId = e.target.getAttribute('data-externalid')
        console.log(name, id, externalId)
        // set the name
        setActor(name)
        setActorId(externalId)
        document.getElementById("actorSearch").value = name
        dispatch(clearActorSearch())
    }

    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-lg-8 col-xlg-9">
                    <div className="card">
                        <div className="card-body">
                            <h4 className="card-title">Request Feedback</h4>
                            <div className="table-responsive">
                                <form className="form-horizontal form-material mx-2">
                                    <div className="form-group">
                                        <label className="col-md-12 mb-0">Subject</label>
                                        <div className="col-md-12">
                                            <input type="text" id="subjectSearch" placeholder="" className="form-control ps-0 form-control-line" onChange={onSubjectChanged} />
                                            <SearchSubjectDD selectLi={selectLiSubject}/>
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label className="col-md-12 mb-0">Actor</label>
                                        <div className="col-md-12">
                                            <input type="text" id="actorSearch" placeholder="" className="form-control ps-0 form-control-line" onChange={onActorChanged} />
                                            <SearchActorDD selectLi={selectLiActor}/>
                                        </div>
                                    </div>
                                    <div className="col-sm-12 d-flex">
                                        <button className="btn btn-success mx-auto mx-md-0 text-white" onClick={onSaveFeedbackClicked} disabled={!canSave}>Send Feedback Request</button>
                                    </div>
                                    <div className="form-group">
                                        <div className="col-md-12">
                                            <Link to={`/`}>Back to Home</Link>
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


export default RequestFeedback;