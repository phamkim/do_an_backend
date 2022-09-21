import React, { useState } from "react";
import styled, { keyframes } from "styled-components";
import background from "../assets/images/background.jpg";
import iconBack from "../assets/images/back.png";
import logo from "../assets/images/huyhieudoan.png";
import { useNavigate } from "react-router-dom";

export const Wrapper = styled.div`
  background-image: url(${background});
  background-size: cover;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 100vw;
  height: 100vh;
`;

export const LogInForm = styled.div`
  background: #fff;
  border-radius: 4px;
  height: 500px;
  width: 400px;
  justify-content: center;
  align-items: center;
  position: relative;
  display: flex;
  @media screen and (max-width: 768px) {
    display: flex;
    height: 100%;
    width: 100%;
    border-radius: 0;
  }
`;

export const Detail = styled.div`
  background: transparent;
  height: 500px;
  width: 500px;
  display: flex;
  color: #fff;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-right: 10px;
  margin-top: -70px;
  @media screen and (max-width: 768px) {
    display: none;
  }
`;

export const Icon = styled.p`
  background-image: url(${iconBack});
  background-size: cover;
  height: 30px;
  width: 30px;
  display: block;
  position: absolute;
  top: 2px;
  left: 2px;
  margin: 5px;
  cursor: pointer;
  z-index: 2;
  &:hover {
    transition: all 0.2s ease-in-out;
    height: 26px;
    width: 27px;
  }

  @media screen and (min-width: 768px) {
    display: none;
  }
`;

const jump = keyframes`
  from{
    transform: translateY(0)
  }
  to{
    transform: translateY(-3px)
  }
`;

const Button = styled.button`
  max-width: 100%;
  padding: 11px 13px;
  color: rgb(253, 249, 243);
  font-weight: 600;
  text-transform: uppercase;
  background: #0058c0;
  border: none;
  border-radius: 3px;
  outline: 0;
  cursor: pointer;
  margin-top: 0.6rem;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1), 0 1px 2px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease-out;
  :hover {
    background: rgb(50, 132, 200);
    animation: ${jump} 0.2s ease-out forwards;
  }
`;

const Input = styled.input`
  min-width: 100%;
  padding: 11px 13px;
  background: #f9f9fa;
  color: #0058c0;
  margin-bottom: 0.9rem;
  border-radius: 4px;
  outline: 0;
  border: 1px solid rgba(245, 245, 245, 0.7);
  font-size: 14px;
  transition: all 0.3s ease-out;
  box-shadow: 0 0 3px rgba(0, 0, 0, 0.1), 0 1px 1px rgba(0, 0, 0, 0.1);
  :focus,
  :hover {
    box-shadow: 0 0 3px rgba(0, 0, 0, 0.15), 0 1px 5px rgba(0, 0, 0, 0.1);
  }
`;

const Form = styled.form`
  margin-top: -100px;
  width: 100%;
  max-width: 350px;
  padding: 1.3rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
`;

const Logo = styled.img`
  width: ${(props) => props.width};
  margin-bottom: 20px;
  display: block;
`;

const ColCenter = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding-top: 50px;
`;

const Title = styled.div`
  font-weight: ${(props) => props.fontWeight};
  color: ${(props) => props.color};
  font-size: ${(props) => props.size};
  text-align: ${(props) => props.textALign};
  margin-bottom: 5px;
  width: 100%;
`;

const LogIn = () => {
  const [dados, setDados] = useState({
    email: "",
    password: "",
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(dados);
  };

  const handleChange = (e) => {
    e.preventDefault();
    const { name, value } = e.target;
    setDados(Object.assign(dados, { [name]: value }));
  };

  let navigate = useNavigate();

  return (
    <Wrapper>
      <Detail>
        <Logo src={logo} width="70px" />
        <Title color="#f8f23d" size="20px" textALign="center" fontWeight="bold">
          HỆ THỐNG QUẢN LÝ VĂN BẢN VÀ ĐIỀU HÀNH
        </Title>
        <Title color="white" textALign="center" fontWeight="bold">
          TRUNG ƯƠNG ĐOÀN TNCS HỒ CHÍ MINH
        </Title>
      </Detail>

      <LogInForm>
        <Icon
          onClick={() => {
            navigate("/");
          }}
        />
        <Form onSubmit={handleSubmit}>
          <Logo src={logo} width="80px" />
          <Title>Tên truy cập</Title>
          <Input
            type="email"
            name="email"
            // value={dados.email}
            // onChange={handleChange}
          />
          <Title>Mật khẩu</Title>
          <Input
            type="password"
            name="password"
            // value={dados.password}
            // onChange={handleChange}
          />
          <Button
            onClick={() => {
              navigate("/");
            }}
          >
            Đăng nhập
          </Button>
        </Form>
      </LogInForm>
    </Wrapper>
  );
};

export default LogIn;
