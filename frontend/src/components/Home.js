import React from "react";
import Aside from "./Aside";
import Article from "./Article";
import Top_Destination from "./Top_Destination";
import Blogs from "./Blogs";
import Content from "./Content";
import Member from "./Member";
const Home = () => {
    return (
        <>
            <div className="container-fluid row">
                <Aside />
                <Article />
            </div>
            <section className="destination">
                <Top_Destination />
                <Blogs />
            </section>
            <section className="content">
                <Content />
            </section>
            <section className="testimonial">
                <Member />
            </section>
        </>
    );
};
export default Home;
