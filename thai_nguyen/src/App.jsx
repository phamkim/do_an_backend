import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MyLayout from "./components/Layout/MyLayout";
import AddressDigitization from "./pages/AddressDigitization";
import CatalogDoc from "./pages/CatalogDoc";
import Home from "./pages/Home";
import Introduce from "./pages/Introduce";
import IventAndNews from "./pages/IventAndNews";
import LogIn from "./pages/LogIn";
import NotFound from "./pages/NotFound";
import PositionMap from "./pages/PositionMap";

const App = () => {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<MyLayout />}>
            <Route index element={<Home />} />
            <Route path="/gioi-thieu" element={<Introduce />} />
            {/* <Route path="/phong-trao/1" element={<Test2 />} />
            <Route path="/phong-trao/2" element={<Test2 />} /> */}
            <Route path="/tin-tuc-su-kien" element={<IventAndNews />} />
            {/* <Route path="/chuong-trinh/1" element={<Test2 />} />
            <Route path="/chuong-trinh/2" element={<Test2 />} /> */}
            <Route
              path="/so-hoa-dia-chi-do"
              element={<AddressDigitization />}
            />
            <Route path="/danh-muc-van-kien-dai-hoi" element={<CatalogDoc />} />
            <Route path="/so-do-vi-tri-cho-ngoi" element={<PositionMap />} />
          </Route>
          <Route path="/dang-nhap" element={<LogIn />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </Router>
    </>
  );
};

export default App;
