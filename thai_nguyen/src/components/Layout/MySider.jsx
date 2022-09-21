import { Image } from "antd";
import React from "react";
import styled from "styled-components";

const Wrapper = styled.div`
  width: 25vw;
  max-width: 320px;
  min-height: 100vh;
  background: #fff;
  @media screen and (max-width: 768px) {
    display: none;
  }
  padding: 10px;
`;

const Title = styled.div`
  background: #256ce1;
  color: #fff;
  text-align: center;
  padding: 5px 0 5px 0;
`;

const ImageMap = styled(Image)`
  width: 100%;
  height: auto;
`;

const MySider = () => {
  return (
    <Wrapper>
      <Title>BẢN ĐỒ ĐỊA GIỚI HÀNH CHÍNH</Title>
      <ImageMap src="https://thainguyen.gov.vn/documents/20181/209296/ban-do-hanh_chinh_2021.jpg/b93f3e72-d4f9-4750-88fe-42cd04256fb2?t=1638781233613" />
    </Wrapper>
  );
};

export default MySider;
