const LeftNav = () => {

    return (
        <aside className="left-sidebar" data-sidebarbg="skin6">
            <div className="scroll-sidebar">
                <nav className="sidebar-nav">
                    <ul id="sidebarnav">
                        <li className="sidebar-item selected"> <a className="sidebar-link waves-effect waves-dark sidebar-link active"
                            href="index.html" aria-expanded="false">
                                <i className="mdi me-2 mdi-account-check"></i><span className="hide-menu">My Feedback</span></a>
                        </li>
                        <li className="sidebar-item"> <a className="sidebar-link waves-effect waves-dark sidebar-link"
                            href="index.html" aria-expanded="false">
                                <i className="mdi me-2 mdi-clipboard-text"></i><span className="hide-menu">Requested Feedback</span></a>
                        </li>
                        <li className="sidebar-item"> <a className="sidebar-link waves-effect waves-dark sidebar-link"
                            href="index.html" aria-expanded="false">
                                <i className="mdi me-2 mdi-arrow-down"></i><span className="hide-menu">Reporting Feedback</span></a>
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