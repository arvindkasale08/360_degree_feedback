import CurrentUserProfile from './CurrentUserProfile';
import DirectReportingList from './DirectReporingList';

const UserProfile = () => {
    return (
        <div className="col-lg-4 col-xlg-3">
            <CurrentUserProfile />
            <DirectReportingList />
        </div>
    )
}

export default UserProfile; 