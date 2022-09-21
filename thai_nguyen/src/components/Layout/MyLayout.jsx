import React from "react";
import { Outlet } from "react-router-dom";
import styled from "styled-components";
import BannerHeader from "../Banner/BannerHeader";
import MyFooter from "./MyFooter";
import NavBar from "../NavBar/NavBar";
import MySider from "./MySider";

export const MyContent = styled.div`
  display: flex;
  justify-content: center;
  min-height: 100vh;
  background: #f2f2f2;
`;

const MyLayout = () => {
  return (
    <>
      <BannerHeader />
      <NavBar />
      <MyContent>
        <Outlet />
        <MySider />
      </MyContent>
      <MyFooter />
    </>
  );
};

export default MyLayout;
