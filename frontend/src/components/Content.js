import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap";

const Content = () => {
    return (
        <div className="container-fluid" style={{ backgroundColor: "#EEE5F9" }}>
            <div className="row">
                <div className="col-5 ms-5">
                    <div className="mt-2">
                        <div className="one">
                            <h3 className="text-uppercase fw-bold text-center">Kiến Tạo Khoảng Khắc</h3>
                        </div>
                        <div className="content-1">
                            <img src="https://vcdn1-dulich.vnecdn.net/2021/06/02/123805720-1795574597247390-537-4293-1878-1622600638.jpg?w=0&h=0&q=100&dpr=1&fit=crop&s=yoNaxu630aCJ9kiQCUcovw"></img>
                            <img className="ms-2" src="https://vn-t.com/attachments/a1-jpg.2040/"></img>
                            <img
                                className="mt-2"
                                src="https://1.bp.blogspot.com/-w_5l5T5gvGA/X-FzFbNpzXI/AAAAAAAAH5U/rIMCDyDwU28a4wZjcfUhPIwY5Ezj5_fGACLcBGAsYHQ/s2048/IMG_3829.JPG"
                                style={{ height: "300px", width: "608px" }}
                            ></img>
                        </div>
                    </div>
                </div>
                <video autoPlay muted loop className="w-50 mt-5 ms-5">
                    <source src="images/video-banner.mp4" type="video/mp4"></source>
                </video>
            </div>

            <div className="two">
                <h3 className="text-uppercase fw-bold">Liên Hệ Với Chúng Tôi</h3>
            </div>

            <div className="row">
                <div className="col-5 ms-5">
                    <div className="mt-2">
                        <iframe
                            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3918.444093308443!2d106.62348197481903!3d10.85378815776246!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752b6c59ba4c97%3A0x535e784068f1558b!2zVHLGsOG7nW5nIENhbyDEkeG6s25nIEZQVCBQb2x5dGVjaG5pYw!5e0!3m2!1svi!2s!4v1697986840394!5m2!1svi!2s"
                            width="680"
                            height="295"
                            style={{ border: 0 }}
                            allowfullscreen=""
                            loading="lazy"
                            referrerpolicy="no-referrer-when-downgrade"
                        ></iframe>
                    </div>
                </div>

                <div className="col-6 ms-5">
                    <div className="mt-2">
                        <form action="" method="">
                            <div class="card border-success rounded-3">
                                <div class="card-header p-0">
                                    <div class="bg-success text-white text-center py-2">
                                        <h4 className="text-uppercase fw-bold">GIẢI ĐÁP THẮC MẮC</h4>
                                    </div>
                                </div>
                                <div class="card-body p-3">
                                    <div class="form-group">
                                        <div class="input-group mb-2">
                                            <div class="input-group-prepend">
                                                <div class="input-group-text">
                                                    <i class="bi bi-person"></i>
                                                </div>
                                            </div>
                                            <input
                                                type="text"
                                                class="form-control"
                                                id="nombre"
                                                name="nombre"
                                                placeholder="Họ Tên"
                                                required
                                            />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group mb-2">
                                            <div class="input-group-prepend">
                                                <div class="input-group-text">
                                                    <i class="bi bi-envelope"></i>
                                                </div>
                                            </div>
                                            <input
                                                type="email"
                                                class="form-control"
                                                id="nombre"
                                                name="email"
                                                placeholder="Email"
                                                required
                                            />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group mb-2">
                                            <div class="input-group-prepend">
                                                <div class="input-group-text">
                                                    <i class="bi bi-pen"></i>
                                                </div>
                                            </div>
                                            <textarea class="form-control" placeholder="Câu Hỏi" required></textarea>
                                        </div>
                                    </div>
                                    <div className="text-end">
                                        <button
                                            type="submit"
                                            value="Enviar"
                                            className="btn btn-success btn-lg rounded-2"
                                        >
                                            {" "}
                                            Gửi
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Content;
