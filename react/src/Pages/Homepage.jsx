import React from "react";
import HomeCarousel from "../customer/Components/Carousel/HomeCarousel";

import { homeCarouselData } from "../customer/Components/Carousel/HomeCaroselData";
import HomeProductSection from "../customer/Components/Home/HomeProductSection";
import {novels} from "../Data/novel/novel";
import {collection} from "../Data/collection/collection";
import {fiction} from "../Data/fiction/fiction";
import {scific} from "../Data/scific/scific";
import {comic} from "../Data/comic/comic";
import {antique} from "../Data/antique/antique";



const Homepage = () => {
  return (
    <div className="">
      <HomeCarousel images={homeCarouselData} />

      <div className="space-y-10 py-20">
        <HomeProductSection data={novels} section={"BestSeller"} />
        <HomeProductSection data={collection} section={"Our Collections"} />
        <HomeProductSection data={antique} section={"Old Antique"} />
        <HomeProductSection data={fiction} section={"Fiction"} />
        <HomeProductSection data={scific} section={"Sci-Fi"} />
        <HomeProductSection data={comic} section={"Comics"} /> 

        {/* Uncomment these sections as needed */}
        {/* <HomeProductSection data={dressPage1} section={"Dress"} />
        <HomeProductSection data={gounsPage1} section={"Women's Gouns"} />
        <HomeProductSection data={kurtaPage1} section={"Women's Kurtas"} />
        <HomeProductSection data={mensPantsPage1} section={"Men's Pants"} /> */}
      </div>
    </div>
  );
};

export default Homepage;
