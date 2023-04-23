import { Link } from 'react-router-dom';

const AssignedFeedbackExcerpt = ({ feedback }) => {
    const { requestor, subject, actor} = feedback;
    return (
        <tr>
            <td>{feedback.id}</td>
            <td>{requestor.firstName} {requestor.lastName}</td>
            <td>{subject.firstName} {subject.lastName}</td>
            <td>{feedback.status}</td>
            <td><Link to={`/assignedFeedback/${feedback.id}`}>Provide Feedback</Link></td>
        </tr>
    )
}

export default AssignedFeedbackExcerpt;