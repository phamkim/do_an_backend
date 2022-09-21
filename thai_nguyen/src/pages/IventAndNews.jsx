import React from "react";
import styled from "styled-components";
import MyContent from "../components/Layout/MyContent";

const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

const IventAndNews = () => {
  const content = `
    <p style="text-align: justify;"><span>Tiếp v&agrave; l&agrave;m việc với Đo&agrave;n c&oacute; đồng ch&iacute; Phạm Ho&agrave;ng Sơn, Ph&oacute; B&iacute; thư Thường trực Tỉnh ủy, Chủ tịch HĐND tỉnh; Dương Văn Tiến, Ủy vi&ecirc;n Ban Thường vụ Tỉnh uỷ, Trưởng Ban D&acirc;n vận Tỉnh ủy; Nguyễn Thanh B&igrave;nh, Ph&oacute; Chủ tịch UBND tỉnh v&agrave; đại diện c&aacute;c sở, ng&agrave;nh của tỉnh.</span></p>
  <p style="text-align: justify;"><span>Trong 15 năm thực hiện NQ số 25-NQ/TW ở Th&aacute;i Nguy&ecirc;n đ&atilde; đạt được nhiều kết quả quan trọng. Trong đ&oacute; c&ocirc;ng t&aacute;c l&atilde;nh chỉ đạo của Đảng với thanh ni&ecirc;n được tăng cường r&otilde; rệt nhiều cơ chế thuận lợi cho thanh ni&ecirc;n trong gi&aacute;o dục học tập giải quyết việc l&agrave;m. Một số kết quả nổi bật như: To&agrave;n tỉnh đ&atilde; tổ chức được 1.432 lớp học tập n&acirc;ng cao tr&igrave;nh độ chuy&ecirc;n m&ocirc;n nghiệp vụ cho 46.310 thanh ni&ecirc;n; 1.549 lớp chuyển giao khoa học kỹ thuật cho 43.520 thanh ni&ecirc;n; tổ chức 415 lớp phổ cập ngoại ngữ, tin học cho 16.831 thanh ni&ecirc;n; duy tr&igrave; hiệu quả 72 quỹ - giải thưởng hỗ trợ trực tiếp cho 5.934 lượt thanh ni&ecirc;n thực hiện c&aacute;c &yacute; tưởng, đề t&agrave;i nghi&ecirc;n cứu khoa học; to&agrave;n tỉnh đ&atilde; tư vấn, giới thiệu việc l&agrave;m cho tr&ecirc;n 182.968 lượt người; tổ chức 187 lớp dạy nghề cho 5.724 lao động n&ocirc;ng th&ocirc;n, tư vấn hướng nghiệp cho 79.987 học sinh, sinh vi&ecirc;n; tư vấn cho 503 lao động xuất khẩu nước ngo&agrave;i trong độ tuổi thanh ni&ecirc;n; c&aacute;c thiết chế văn ho&aacute;, thể thao được quan t&acirc;m đầu tư, x&acirc;y dựng đảm bảo m&ocirc;i trường văn h&oacute;a l&agrave;nh mạnh, h&igrave;nh th&agrave;nh lối sống văn h&oacute;a trong thanh ni&ecirc;n. Ghi nhận, đ&aacute;nh gi&aacute; cao việc triển khai thực hiện Nghị quyết số 25 tại tỉnh Th&aacute;i Nguy&ecirc;n Đo&agrave;n gi&aacute;m s&aacute;t cũng trao đổi, l&agrave;m r&otilde; một số kh&oacute; khăn, vướng mắc trong thời gian qua của tỉnh Th&aacute;i Nguy&ecirc;n.</span></p>
  <p style="text-align: justify;"><span><img src="https://cms.thainguyen.vn/documents/130212/9068449/Vuot+qua+1.jpg/dd8cc476-ba9b-4ae6-b226-0a9bdb136ec3?t=1663405359401" alt="dede"  style="max-width: 100%; height: auto;" /></span></p>  
    `;

  return (
    <Container>
      <MyContent
        title={"Lịch tiếp công dân của đồng chí Bí thư Huyện ủy năm 2021"}
        time={" 28-06-2021 16:12"}
        content={content}
      />
    </Container>
  );
};

export default IventAndNews;
