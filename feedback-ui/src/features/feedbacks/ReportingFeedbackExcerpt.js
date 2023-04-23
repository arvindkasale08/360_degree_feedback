import { Link } from 'react-router-dom';

const ReportingFeedbackExcerpt = ({ feedback }) => {
    const { requestor, subject, actor} = feedback;
    return (
        <tr>
            <td>{feedback.id}</td>
            <td>{requestor.firstName} {requestor.lastName}</td>
            <td>{subject.firstName} {subject.lastName}</td>
            <td>{actor.firstName} {actor.lastName}</td>
            <td>{feedback.status}</td>
            <td><Link to={`/reportingFeedback/${feedback.id}`}>View Feedback</Link></td>
        </tr>
    )
}

export default ReportingFeedbackExcerpt;