import styled from "styled-components";
import { NavLink as Link } from "react-router-dom";

export const Nav = styled.nav`
  position: sticky;
  top: 0;
  width: 100%;
  background: #0058c0;
  height: ${(props) => (props.extendNavBar ? "" : "45px")};
  display: flex;
  justify-content: space-between;
  z-index: 10;
  margin-top: -5px;
  @media screen and (min-width: 768px) {
    height: 45px;
  }

  @media screen and (max-width: 768px) {
    margin: 0;
    display: flex;
    align-items: center;
    justify-content: center;
  }
`;

export const NavLink = styled(Link)`
  color: #fff;
  display: flex;
  text-decoration: none;
  justify-content: center;
  align-items: center;
  padding: 0 1rem;
  font-weight: lighter;
  margin: 0;
  height: 100%;
  padding-bottom: 12px;
  padding-top: 12px;
  cursor: pointer;
  /* &.active {
    color: #bce6f1;
  } */
  &:hover {
    transition: all 0.2s ease-in-out;
    background: #41a2eb;
    color: #fff;
  }
`;

export const Bars = styled.button`
  display: none;
  color: #fff;
  @media screen and (max-width: 768px) {
    display: block;
    position: absolute;
    top: 0;
    right: 20px;
    background: none;
    border: none;
    font-size: 1.9rem;
    cursor: pointer;
  }
`;

export const NavLogo = styled.img`
  display: none;
  @media screen and (max-width: 768px) {
    display: block;
    position: absolute;
    top: 2px;
    left: 20px;
    width: 30px;
  }
`;

export const NavMenu = styled.div`
  display: flex;
  align-items: center;
  @media screen and (max-width: 768px) {
    display: none;
  }
`;

export const NavMenuExtend = styled.div`
  display: flex;
  flex-direction: column;
  padding-top: 50px;
  padding-bottom: 50px;
  @media screen and (min-width: 768px) {
    display: none;
  }
`;

export const NavBtn = styled.nav`
  display: flex;
  align-items: center;
  margin-right: 24px;

  @media screen and (max-width: 768px) {
    display: none;
  }
`;

export const NavBtnLink = styled(Link)`
  /* border-radius: 4px; */
  background: #256ce1;
  padding: 10px 22px;
  color: #fff;
  border: none;
  outline: none;
  cursor: pointer;
  text-align: center;
  transition: all 0.2s ease-in-out;
  text-decoration: none;

  &:hover {
    transition: all 0.2s ease-in-out;
    background: #fff;
    color: #010606;
  }
`;

export const NavSubLink = styled.div`
  position: absolute;
  display: flex;
  min-width: 100%;
  min-width: max-content;
  background: #0058c0;
  flex-direction: column;
  visibility: hidden;
  opacity: 0;
  z-index: 2;
  transition: visibility 0.3s ease-in-out, opacity 0.3s ease-in-out;
`;

export const NavSubMenu = styled.div`
  position: relative;
  margin: 0;
  height: 100%;
  min-width: max-content;
  display: inline-block;
  &:hover ${NavSubLink} {
    visibility: visible;
    opacity: 1;
    cursor: pointer;
  }
`;
