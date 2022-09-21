import React from "react";
import { Carousel } from "react-responsive-carousel";
import "react-responsive-carousel/lib/styles/carousel.min.css";
import styled from "styled-components";

const Wrapper = styled.div`
  height: 500px;
  max-width: 700px;
  width: 47vw;
  justify-content: center;
  align-items: center;

  @media screen and (max-width: 768px) {
    width: 100vw;
  }
`;

const Iframe = styled.iframe`
  padding-top: 50px;
  height: 500px;
`;

const CarouselStyled = styled(Carousel)`
  background: #f2f2f2;
`;

const MyCarousel = () => {
  return (
    <Wrapper>
      <CarouselStyled
        infiniteLoop
        autoPlay
        showArrows={true}
        showThumbs={false}
        showIndicators={true}
        showStatus={true}
      >
        <Iframe src="https://momento360.com/e/u/2b9141e13a2f4095b683ce9faac75910?utm_campaign=embed&utm_source=other&heading=37.4&pitch=-16.26&field-of-view=20&size=medium" />

        <Iframe src="https://momento360.com/e/u/2b9141e13a2f4095b683ce9faac75910?utm_campaign=embed&utm_source=other&heading=37.4&pitch=-16.26&field-of-view=20&size=medium" />

        <Iframe src="https://momento360.com/e/u/2b9141e13a2f4095b683ce9faac75910?utm_campaign=embed&utm_source=other&heading=37.4&pitch=-16.26&field-of-view=20&size=medium" />

        <Iframe src="https://momento360.com/e/u/2b9141e13a2f4095b683ce9faac75910?utm_campaign=embed&utm_source=other&heading=37.4&pitch=-16.26&field-of-view=20&size=medium" />

        <Iframe src="https://momento360.com/e/u/2b9141e13a2f4095b683ce9faac75910?utm_campaign=embed&utm_source=other&heading=37.4&pitch=-16.26&field-of-view=20&size=medium" />
      </CarouselStyled>
    </Wrapper>
  );
};

export default MyCarousel;
