
const ReportingExcerpt = ( { reporting }) => {
    const {profile} = reporting;
    return (
        <a href="#" className="d-flex align-items-center">
            <div className="user-img mb-0"> <img src={profile.imageUrl} alt="user" className="img-circle" /> <span className="profile-status online pull-right"></span> </div>
            <div className="mail-contnet">
                <h5 className="mb-0">{profile.firstName} {profile.lastName}</h5> <span className="mail-desc">{profile.email}</span>
            </div>
        </a>
    )
}

export default ReportingExcerpt;