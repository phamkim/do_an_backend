import React from "react";
import { BaseBody, BaseImage } from "./styled";
import map from "../assets/images/map.png";
import styled from "styled-components";

const Wrapper = styled(BaseBody)`
  height: fit-content;
  
`;

const PositionMap = () => {
  return (
    <BaseBody>
      <Wrapper>
        <BaseImage src={map} />
      </Wrapper>
    </BaseBody>
  );
};

export default PositionMap;
