import React from "react";
import '../style/style.scss'

const Footer = () => {
        return (
            <footer>
                <div className="container">
                    <div className="footer-top">
                        <a href="#" className="footer-logo"><img src="images/tour-logo.png" /></a>
                        <div className="footer-social">
                            <a href="#"><i className="ri-facebook-fill"></i></a>
                            <a href="#"><i className="ri-instagram-fill"></i></a>
                            <a href="#"><i className="ri-twitter-fill"></i></a>
                            <a href="#"><i className="ri-linkedin-fill"></i></a>
                        </div>
                    </div>
                    <div className="footer-links row mt-4">
                        <div className="col-6">
                            <b style={{ fontSize: "20px" }}>Công ty cổ phần du lịch Việt Nam SaiGonTrip</b>
                            <p className="mt-2">Tổng đài chăm sóc: 1900 2083</p>
                            <p>Email: hotro@saigontrip.vn</p>
                            <p>Văn phòng chính: Cong vien phan mem Quang Trung</p>
                            <div className="mb-3 row">
                                <label for="exampleInputEmail1" className="form-label">Đăng ký nhận thông báo mới nhất từ chúng tôi:</label>
                                <form className="d-flex">
                                    <input className="form-control me-2 col-ms-12" type="Email" placeholder="Email" aria-label="Email" style={{ width: "280px" }} />
                                    <button className="btn btn-success" type="submit">Gửi</button>
                                </form>
                            </div>
                            <div>
                                <a href=""><img src="../images/logo-bocongthuong.png" style={{ height: "50px" }}></img></a>
                                <a href=""><img src="../images/logo-dkbocongthuong.png" style={{ height: "40px", marginLeft: "20px" }}></img></a>
                            </div>
                        </div>
                        <div className="col-2">
                            <div className="footer-links-title">Chính sách & Quy định</div>
                            <ul className="footer-links-list">
                                <li><a href="#">Điều khoản & điều kiện</a></li>
                                <li><a href="#">Quy định thanh toán</a></li>
                                <li><a href="#">Quy chế hoạt động</a></li>
                                <li><a href="#">Chương trình khách hàng thân thiết</a></li>
                                <li><a href="#">Chương trình đánh giá trải nghiệm khách sạn</a></li>
                            </ul>
                        </div>
                        <div className="col-2">
                            <div className="footer-links-title">Khách hàng và đối tác</div>
                            <ul className="footer-links-list">
                                <li><a href="#">Dịch Vụ</a></li>
                                <li><a href="#">Liên Hệ</a></li>
                                <li><a href="#">Chi Tiết Dịch Vụ</a></li>
                                <li><a href="#">Đăng Nhập</a></li>
                                <li><a href="#">Tuyển Dụng</a></li>
                            </ul>
                        </div>
                        <div className="col-2">
                            <div className="footer-links-title">Khác</div>
                            <ul className="footer-links-list">
                                <li><a href="#">SaiGonTrip Blog</a></li>
                                <li><a href="#">Địa Điểm Nổi Tiếng</a></li>
                                <li><a href="#">Xu Hướng</a></li>
                            </ul>
                        </div>
                    </div>
                    <div className="footer-bottom text-center row">
                        <p>SaiGonTrip là thành viên của FPT Services - Một trong những tập đoàn đứng đầu Đông Nam Á về du lịch trực tuyến và các dịch vụ liên quan</p>
                        <div className="footer-line-img">
                            <img src="../images/logo-fpt.png" className="col-4 " style={{ height: "100px", width: "100px" }}></img>
                            <img src="../images/logo-footer1.png" className="col-4 " style={{ height: "100px", width: "100px" }}></img>
                            <img src="../images/logo-footer2.png" className="col-4 " style={{ height: "120px", width: "120px" }}></img>
                            <img src="../images/logo-footer3.png" className="col-4" style={{ height: "100px", width: "100px" }}></img>
                        </div>
                        <p>CÔNG TY CỔ PHẦN DU LỊCH SaiGonTrip - Đăng ký kinh doanh số 113 - do Sở Kế hoạch và Đầu tư thành phố Hồ Chí Minh cấp lần đầu ngày 29 tháng 11 năm 2023</p>
                    </div>
                </div>
            </footer>
        )
}

export default Footer;