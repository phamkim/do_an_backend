import React from "react";
import styled from "styled-components";

export const FooterBody = styled.div`
  background: #0058c0;
  color: #fff;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 20px;
  padding-bottom: 20px;
`;

export const TextFooter = styled.p`
  padding: 5px;
  font-weight: ${(props)=> props.fontWeight};
  text-align: center;
`;

const MyFooter = () => {
  return (
    <FooterBody>
      <TextFooter fontWeight="bold">
        TRANG THÔNG TIN ĐIỆN TỬ ĐẠI HỘI ĐOÀN TNCS HỒ CHÍ MINH TỈNH THÁI NGUYÊN
        LẦN THỨ XV, NHIỆM KỲ 2022 - 2027
      </TextFooter>
      <TextFooter>
        Địa chỉ: Đường Thanh niên xung phong, phường Gia Sàng, TP Thái Nguyên
      </TextFooter>
      <TextFooter>Điện thoại: 02083.855.224 / Fax: 02083.855.224</TextFooter>
    </FooterBody>
  );
};

export default MyFooter;
