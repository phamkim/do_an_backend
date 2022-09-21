import styled from "styled-components";
import { Image } from "antd";
export const BaseBody = styled.div`
  display: flex;
  justify-content: center;
  padding-top: 10px;
  /* align-items: center; */
  background: #fff;
  max-width: 750px;
  width: 50vw;

  @media screen and (max-width: 768px) {
    width: 100vw;
  }
`;

export const BaseImage = styled(Image)`
  width: 100%;
  height: auto;
  @media screen and (max-width: 768px) {
    width: 100vw;
  }
`;
