package web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Province;
import service.ProvinceService;
import service.impl.ProvinceServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/provinceServlet")
public class ProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        //获取数据库数据
        ProvinceService provinceService=new ProvinceServiceimpl();
        String dataJson = provinceService.findAllJson();
        response.getWriter().write(dataJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
