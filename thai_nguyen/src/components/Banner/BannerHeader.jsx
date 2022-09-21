import React from "react";
import styled from "styled-components";
import bannerImage from "../../assets/images/bannerHeader.jpg";

const BannerHeader = () => {
  return (
    <>
      <Img src={bannerImage} />
    </>
  );
};

const Img = styled.img`
  width: 100%;
  margin: 0;
  @media screen and (max-width: 768px) {
    display: none;
  }
`;

export default BannerHeader;
