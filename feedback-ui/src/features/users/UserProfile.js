const UserProfile = () => {
    return (
        <div className="col-lg-4 col-xlg-3">
                        <div className="card">
                            <img className="card-img-top" src="../assets/images/background/profile-bg.jpg" alt="Card image cap" />
                            <div className="card-body little-profile text-center">
                                <div className="pro-img"><img src="../assets/images/users/4.jpg" alt="user" /></div>
                                <h3 className="mb-0">Angela Dominic</h3>
                                <p>Web Designer &amp; Developer</p>
                                <a href="" className="mt-2 waves-effect waves-dark btn btn-primary btn-md btn-rounded">Follow</a>
                                <div className="row text-center mt-3">
                                    <div className="col-lg-4 col-md-4 mt-3">
                                        <h3 className="mb-0 font-light">1099</h3><small>Articles</small>
                                    </div>
                                    <div className="col-lg-4 col-md-4 mt-3">
                                        <h3 className="mb-0 font-light">23,469</h3><small>Followers</small>
                                    </div>
                                    <div className="col-lg-4 col-md-4 mt-3">
                                        <h3 className="mb-0 font-light">6035</h3><small>Following</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="card">
                            <div className="card-body bg-info">
                                <h4 className="text-white card-title">My Contacts</h4>
                                <h6 className="card-subtitle text-white mb-0 op-5">Checkout my contacts here</h6>
                            </div>
                            <div className="card-body">
                                <div className="message-box contact-box">
                                    <h2 className="add-ct-btn"><button type="button" className="btn btn-circle btn-lg btn-success waves-effect waves-dark">+</button>
                                    </h2>
                                    <div className="message-widget contact-widget">
                                        <a href="#" className="d-flex align-items-center">
                                            <div className="user-img mb-0"> <img src="../assets/images/users/1.jpg" alt="user" className="img-circle" /> <span className="profile-status online pull-right"></span> </div>
                                            <div className="mail-contnet">
                                                <h5 className="mb-0">Pavan kumar</h5> <span className="mail-desc">info@wrappixel.com</span>
                                            </div>
                                        </a>
                                        <a href="#" className="d-flex align-items-center">
                                            <div className="user-img mb-0"> <img src="../assets/images/users/2.jpg" alt="user" className="img-circle" /> <span className="profile-status busy pull-right"></span> </div>
                                            <div className="mail-contnet">
                                                <h5 className="mb-0">Sonu Nigam</h5> <span className="mail-desc">pamela1987@gmail.com</span>
                                            </div>
                                        </a>
                                        <a href="#" className="d-flex align-items-center">
                                            <div className="user-img mb-0"> <span className="round">A</span> <span className="profile-status away pull-right"></span> </div>
                                            <div className="mail-contnet">
                                                <h5 className="mb-0">Arijit Sinh</h5> <span className="mail-desc">cruise1298.fiplip@gmail.com</span>
                                            </div>
                                        </a>
                                        <a href="#" className="d-flex align-items-center">
                                            <div className="user-img mb-0"> <img src="../assets/images/users/4.jpg" alt="user" className="img-circle" /> <span className="profile-status offline pull-right"></span> </div>
                                            <div className="mail-contnet">
                                                <h5 className="mb-0">Pavan kumar</h5> <span className="mail-desc">kat@gmail.com</span>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
    )
}

export default UserProfile; 