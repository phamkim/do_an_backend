import React from "react";
import styled from "styled-components";

const Wrapper = styled.div`
  display: block;
  max-width: 700px;
  padding-top: 30px;
  background: #fff;
  padding: 10px;
`;

const Title = styled.div`
  color: #194ce6;
  margin-bottom: 5px;
`;

const Time = styled.div`
  font-size: 12px;
  margin-bottom: 20px;
`;

const MyContent = ({ content, title, time }) => {
  const renderHTML = (escapedHTML) =>
    React.createElement("div", {
      dangerouslySetInnerHTML: { __html: escapedHTML },
    });
  return (
    <Wrapper>
      <Title>{title}</Title>
      <Time>{time}</Time>
      {renderHTML(content)}
    </Wrapper>
  );
};

export default MyContent;
