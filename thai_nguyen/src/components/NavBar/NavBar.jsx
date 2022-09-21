import React, { useState } from "react";
import { Link } from "react-router-dom";
import logo from "../../assets/images/huyhieudoan.png";
import { routes } from "./routes";
import {
  NavLogo,
  Nav,
  Bars,
  NavBtn,
  NavBtnLink,
  NavLink,
  NavMenu,
  NavMenuExtend,
  NavSubMenu,
  NavSubLink,
} from "./styled";
const NavBar = () => {
  const [extendNavBar, setExtendNavBar] = useState(false);

  return (
    <>
      <Nav extendNavBar={extendNavBar}>
        <Link to="/">
          <NavLogo src={logo} />
        </Link>

        <Bars
          onClick={() => {
            setExtendNavBar((crr) => !crr);
          }}
        >
          {extendNavBar ? <>&#10005;</> : <> &#8801;</>}
        </Bars>
        <NavMenu>
          {routes.map((route) => {
            if (route.subRoutes) {
              return (
                <NavSubMenu>
                  <NavLink to={route.link}>{route.name}</NavLink>
                  <NavSubLink>
                    {route.subRoutes.map((subRoute) => (
                      <NavLink to={subRoute.link}>{subRoute.name}</NavLink>
                    ))}
                  </NavSubLink>
                </NavSubMenu>
              );
            } else {
              return <NavLink to={route.link}>{route.name}</NavLink>;
            }
          })}
        </NavMenu>
        <NavBtn>
          <NavBtnLink to="/dang-nhap">Đăng nhập</NavBtnLink>
        </NavBtn>
        {extendNavBar && (
          <NavMenuExtend>
            {routes.map((route) => {
              if (route.subRoutes) {
                return (
                  <NavSubMenu>
                    <NavLink to={route.link}>{route.name}</NavLink>
                    <NavSubLink>
                      {route.subRoutes.map((subRoute) => (
                        <NavLink to={subRoute.link}>{subRoute.name}</NavLink>
                      ))}
                    </NavSubLink>
                  </NavSubMenu>
                );
              } else {
                return <NavLink to={route.link}>{route.name}</NavLink>;
              }
            })}
            <NavBtnLink to="/dang-nhap">Đăng nhập</NavBtnLink>
          </NavMenuExtend>
        )}
      </Nav>
    </>
  );
};

export default NavBar;
