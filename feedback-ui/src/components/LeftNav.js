import { Link, useLocation } from 'react-router-dom'

const LeftNav = () => {

    const { pathname } = useLocation()
    let myActive = "active"
    let requestActive = ""
    let reportingActive = ""

    if (pathname.includes("reportingFeedback")) {
        myActive = ""
        requestActive = ""
        reportingActive = "active"
    } else if (pathname.includes("myFeedback")) {
        myActive = "active"
        requestActive = ""
        reportingActive = ""
    } else if (pathname.includes("requested")){
        myActive = ""
        requestActive = "active"
        reportingActive = ""
    }

    return (
        <aside className="left-sidebar" data-sidebarbg="skin6">
            <div className="scroll-sidebar">
                <nav className="sidebar-nav">
                    <ul id="sidebarnav">
                        <li className="sidebar-item"> <Link to={`/myFeedback`} className={`sidebar-link waves-effect waves-dark sidebar-link ${myActive}`}
                            href="index.html" aria-expanded="false">
                                <i className="mdi me-2 mdi-account-check"></i><span className="hide-menu">My Feedback</span></Link>
                        </li>
                        <li className="sidebar-item"> <a className={`sidebar-link waves-effect waves-dark sidebar-link ${requestActive}`}
                            href="index.html" aria-expanded="false">
                                <i className="mdi me-2 mdi-clipboard-text"></i><span className="hide-menu">Requested Feedback</span></a>
                        </li>
                        <li className="sidebar-item"> <Link to={`/reportingFeedback`} className={`sidebar-link waves-effect waves-dark sidebar-link ${reportingActive}`}
                            href="index.html" aria-expanded="false">
                                <i className="mdi me-2 mdi-arrow-down"></i><span className="hide-menu">Reporting Feedback</span></Link>
                        </li>

                        
                    </ul>

                </nav>
            </div>
            <div className="sidebar-footer">
                <div className="row">
                    <div className="col-4 link-wrap">
                        <a href="" className="link" data-toggle="tooltip" title="" data-original-title="Settings"><i
                            className="ti-settings"></i></a>
                    </div>
                    <div className="col-4 link-wrap">
                        <a href="" className="link" data-toggle="tooltip" title="" data-original-title="Email"><i
                            className="mdi mdi-gmail"></i></a>
                    </div>
                    <div className="col-4 link-wrap">
                        <a href="" className="link" data-toggle="tooltip" title="" data-original-title="Logout"><i
                            className="mdi mdi-power"></i></a>
                    </div>
                </div>
            </div>
        </aside>
    )
}

export default LeftNav